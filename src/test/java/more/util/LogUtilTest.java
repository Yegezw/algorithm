package more.util;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class LogUtilTest
{

    public static final TreeSet<String> keywordSet = new TreeSet<>();
    static
    {
        keywordSet.add("气象站点");
        keywordSet.add("基本信息");

        keywordSet.add("月功率因数");
        keywordSet.add("月电量");
        keywordSet.add("月电费组成");

        keywordSet.add("日电量");
        keywordSet.add("日负载96点数据同步成功");
        keywordSet.add("预测天气数据同步成功");
        keywordSet.add("历史天气数据同步成功");

        keywordSet.add("数据同步-单线程监控");
    }

    @Test
    void test() throws IOException
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
                "D:\\02-data\\05-DingDingDownload\\03-绿网\\0314日志",
                block ->
                {
                    for (String ignore : set)
                    {
                        if (block.contains(ignore)) return null;
                    }
                    for (String keyword : keywordSet)
                    {
                        if (block.contains(keyword)) print(LogUtil.handleBlock(block));
                    }
                    if (block.contains("StatFilter:478"))
                    {
                        if (block.contains("sup_lx_day_ana_index") ||  block.contains("sup_lx_month_ana_index"))
                        {
                            slowSQL.add(LogUtil.handleBlock(block));
                        }
                    }
                    return block;
                }
        );

        FileUtils.writeLines(
                new File("D:\\02-data\\05-DingDingDownload\\03-绿网\\0314日志\\res\\slow-sql.log"), 
                "UTF-8", 
                slowSQL
        );
    }

    void print(String str)
    {
        String res = str.replaceAll(" \\[com.sgcc.iesg.datatransfer.lx.transfer.buffer.adapter.monitor.SingleThreadMonitorAdapter:30]", "");
        System.out.println(res);
    }
}
