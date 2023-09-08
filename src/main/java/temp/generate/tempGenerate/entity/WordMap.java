package temp.generate.tempGenerate.entity;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.*;
import com.deepoove.poi.plugin.table.LoopRowTableRenderPolicy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import temp.generate.tempGenerate.common.constants.Constant;
import temp.generate.tempGenerate.helper.CustomTablePolicy;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@AllArgsConstructor
@Builder
@Data
public class WordMap {

    /**
     * 模板路径
     */
    private String templatePath = "C:\\Temp\\统一需求支撑月度分析报告0327.docx";
    private String destPath = "C:\\Temp\\统一需求支撑月度分析报告0327output.docx";
    /**
     * 参数绑定map
     */
    private static final ConcurrentHashMap<String, Object> bindMap = new ConcurrentHashMap<>();
    /**
     * 当前年月日信息
     */
    private Integer currentYear;
    private Integer currentMonth;
    private Integer lastMonth;
    private Integer lastDayofMonth;
    private LocalDate localDate;
    /**
     * 生成报告月份
     */
    private String monthId;


    public WordMap(String monthId){
        this.monthId = monthId;
        this.localDate = LocalDate.now();
        this.currentYear = localDate.getMonth().getValue() == 1 ? localDate.minusYears(1).getYear() : localDate.getYear();
        this.currentMonth = localDate.getMonth().getValue();
        this.lastMonth = localDate.now().getMonth().minus(1).getValue();
        this.lastDayofMonth = localDate.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();

        //当前年
        bindMap.put("currentYear", this.currentYear);
        // 当前月
        bindMap.put("currentMonth", this.currentMonth);
        // 上个月
        bindMap.put("lastMonth", this.lastMonth);
        // 上个月最后一天
        bindMap.put("lastDay", this.lastDayofMonth);
    }

    /**
     * 获取当年1月时间
     */
    public String getCurrentYearFirstMonth(){
        return currentYear+"01";
    }

    public String getMonthId(Integer monthId){
        return LocalDate.of(currentYear, monthId, 1).format(DateTimeFormatter.ofPattern("yyyyMM"));
    }

    /**
     * bindMap插入数据
     */
    public void putBindMap(String name, Object value){
        this.bindMap.put(name, value);
    }

    public Map getBindMap(){
        return bindMap;
    }

    public void export() throws IOException {
        FileOutputStream outputStream = new FileOutputStream(destPath);
        XWPFTemplate template = XWPFTemplate.compile(templatePath, getConfigure()).render(bindMap);
        template.writeAndClose(outputStream);
    }

    /**
     * 指定表格生成策略绑定
     * @return
     */
    public Configure getConfigure(){
        LoopRowTableRenderPolicy policy = new LoopRowTableRenderPolicy();

        Configure config = Configure.builder()
                .bind(Constant.DELIVERY1, policy)
                .build();
        return config;
    }

    /**
     * 生成柱状图
     * 已验证
     * @param histogram
     * @return
     */
    public ChartMultiSeriesRenderData histogramGenerate(MonthlyReportHistogram histogram) throws Exception{
        if(!histogram.check()){
            throw new Exception("图表数据不全");
        }
        ChartMultiSeriesRenderData data = new ChartMultiSeriesRenderData();
        data.setChartTitle(histogram.getHistogramTitle());
        data.setCategories(histogram.getCategories());
        data.setSeriesDatas(fillData(histogram));
        return data;
    }

    /**
     * 生成柱状图
     * 已验证
     * @param piechart
     * @return
     * @throws IOException
     */
    public ChartSingleSeriesRenderData pieChartGenerate(MonthlyReportHistogram piechart) throws IOException {
        ChartSingleSeriesRenderData data = new ChartSingleSeriesRenderData();
        data.setChartTitle(piechart.getHistogramTitle());
        data.setCategories(piechart.getCategories());
        data.setSeriesData(fillData(piechart).get(0));
        return data;
    }

    /**
     * 填充图表数据
     * @param data
     * @return
     */
    private List<SeriesRenderData> fillData(MonthlyReportHistogram data){
        List<SeriesRenderData> seriesRenderDatas = new ArrayList<>();
        Map<String, Number[]> values = data.getValues();
        for (String name : values.keySet()) {
            SeriesRenderData seriesRenderData = new SeriesRenderData(name, values.get(name));
            seriesRenderDatas.add(seriesRenderData);
        }
        return seriesRenderDatas;
    }
}
