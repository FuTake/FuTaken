package temp.generate.tempGenerate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import temp.generate.tempGenerate.common.constants.Constant;
import temp.generate.tempGenerate.entity.OverallData;
import temp.generate.tempGenerate.entity.WordMap;
import temp.generate.tempGenerate.mapper.MonthlyReportOverallDataMapper;
import temp.generate.tempGenerate.service.IOverallDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhg
 * @since 2023-03-27
 */
@Service
public class OverallDataServiceImpl extends ServiceImpl<MonthlyReportOverallDataMapper, OverallData> implements IOverallDataService {

    @Override
    public List<OverallData> currentYearReportData(WordMap wordMap, String analysisType, String valueType) {
        QueryWrapper<OverallData> wrapper = new QueryWrapper<>();
        wrapper.ge(Constant.MONTH_ID, wordMap.getCurrentYearFirstMonth());
        // 确认一张表的数据
        wrapper.eq(Constant.ANALYSIS_TYPE, 0);
        wrapper.eq(Constant.VALUE_TYPE, 0);
        return baseMapper.selectList(wrapper);
    }

}
