package more.util;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogUtil
{

    private LogUtil()
    {
    }

    // ------------------------------------------------

    public static void handleLog(String dir, Function<String, String> blockHandler)
    {
        handleLog(new File(dir), blockHandler);
    }

    public static void handleLog(File dir, Function<String, String> blockHandler)
    {
        Preconditions.checkArgument(dir != null, "dir is null");
        Preconditions.checkArgument(dir.isDirectory(), "dir is not a directory");

        File[] files = dir.listFiles();
        if (files == null) return;

        for (File file : files)
        {
            String extension = FilenameUtils.getExtension(file.getName());
            if (!(extension.equals("txt") || extension.equals("log"))) continue;
            handleFile(file, blockHandler);
        }
    }

    public static void handleFile(String file, Function<String, String> blockHandler)
    {
        handleFile(new File(file), blockHandler);
    }

    public static void handleFile(File file, Function<String, String> blockHandler)
    {
        try
        {
            List<String> src = toLogBlocks(file);
            List<String> res = new ArrayList<>(src.size() / 2);

            for (String block : src)
            {
                String newBlock = blockHandler.apply(block);
                if (newBlock != null) res.add(handleBlock(newBlock));
            }

            File resFile = new File(file.getParentFile(), "res/" + file.getName());
            FileUtils.writeLines(resFile, "UTF-8", res);
        }
        catch (Exception ignore)
        {
        }
    }

    // ------------------------------------------------

    /**
     * 日志块的开头 yyyy-MM-dd HH:mm:ss.SSS [INFO]
     */
    private static final Pattern LOG_START_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3} \\[\\w+]", Pattern.MULTILINE);

    /**
     * 解析日志文件为日志块
     *
     * @param file 日志文件
     * @return 日志块列表
     */
    public static List<String> toLogBlocks(File file) throws IOException
    {
        List<String> logBlocks = new ArrayList<>();

        String  content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        Matcher matcher = LOG_START_PATTERN.matcher(content);

        List<Integer> index = new ArrayList<>();
        while (matcher.find()) index.add(matcher.start());

        for (int i = 0; i < index.size(); i++)
        {
            int l = index.get(i);
            int r = (i != index.size() - 1) ? index.get(i + 1) : content.length();
            logBlocks.add(content.substring(l, r));
        }

        return logBlocks;
    }

    // ------------------------------------------------

    private static final Pattern MYBATIS_1     = Pattern.compile("\\(\\s*(?:\\s*(?:\\?|NOW\\(\\))\\s*,?)+\\s*\\)(?:\\s*,\\s*\\(\\s*(?:\\s*(?:\\?|NOW\\(\\))\\s*,?)+\\s*\\))*", Pattern.DOTALL);
    private static final Pattern MYBATIS_2     = Pattern.compile("\\(\\s*((?:\\s*(?:\\?|NOW\\(\\))\\s*,?)+)\\s*\\)", Pattern.DOTALL);
    private static final Joiner  JOINER_NORMAL = Joiner.on(", ");

    public static String handleBlock(String block)
    {
        Matcher sqlMatcher = MYBATIS_1.matcher(block);
        if (!sqlMatcher.find()) return ignoreEmptyLine(block);

        String       srcSQL        = sqlMatcher.group();
        Matcher      paramMatcher  = MYBATIS_2.matcher(srcSQL);
        List<String> paramList = new ArrayList<>();
        while (paramMatcher.find())
        {
            // 替换为单行格式
            String param = paramMatcher.group(1)
                    .replaceAll("\\s+", " ")
                    .replaceAll(" ,", ",")
                    .trim();
            paramList.add(String.format("(%s)", param));
        }
        String resSQL = JOINER_NORMAL.join(paramList);

        return ignoreEmptyLine(block.replace(srcSQL, resSQL + "\n"));
    }

    // ------------------------------------------------

    /**
     * 换行符合并
     */
    private static final Joiner   JOINER_SEPARATOR   = Joiner.on("\n");
    /**
     * 换行符分割
     */
    private static final Splitter SPLITTER_SEPARATOR = Splitter.on("\n").omitEmptyStrings();
    /**
     * 右不可见字符
     */
    private static final Pattern  RIGHT_EMPTY        = Pattern.compile("\\s+$");

    public static String ignoreEmptyLine(String str)
    {
        Iterable<String> it   = SPLITTER_SEPARATOR.split(str);
        List<String>     list = new ArrayList<>();
        for (String line : it)
        {
            line = RIGHT_EMPTY.matcher(line).replaceAll("");
            if (line.isEmpty()) continue;
            if (line.startsWith("[")) line = "        " + line;
            line = line.replaceAll("millis\\. ", "millis. \n        ");
            list.add(line);
        }
        return JOINER_SEPARATOR.join(list);
    }
}
