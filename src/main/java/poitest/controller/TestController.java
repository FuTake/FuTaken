package poitest.controller;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.*;
import com.deepoove.poi.plugin.comment.CommentRenderData;
import com.deepoove.poi.plugin.comment.CommentRenderPolicy;
import com.deepoove.poi.plugin.comment.Comments;
import com.deepoove.poi.plugin.table.LoopColumnTableRenderPolicy;
import com.deepoove.poi.plugin.table.LoopRowTableRenderPolicy;
import org.apache.poi.util.LocaleUtil;
import org.junit.Test;
import poitest.entity.word.RowObject;
import poitest.utils.CustomTablePolicy;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class TestController {
    public static String destPath = "C:\\Users\\a1557\\Desktop\\demo.docx";
    public static Map<String, Object> bindMap = new HashMap<>();
    public static InputStream resources = TestController.class.getClassLoader().getResourceAsStream("work.docx");
    public static InputStream sub = TestController.class.getClassLoader().getResourceAsStream("sub.docx");


    // 替换变量 ok
    @Test
    public void replaceVariables() throws IOException {
        Map<String, Object> bindMap = new HashMap<>();
        bindMap.put("test", "poi-tl变量替换测试");
        bindMap.put("author", new TextRenderData("00FF00", "Sayi"));
        bindMap.put("link", new HyperlinkTextRenderData("website", "http://deepoove.com"));
        write(bindMap);
    }

    // 替换多系列图表 ok
    // 生成图表样式取决于模板中的样式
    @Test
    public void replaceMultiSeriesDiagram() throws IOException {
        ChartMultiSeriesRenderData chart = Charts
                .ofMultiSeries("多系列图表", new String[] { "中文", "英文" })
                .addSeries("国家", new Double[] { 15.0, 6.0 })
                .addSeries("语言", new Double[] { 223.0, 119.0 })
                .addSeries("test", new Double[] {164.4, 212.5})
                .create();
        Map<String, Object> bindMap = new HashMap<>();
        bindMap.put("diagram", chart);
        write(bindMap);
    }

    // 替换单系列图表 ok
    // 生成图表样式取决于模板中的样式
    @Test
    public void replaceSingleSeriesDiagram() throws IOException {
        ChartSingleSeriesRenderData pie = Charts
                .ofSingleSeries("单系列图表", new String[] { "北京", "上海" })
                .series("countries", new Integer[] { 560, 440 })
                .create();
        bindMap.put("pie", pie);
        write(bindMap);
    }

    // 替换组合图表 ok
    @Test
    public void replaceCombineDiagram() throws IOException {
        ChartMultiSeriesRenderData comb = Charts
                .ofComboSeries("组合图表", new String[] { "中文", "English", "日文" })
                .addBarSeries("国家", new Double[] { 15.0, 6.0, 12.0})
                .addBarSeries("人数", new Double[] { 223.0, 119.0, 150.0})
                .addLineSeries("年轻人占比", new Double[] { 323.0, 89.0, 213.3}).create();
        bindMap.put("combChart", comb);
        write(bindMap);
    }


    @Test
    // 循环行表格
    public void dynamicRow() throws IOException {
        List<RowObject> rowObjects = new ArrayList<>();
        for(int i=0; i<5; i++) {
            RowObject build = RowObject.builder()
                    .checking(i)
                    .completeDemand(i)
                    .completeRatio(new BigDecimal(i).divide(BigDecimal.valueOf(2)).subtract(BigDecimal.valueOf(2)))
                    .confirming(i)
                    .developping(i)
                    .month(i)
                    .testing(i)
                    .publishing(i)
                    .totalDemand(i)
                    .build();
            rowObjects.add(build);
        }
        bindMap.put("excel1", rowObjects);
        LoopRowTableRenderPolicy  policy = new LoopRowTableRenderPolicy ();
        Configure config = Configure.builder()
                .bind("excel1", policy)
                .build();
        write(bindMap, config);
    }

    // 循环列表格
    @Test
    public void dynamicColumn() throws IOException {
        List<RowObject> rowObjects = new ArrayList<>();
        for(int i=0; i<5; i++) {
            RowObject build = RowObject.builder()
                    .checking(i)
                    .completeDemand(i)
                    .completeRatio(new BigDecimal(i).divide(BigDecimal.valueOf(2)).subtract(BigDecimal.valueOf(2)))
                    .confirming(i)
                    .developping(i)
                    .month(i)
                    .testing(i)
                    .publishing(i)
                    .totalDemand(i)
                    .build();
            rowObjects.add(build);
        }
        bindMap.put("excel2", rowObjects);
        LoopColumnTableRenderPolicy policy = new LoopColumnTableRenderPolicy();
        Configure config = Configure.builder()
                .bind("excel2", policy)
                .build();
        write(bindMap, config);
    }

    // 自定义表格 ok
    @Test
    public void dynamicExcel2() throws IOException {
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
        bindMap.put("classify", rowData);
        Configure config = Configure.builder()
                .bind("classify", new CustomTablePolicy())
                .build();
        write(bindMap, config);
    }

    // 嵌套 ok
    @Test
    public void replaceNested() throws IOException {
        List<RowObject> rows = new ArrayList<>();
        rows.add(RowObject.builder().month(1).totalDemand(10).completeDemand(100).build());
        rows.add(RowObject.builder().month(2).totalDemand(20).completeDemand(200).build());
        rows.add(RowObject.builder().month(3).totalDemand(30).completeDemand(300).build());
        bindMap.put("nested", Includes.ofStream(sub).setRenderModel(rows).create());
        write(bindMap);
    }

    @Test
    // 批注
    public void comment() throws IOException {
        CommentRenderData comment = Comments.of("批注功能测试")
                .signature("zhg", "s", LocaleUtil.getLocaleCalendar())
                .comment("批注功能演示")
                .create();
        bindMap.put("comment", comment);
        Configure config = Configure.builder().bind("comment", new CommentRenderPolicy()).build();
        write(bindMap, config);
    }

    public void write(Map<String, Object> bindMap, Configure config) throws IOException {
        // 可以使用流的方式
        XWPFTemplate template = XWPFTemplate.compile(resources,config).render(bindMap);
        template.writeAndClose(new FileOutputStream(destPath));
        /*
            //也可以使用File对象
            XWPFTemplate template1 = XWPFTemplate.compile(new File(path)).render(bindMap);
            template1.writeAndClose(new FileOutputStream(destPath));
         */
    }

    public void write(Map<String, Object> bindMap) throws IOException {
        write(bindMap, Configure.createDefault());
    }
}
