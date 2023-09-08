package temp.generate.tempGenerate.utils;

import com.alibaba.fastjson.JSONObject;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.*;
import com.deepoove.poi.plugin.table.LoopRowTableRenderPolicy;
import poitest.entity.word.ExcelObject;
import poitest.entity.word.RowObject;
import temp.generate.tempGenerate.helper.CustomTablePolicy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;



public class WordAnalysisUtil {
    public static String path = "C:\\Temp\\work.docx";
    public static String diagramPath = "C:\\Temp\\diagram.docx";
    public static String destPath = "C:\\Temp\\output.docx";
    public static List<RowObject> rowObjects = new ArrayList<>();
    public static List<RowObject> rowObjects1 = new ArrayList<>();

    public static Configure getConfigure(){

        LoopRowTableRenderPolicy policy = new LoopRowTableRenderPolicy();

        Configure config = Configure.builder()
                .bind("这是一个测试1", policy)
                .bind("这是一个测试2", policy)
                .bind("classify", new CustomTablePolicy())
                .build();
        return config;

    }

    public static TableRenderData getExcel(ExcelObject excelObject){
        RowRenderData[] datas = new RowRenderData[excelObject.getValue().size() + 1];
        List<String> titles = excelObject.getTitleName();
        RowRenderData title = Rows.of(titles.toArray(new String[titles.size()])).center().create();
        datas[0] = title;
        for (int i = 1; i < excelObject.getValue().size(); i++) {
            List<String> values = excelObject.getValue().get(i);
            datas[i] = Rows.create(values.toArray(new String[values.size()]));
        }
        return Tables.create(datas);
    }

    public static void test() throws IOException {
        for(int i=0; i<5; i++){
            RowObject build = RowObject.builder()
                    .checking(i)
                    .avgCheckingTime(i)
                    .avgDeliveryTime(i)
                    .checkingTime(i)
                    .completeDemand(i)
                    .completeRatio(new BigDecimal(i).divide(BigDecimal.valueOf(2)).subtract(BigDecimal.valueOf(2)))
                    .confirming(i)
                    .deliveryTime(i)
                    .developping(i)
                    .month(i)
                    .testing(i)
                    .publishing(i)
                    .totalDemand(i)
                    .build();
            RowObject build2 = RowObject.builder()
                    .checking(i*2)
                    .avgCheckingTime(i*2)
                    .avgDeliveryTime(i*2)
                    .checkingTime(i*2)
                    .completeDemand(i*2)
                    .completeRatio(new BigDecimal(i).divide(BigDecimal.valueOf(2)).subtract(BigDecimal.valueOf(2)))
                    .confirming(i*2)
                    .deliveryTime(i*2)
                    .developping(i*2)
                    .month(i*2)
                    .testing(i*2)
                    .publishing(i*2)
                    .totalDemand(i*2)
                    .build();
            rowObjects.add(build);
            rowObjects1.add(build2);
        }
    }

    public static ChartSingleSeriesRenderData diagramGenerate() throws IOException {

        ChartSingleSeriesRenderData data = new ChartSingleSeriesRenderData();
        data.setChartTitle("Chart");
        data.setCategories(new String[] { "15天以内", "15到30天内", "30天以上" });
        data.setSeriesData(new SeriesRenderData("test", new Long[]{45l, 36l, 17l}));
        return data;

//        return Charts
//                .ofSingleSeries("Chart", new String[] { "15天以内", "15到30天内", "30天以上" })
//                .series("test", new Double[]{0.45, 0.36, 0.17})
//                .create();
    }

