package poitest.entity.word;
import lombok.Data;

import java.util.List;

@Data
public class ExcelObject {
    String tableName;
    List<String> titleName;
    List<List<String>> value;

    public String getTableName() {
        return tableName + " _ ";
    }
}

