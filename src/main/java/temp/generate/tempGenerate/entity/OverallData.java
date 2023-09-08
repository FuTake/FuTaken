package temp.generate.tempGenerate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;

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
@TableName("monthly_report_overall_data")
@ApiModel(value = "MonthlyReportOverallData对象", description = "")
public class OverallData implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("通用列名称")
    private String commonName;

    @ApiModelProperty("需求总量")
    private String demandTotal;

    @ApiModelProperty("已完成需求量")
    private String completed;

    @ApiModelProperty("已完成需求占比")
    private String completedRate;

    @ApiModelProperty("评估中需求量")
    private Integer evaluating;

    @ApiModelProperty("确认中需求量")
    private Integer confirming;

    @ApiModelProperty("开发中需求量")
    private Integer developing;

    @ApiModelProperty("测试中需求量")
    private Integer testing;

    @ApiModelProperty("发布中需求量")
    private Integer publishing;

    @ApiModelProperty("评估工时")
    private BigDecimal evaluateHours;

    @ApiModelProperty("交付工时")
    private BigDecimal deliveryHours;

    @ApiModelProperty("平均评估工时")
    private BigDecimal averageEvaluateHours;

    @ApiModelProperty("平均交付工时")
    private BigDecimal averageDeliveryHours;

    @ApiModelProperty("在途需求数")
    private String inTransit;

    @ApiModelProperty("在途需求数占比")
    private String inTransitRate;

    @ApiModelProperty("跨项目需求数")
    private String crossProject;

    @ApiModelProperty("单系统需求数")
    private String singleSystem;

    @ApiModelProperty("0 接收需求总体情况，1 总部需求交付情况，2 省分需求交付情况，3 分子公司需求交付情况，4 软研院需求情况，5 需求人员情况分析，6 需求拆分项目支撑情况分析")
    private Integer analysisType;

    @ApiModelProperty("账期")
    private String monthId;

    @ApiModelProperty("数据类型：0-本期值，1-累计值")
    private Integer valueType;

    /**
     * 在途需求量
     * @return
     */
    public Integer getOnGoingDemand(){
        return evaluating + confirming + developing + testing + publishing;
    }

    /**
     * 记录的唯一标识
     * @return
     */
    public String getKey(){
        return commonName + analysisType + valueType;
    }

    public String getCompletedRate() {
        return completedRate + "%";
    }

    public String getInTransitRate() {
        return inTransitRate + "%";
    }

//    public void setEvaluateHours(String evaluateHours) {
//        this.evaluateHours = new BigDecimal(evaluateHours);
//    }
//
//    public void setDeliveryHours(String deliveryHours) {
//        this.deliveryHours = new BigDecimal(deliveryHours);
//    }
//
//    public void setAverageEvaluateHours(String averageEvaluateHours) {
//        this.averageEvaluateHours = new BigDecimal(averageEvaluateHours);
//    }
//
//    public void setAverageDeliveryHours(String averageDeliveryHours) {
//        this.averageDeliveryHours = new BigDecimal(averageDeliveryHours);
//    }
}
