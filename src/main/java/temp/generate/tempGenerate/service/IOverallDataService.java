package temp.generate.tempGenerate.service;

import temp.generate.tempGenerate.entity.OverallData;
import com.baomidou.mybatisplus.extension.service.IService;
import temp.generate.tempGenerate.entity.WordMap;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhg
 * @since 2023-03-27
 */
public interface IOverallDataService extends IService<OverallData> {

    List<OverallData> currentYearReportData(WordMap wordMap, String analysisType, String valueType);
}
