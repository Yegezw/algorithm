package more.util.chart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 图表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chart
{

    /**
     * 横轴
     */
    private List<String> xList;

    /**
     * 折线 name -> dataList
     */
    private Map<String, List<String>> lineMap;

    public Chart(int xCount, int lineCount)
    {
        this.xList   = new ArrayList<>(xCount);
        this.lineMap = new HashMap<>((int) (lineCount / 0.75 + 1));
    }
}
