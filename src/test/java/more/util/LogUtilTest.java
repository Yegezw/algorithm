package more.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class LogUtilTest
{

    public static void main(String[] args) throws IOException
    {
        TreeSet<String> set = new TreeSet<>();
        set.add("FilterConfigCacheSupport");
        set.add("AbstractPlatformCacheSupport");
        set.add("ServicePermissionLocator");
        set.add("StatInfoLogger");
        set.add("com.taobao");
        set.add("数据中台标签结果同步");
        set.add("LoadLocalCacheConsumer");

        List<String> slowSQL = new ArrayList<>();

        LogUtil.handleLog(
                "D:\\02-data\\05-DingDingDownload\\03-绿网\\数据迁移\\日志\\01-31",
                block ->
                {
                    for (String ignore : set)
                    {
                        if (block.contains(ignore)) return null;
                    }
                    if (block.contains("StatFilter:478")) slowSQL.add(LogUtil.handleBlock(block));
                    return block;
                }
        );

        FileUtils.writeLines(
                new File("D:\\02-data\\05-DingDingDownload\\03-绿网\\数据迁移\\日志\\01-31\\slow-sql.log"), 
                "UTF-8", 
                slowSQL
        );
    }
}
