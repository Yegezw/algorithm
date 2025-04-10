package more.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SQLUtil
{

    private SQLUtil()
    {
    }

    // ------------------------------------------------

    public static void handleSQL(String dir, BiFunction<String, String, String> sqlHandler)
    {
        handleSQL(new File(dir), sqlHandler);
    }

    @SneakyThrows
    public static void handleSQL(File dir, BiFunction<String, String, String> sqlHandler)
    {
        Preconditions.checkArgument(dir != null, "dir is null");
        Preconditions.checkArgument(dir.isDirectory(), "dir is not a directory");

        File[] files = dir.listFiles();
        if (files == null) return;

        CountDownLatch latch = new CountDownLatch(files.length);
        for (File file : files)
        {
            String extension = FilenameUtils.getExtension(file.getName());
            if (!(extension.equals("sql"))) continue;
            Pool.commonPool.submit(() -> handleFile(file, sqlHandler, latch));
        }
        latch.await();
    }

    // ------------------------------------------------

    private static final int     mergeSize = 500;
    private static final Pattern pattern   = Pattern.compile("INSERT INTO `([^`]+)` VALUES (.+);");

    @SneakyThrows
    private static void handleFile(File file, BiFunction<String, String, String> sqlHandler, CountDownLatch latch)
    {
        List<String> lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
        String tableName = getTableName(lines);
        if (tableName == null) return;

        for (int i = 0; i < lines.size(); i++)
        {
            String line = lines.get(i);
            line = sqlHandler.apply(line, tableName);
            lines.set(i, line);
        }

        // 合并 sql
        List<String>       sqlList   = new ArrayList<>(50);
        List<List<String>> partition = Lists.partition(lines, mergeSize);
        for (List<String> p : partition)
        {
            String sql = mergeInsertStatements(p);
            if (sql != null) sqlList.add(sql);
        }

        // 替换 sql
        List<String> newLines = new ArrayList<>(100);
        for (String line : lines)
        {
            if (!line.startsWith("INSERT INTO")) newLines.add(line);
        }
        newLines.addAll(sqlList);

        FileUtils.writeLines(file, newLines, "\n", false);
        latch.countDown();
    }

    public static String getTableName(List<String> lines)
    {
        for (String line : lines)
        {
            String tableName = getTableName(line);
            if (tableName != null) return tableName;
        }
        return null;
    }

    public static String getTableName(String line)
    {
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) return matcher.group(1);
        return null;
    }

    public static String mergeInsertStatements(List<String> lines)
    {
        String        tableName     = null;
        StringBuilder valuesBuilder = new StringBuilder();

        for (String sql : lines)
        {
            Matcher matcher = pattern.matcher(sql);
            if (matcher.find())
            {
                if (tableName == null)
                {
                    tableName = matcher.group(1);
                }
                else if (!tableName.equals(matcher.group(1)))
                {
                    throw new IllegalArgumentException("All SQL statements must target the same table.");
                }
                valuesBuilder.append(matcher.group(2)).append(", ");
            }
        }

        if (tableName != null)
        {
            // 去掉最后一个 ", "
            if (valuesBuilder.length() > 2)
            {
                valuesBuilder.setLength(valuesBuilder.length() - 2);
            }
            return String.format("INSERT INTO `%s` VALUES %s;", tableName, valuesBuilder);
        }
        return null;
    }

    public static String wrapper(String str)
    {
        return "`" + str + "`";
    }
}
