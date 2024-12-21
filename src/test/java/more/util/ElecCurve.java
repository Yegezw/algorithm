package more.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import more.util.chart.Y;

/**
 * 用电曲线
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElecCurve
{

    @Y(description = "01:00")
    private String v1;

    @Y(description = "02:00")
    private String v2;

    @Y(description = "03:00")
    private String v3;

    @Y(description = "04:00")
    private String v4;

    @Y(description = "05:00")
    private String v5;

    @Y(description = "06:00")
    private String v6;

    @Y(description = "07:00")
    private String v7;

    @Y(description = "08:00")
    private String v8;

    @Y(description = "09:00")
    private String v9;
}
