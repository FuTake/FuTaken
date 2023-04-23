package poitest.entity.word;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RowObject {
    private int month;
    private int totalDemand;
    private int completeDemand;
    private BigDecimal completeRatio;
    private int checking;
    private int confirming;
    private int developping;
    private int testing;
    private int publishing;
}

