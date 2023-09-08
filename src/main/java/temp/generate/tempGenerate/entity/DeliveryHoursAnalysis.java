package temp.generate.tempGenerate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhg
 * @since 2023-03-27
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("monthly_report_delivery_hours_analysis")
@ApiModel(value = "MonthlyReportDeliveryHoursAnalysis对象", description = "")
public class DeliveryHoursAnalysis implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("需求分类")
    private String demandType;

    @ApiModelProperty("数量")
    private String demandNum;

    @ApiModelProperty("平均交付时长")
    private String averageDeliveryTime;

    @ApiModelProperty("15天内")
    private String underFifteenDays;

    @ApiModelProperty("15天内占比")
    private String underFifteenDaysRate;

    @ApiModelProperty("15到30天内")
    private String fifteenDaysToThirtyDays;

    @ApiModelProperty("15到30天内占比")
    private String fifteenDaysToThirtyDaysRate;

    @ApiModelProperty("30天以上")
    private String overThirtyDays;

    @ApiModelProperty("30天以上占比")
    private String overThirtyDaysRate;

    @ApiModelProperty("账期")
    private String monthId;

    @ApiModelProperty("数据类型：0-本期值，1-累计值")
    private Integer valueType;


}
