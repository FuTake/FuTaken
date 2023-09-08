package temp.generate.tempGenerate.entity;

import com.deepoove.poi.data.ChartMultiSeriesRenderData;
import com.deepoove.poi.data.ChartSingleSeriesRenderData;
import com.deepoove.poi.data.RenderData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MonthlyReportHistogram {
    private String histogramTitle;
    private String[] categories;
    private Map<String, Number[]> values;

    public boolean check(){
        if(StringUtils.isNotBlank(histogramTitle) || categories != null || categories.length > 0 || values != null){
            return true;
        }
        return false;
    }
}
