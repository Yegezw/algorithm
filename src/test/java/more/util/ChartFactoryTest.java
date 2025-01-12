package more.util;

import com.alibaba.fastjson2.JSONObject;
import more.util.chart.Chart;
import more.util.chart.ChartFactory;

import java.util.ArrayList;

public class ChartFactoryTest
{

    public static void main(String[] args)
    {
        testLine1();
        testLine2();

        System.out.println();

        testRow1();
        testRow2();
    }

    public static void testLine1()
    {
        ArrayList<String>    nameList = new ArrayList<>();
        ArrayList<ElecCurve> lineList = new ArrayList<>();
        nameList.add("line1");
        nameList.add("line2");

        Chart chart = ChartFactory.lineListToChart(nameList, lineList, ElecCurve.class);

        System.out.println(JSONObject.toJSONString(chart));
    }

    public static void testLine2()
    {
        ArrayList<String>    nameList = new ArrayList<>();
        ArrayList<ElecCurve> lineList = new ArrayList<>();
        lineList.add(new ElecCurve("1", "2", "3", "4", "5", "6", "7", "8", "9"));
        lineList.add(new ElecCurve("1", "2", null, "4", "5", null, "7", "8", "9"));
        nameList.add("line1");
        nameList.add("line2");

        Chart chart = ChartFactory.lineListToChart(nameList, lineList, ElecCurve.class);

        System.out.println(JSONObject.toJSONString(chart));
    }

    public static void testRow1()
    {
        ArrayList<MaxMinLoad> rowList = new ArrayList<>();
        MaxMinLoad            load1   = new MaxMinLoad();
        MaxMinLoad            load2   = new MaxMinLoad();
        load1.setDate("2024-01-01");
        load2.setDate("2024-01-02");
        rowList.add(load1);
        rowList.add(load2);

        Chart chart = ChartFactory.rowListToChart(rowList, MaxMinLoad.class);

        System.out.println(JSONObject.toJSONString(chart));
    }

    public static void testRow2()
    {
        ArrayList<MaxMinLoad> rowList = new ArrayList<>();
        rowList.add(new MaxMinLoad("2024-01-01", "50", "30"));
        rowList.add(new MaxMinLoad("2024-01-02", "98", "15"));

        Chart chart = ChartFactory.rowListToChart(rowList, MaxMinLoad.class);

        System.out.println(JSONObject.toJSONString(chart));
    }
}