    public static ChartMultiSeriesRenderData multiDiagramGenerate(){
        ChartMultiSeriesRenderData data = new ChartMultiSeriesRenderData();
        data.setChartTitle("MultiDiagram");
        data.setCategories(new String[] { "平均交付时长"});
        List<SeriesRenderData> datas = new ArrayList<>();
        datas.add(new SeriesRenderData("取数类", new Double[] { 20.37 }));
        datas.add(new SeriesRenderData("采集能力类", new Double[] { 17.15 }));
        datas.add(new SeriesRenderData("算法标签类", new Double[] { 31.06 }));
        datas.add(new SeriesRenderData("经分固化类", new Double[] { 17.4 }));
        datas.add(new SeriesRenderData("应用类", new Double[] { 15.92 }));
        data.setSeriesDatas(datas);
        return data;

//        return Charts
//                .ofComboSeries("MultiDiagram", new String[] { "平均交付时长"})
//                .addBarSeries("取数类", new Double[] { 20.37 })
//                .addBarSeries("采集能力类", new Double[] { 17.15 })
//                .addBarSeries("算法标签类", new Double[] { 31.06 })
//                .addBarSeries("经分固化类", new Double[] { 17.4 })
//                .addBarSeries("应用类", new Double[] { 15.92 }).create();
    }
    public static List<List<String>> dynamicExcel(){
        List<List<String>> rowData = new ArrayList<>();
        List<String> cells = Arrays.asList("总计", "总计", "783", "399", "51.0%");
        rowData.add(cells);
        cells = Arrays.asList("总部", "总部", "123","321", "50%");
        rowData.add(cells);
        cells = Arrays.asList("省份", "广东", "123","321", "50%");
        rowData.add(cells);
        cells = Arrays.asList("省份", "上海", "123","321", "50%");
        rowData.add(cells);
        cells = Arrays.asList("省份", "深圳", "123","321", "50%");
        rowData.add(cells);
        cells = Arrays.asList("省份", "北京", "123","321", "50%");
        rowData.add(cells);
        cells = Arrays.asList("软研院", "联通软件研究院", "123","321", "50%");
        rowData.add(cells);
        cells = Arrays.asList("其他分公司", "联通在线信息科技有限公司", "123","321", "50%");
        rowData.add(cells);
        cells = Arrays.asList("其他分子公司", "联通华盛通信有限公司", "123","321", "50%");
        rowData.add(cells);
        return rowData;
    }
    public static List<List<String>> dynamicExcel2(){
        List<List<String>> rowData = new ArrayList<>();
        List<String> cells = Arrays.asList("总计", "总计", "783", "399", "51.0%");
        rowData.add(cells);
        cells = Arrays.asList("总部", "总部", "123","321", "50%");
        rowData.add(cells);
        cells = Arrays.asList("省份", "广东", "123","321", "50%");
        rowData.add(cells);
        cells = Arrays.asList("省份", "上海", "123","321", "50%");
        rowData.add(cells);
        cells = Arrays.asList("省份", "深圳", "123","321", "50%");
        rowData.add(cells);
        cells = Arrays.asList("省份", "北京", "123","321", "50%");
        rowData.add(cells);
        cells = Arrays.asList("软研院", "联通软件研究院", "123","321", "50%");
        rowData.add(cells);
        cells = Arrays.asList("其他分子公司", "联通在线信息科技有限公司", "123","321", "50%");
        rowData.add(cells);
        cells = Arrays.asList("其他分子公司", "联通华盛通信有限公司", "123","321", "50%");
        rowData.add(cells);
        return rowData;
    }

    public static void main(String[] args) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(destPath);
        test();
        ChartSingleSeriesRenderData chartSingleSeriesRenderData = diagramGenerate();
        ChartMultiSeriesRenderData chartMultiSeriesRenderData = multiDiagramGenerate();
        System.out.println("chartSingleSeriesRenderData - " + JSONObject.toJSONString(chartSingleSeriesRenderData));
        System.out.println("chartMultiSeriesRenderData - " + JSONObject.toJSONString(chartMultiSeriesRenderData));
        XWPFTemplate template = XWPFTemplate.compile(path, getConfigure()).render(
                new HashMap<String, Object>() {{
                    put("这是一个测试1", rowObjects);
                    put("这是一个测试2", rowObjects1);
                    put("diagram", chartSingleSeriesRenderData);
                    put("multiDiagram", chartMultiSeriesRenderData);
//                    put("classify", dynamicExcel());
                    put("classify", dynamicExcel2());
                    put("testtest", "这是一个测试");
                }}
        );
        template.writeAndClose(outputStream);
    }

    public static void diagramTest() throws IOException {
        ChartSingleSeriesRenderData pie = Charts
                .ofSingleSeries("ChartTitle", new String[] { "美国", "中国" })
                .series("countries", new Integer[] { 9826675, 9596961 })
                .create();
        XWPFTemplate template1 = XWPFTemplate.compile(diagramPath).render(
                new HashMap<String, Object>(){{
                    put("diagram", pie);
                }}
        );
        template1.writeAndClose(new FileOutputStream(destPath));
    }


}


