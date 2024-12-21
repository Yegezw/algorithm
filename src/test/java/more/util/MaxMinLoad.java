package more.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import more.util.chart.X;
import more.util.chart.Y;

/**
 * 最大最小负荷
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaxMinLoad
{

    @X
    private String date;

    @Y(description = "maxLoad")
    private String maxLoad;

    @Y(description = "minLoad")
    private String minLoad;
}
