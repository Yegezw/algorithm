package more.util;

import lombok.SneakyThrows;

public class SQLUtilTest
{

    private static final String prefix = "dj_";
    private static final String dir    = "D:\\02-data\\05-DingDingDownload\\07-福建省级车联网\\脚本1";

    @SneakyThrows
    public static void main(String[] args)
    {
        LogTool.execute(
                () ->
                {
                    SQLUtil.handleSQL(
                            dir,
                            (line, tableName) ->
                            line.replaceAll(
                                    SQLUtil.wrapper(tableName), SQLUtil.wrapper(prefix + tableName)
                            )
                    );
                    return null;
                },
                "SQL 合并"
        );
    }
}
