package temp.generate.tempGenerate.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import temp.generate.tempGenerate.common.constants.Constant;
import temp.generate.tempGenerate.entity.OverallData;
import temp.generate.tempGenerate.entity.WordMap;
import temp.generate.tempGenerate.service.DynamicWordExportService;
import temp.generate.tempGenerate.service.IDeliveryHoursAnalysisService;
import temp.generate.tempGenerate.service.IOverallDataService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DynamicWordExportServiceImpl implements DynamicWordExportService {

    @Resource
    IDeliveryHoursAnalysisService iDeliveryHoursAnalysisService;

    @Resource
    IOverallDataService iOverallDataService;

    @Override
    public String export(String monthId, HttpServletResponse response) {
        try {
//            InputStream inputStream = generateWord(monthId);
//            // 清空response
//            response.reset();
//            // 设置response的Header
//            response.setCharacterEncoding("UTF-8");
//            //Content-Disposition的作用：告知浏览器以何种方式显示响应返回的文件，用浏览器打开还是以附件的形式下载到本地保存
//            //attachment表示以附件方式下载 inline表示在线打开 "Content-Disposition: inline; filename=文件名.mp3"
//            // filename表示文件的默认名称，因为网络传输只支持URL编码的相关支付，因此需要将文件名URL编码后进行传输,前端收到后需要反编码才能获取到真正的名称
//            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("test", "UTF-8"));
//            // 告知浏览器文件的大小
//            response.addHeader("Content-Length", "0");
//            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
//            response.setContentType("application/octet-stream");
//            IOUtils.copy(inputStream, outputStream);
//            outputStream.flush();
            generateWord(monthId);
        }catch (Exception e) {
            log.error(e.getMessage(), e);
        }finally {

        }
        return "ok";
    }

    private InputStream generateWord(String monthId){
        try {
            WordMap wordMap = new WordMap(monthId);
            generate212(wordMap);
            wordMap.export();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void generate212(WordMap wordMap){
        Map bindMap = wordMap.getBindMap();
        String analysisType = "0";
        String valueType = "0";
        final List<OverallData> overallData = iOverallDataService.currentYearReportData(wordMap, analysisType, valueType);
        System.out.println(JSONObject.toJSONString(overallData, SerializerFeature.PrettyFormat));
    }

    /**
     * 生成 2.1.2 本年截止{{lastMonth}}月接收需求的完成情况 内容
     * @param wordMap
     */
    private void generate212test(WordMap wordMap){
        Map bindMap = wordMap.getBindMap();
        String analysisType = "0";
        String valueType = "0";
        final List<OverallData> overallData = iOverallDataService.currentYearReportData(wordMap, analysisType, valueType);
        //<commonNameAnalysisTypeValueType, 下标>，唯一键分组
        Map<String, Integer> monthlyReportOverallDataGroupByKey = overallData.stream().collect(Collectors.toMap(OverallData::getKey,
                value -> overallData.indexOf(value)));

        log.info("monthlyReportOverallDataGroupByKey: " + JSONObject.toJSONString(monthlyReportOverallDataGroupByKey));
        // 文字
        OverallData total = overallData.get(monthlyReportOverallDataGroupByKey.get("总计" + analysisType + valueType));

        //total: {"analysisType":0,"averageDeliveryHours":"19.05 ","averageEvaluateHours":"7.52 ","commonName":"总计","completed":"384","completedRate":"49.0%","confirming":46,"deliveryHours":"7,316.49 ","demandTotal":"783","developing":242,"evaluateHours":"5,890.21 ","evaluating":70,"id":3,"key":"总计00","monthId":"202302","onGoingDemand":399,"publishing":28,"testing":13,"valueType":0}
        log.info("total: " + JSONObject.toJSONString(total));
        bindMap.put("deliveryGive", total.getDemandTotal());
        bindMap.put("deliveryDemand", total.getCompleted());
        bindMap.put("receiveDeliveryRatio", total.getCompletedRate());
        Integer receiveOngoingDemand = total.getOnGoingDemand();
        bindMap.put("receiveOngoingDemand", receiveOngoingDemand);


        StringBuilder receiveDeliveryMonth = new StringBuilder("其中");
        StringBuilder receiveOngoingMonth = new StringBuilder("其中");
        for(int month = 1; month <= wordMap.getLastMonth(); month++){
            OverallData overallData1 = overallData.get(monthlyReportOverallDataGroupByKey.get(wordMap.getMonthId(month) + analysisType + valueType));
            receiveDeliveryMonth.append(month + "月交付" + overallData1.getCompleted() + "个");
            receiveOngoingMonth.append(month + "月交付" + overallData1.getOnGoingDemand() + "个");
            // 符号处理
            if(month != wordMap.getLastMonth()){
                receiveDeliveryMonth.append(",");
                receiveOngoingMonth.append(",");
            }
        }
        bindMap.put("receiveDeliveryMonth", receiveDeliveryMonth.toString());
        bindMap.put("receiveOngoingMonth", receiveOngoingMonth.toString());


        // 表格
        List<OverallData> monthlyReportOverallSortData = overallData.stream().sorted((value1, value2) -> {
            // 总计在前，然后月份升序排列
            if(value1.getCommonName().equals("总计")){
                return -1;
            }
            return Integer.parseInt(value1.getMonthId()) - Integer.parseInt(value2.getMonthId());
        }).collect(Collectors.toList());
        // 存入bindMap
        bindMap.put(Constant.DELIVERY1, monthlyReportOverallSortData);
    }

    /**
     * 生成 2.1.2.1 交付完成分析
     */
    private void generate2121(WordMap wordMap){
        Map bindMap = wordMap.getBindMap();

        //总部需求
        String analysisType = "1";
        String valueType = "0";
        final List<OverallData> overallData = iOverallDataService.currentYearReportData(wordMap, analysisType, valueType);
        //<commonNameAnalysisTypeValueType, 下标>，唯一键分组
        Map<String, Integer> monthlyReportOverallDataGroupByKey = overallData.stream().collect(Collectors.toMap(OverallData::getKey,
                value -> overallData.indexOf(value)));

        //文字
        OverallData total = overallData.get(monthlyReportOverallDataGroupByKey.get("小计" + analysisType + valueType));

        //total: {"analysisType":0,"averageDeliveryHours":"19.05 ","averageEvaluateHours":"7.52 ","commonName":"总计","completed":"384","completedRate":"49.0%","confirming":46,"deliveryHours":"7,316.49 ","demandTotal":"783","developing":242,"evaluateHours":"5,890.21 ","evaluating":70,"id":3,"key":"总计00","monthId":"202302","onGoingDemand":399,"publishing":28,"testing":13,"valueType":0}
        // TODO 表头数据待补充
        log.info("total: " + JSONObject.toJSONString(total));
        bindMap.put("completeHeadDeliveryDemand", total.getCompleted());
        BigDecimal completeHeadRatio = new BigDecimal(total.getCompleted()).divide(new BigDecimal((String)bindMap.get("deliveryDemand"))).multiply(BigDecimal.valueOf(100)).setScale(1, RoundingMode.HALF_UP);
        bindMap.put("completeHeadRatio", completeHeadRatio + "%");
        bindMap.put("completeProvinceDemand", null);

        Integer receiveOngoingDemand = total.getOnGoingDemand();
        bindMap.put("receiveOngoingDemand", receiveOngoingDemand);


        StringBuilder receiveDeliveryMonth = new StringBuilder("其中");
        StringBuilder receiveOngoingMonth = new StringBuilder("其中");
        for(int month = 1; month <= wordMap.getLastMonth(); month++){
            OverallData overallData1 = overallData.get(monthlyReportOverallDataGroupByKey.get(wordMap.getMonthId(month) + analysisType + valueType));
            receiveDeliveryMonth.append(month + "月交付" + overallData1.getCompleted() + "个");
            receiveOngoingMonth.append(month + "月交付" + overallData1.getOnGoingDemand() + "个");
            // 符号处理
            if(month != wordMap.getLastMonth()){
                receiveDeliveryMonth.append(",");
                receiveOngoingMonth.append(",");
            }
        }
        bindMap.put("receiveDeliveryMonth", receiveDeliveryMonth.toString());
        bindMap.put("receiveOngoingMonth", receiveOngoingMonth.toString());

        //表格
        List<OverallData> monthlyReportOverallSortData = overallData.stream().sorted((value1, value2) -> {
            // 总计置顶
            return substract(value1.getDemandTotal(), value2.getDemandTotal());
        }).collect(Collectors.toList());
        bindMap.put(Constant.DELIVERY2, monthlyReportOverallSortData);

        // 省份需求
        analysisType = "2";
        valueType = "0";
        final List<OverallData> monthlyProvinceReportOverallData = iOverallDataService.currentYearReportData(wordMap, analysisType, valueType);
        //<commonNameAnalysisTypeValueType, 下标>，唯一键分组
        Map<String, Integer> monthlyProvinceReportOverallDataGroupByKey = monthlyProvinceReportOverallData.stream().collect(Collectors.toMap(OverallData::getKey,
                value -> monthlyProvinceReportOverallData.indexOf(value)));
            //文字
        total = monthlyProvinceReportOverallData.get(monthlyProvinceReportOverallDataGroupByKey.get("小计" + analysisType + valueType));
        bindMap.put("completeProvinceReceiveDemand", total.getDemandTotal());
        bindMap.put("completeProvinceDeliveryDemand", total.getCompleted());
        List<String> completeGiveDemandProvinceTop3;

            //表格
        List<OverallData> monthlyProvinceReportOverallSortData = monthlyProvinceReportOverallData.stream().sorted((value1, value2) -> {
            // 总计置顶
            return substract(value1.getDemandTotal(), value2.getDemandTotal());
        }).collect(Collectors.toList());
        bindMap.put(Constant.DELIVERY3, monthlyProvinceReportOverallSortData);

        // 分子公司需求
        // 软研院需求
        // 需求人员效能分析
        // 需求拆分项目分析

    }

    private Integer substract(String value1, String value2){
        return Integer.parseInt(value1) - Integer.parseInt(value2);
    }

}
