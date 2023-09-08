package temp.generate.tempGenerate.helper;

import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.deepoove.poi.util.TableTools;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;

/**
 * 动态表格生成策略
 * P27
 */
public class CustomTablePolicy extends DynamicTableRenderPolicy {
    @Override
    public void render(XWPFTable table, Object data) throws Exception {
        if (null == data) return;
        List<List<String>> detailData = (List<List<String>>) data;

        if (null != detailData) {
            table.removeRow(1);
            // 循环插入行
            for (int i = detailData.size() - 1; i >= 0; i--) {
                // 根据数据长度创建对应行数
                XWPFTableRow insertNewTableRow = table.insertNewTableRow(1);
                // 循环插入行列确定的单元格
                for (int j = 0; j < 5; j++) {
                    //根据列的数量创建对应单元格, 并添加值
                    XWPFTableCell cell = insertNewTableRow.createCell();
                    cell.setText(detailData.get(i).get(j));
                }
            }
            // 总计 单元格合并
            TableTools.mergeCellsHorizonal(table, 1, 0, 1);
            // 分类 列单元格合并
            // 初始化参数
            int startCol = 1;
            // 前2行固定 分类、总计，所以从第3行开始检测合并
            String classifyName = detailData.get(startCol).get(0);
            for( int endCol = 2; endCol < detailData.size(); endCol++){
                // 获取第 endCol 行第0列的数据
                String tempClassifyName = detailData.get(endCol).get(0);
                System.out.println(classifyName + " - " + tempClassifyName + " - " + startCol + " - " + endCol);
                if (classifyName.equals(tempClassifyName)){
                    // 解决最后一行的问题
                    if(endCol + 1== detailData.size()){
                        // 实际合并行数 需要startCol、endCol加上固定的首行1
                        TableTools.mergeCellsVertically(table, 0, startCol + 1, endCol + 1);
                        break;
                    }else{
                        continue;
                    }
                }
                if(endCol > startCol + 1){
                    // 实际合并行数 需要startCol加上固定的首行1，而endCol已经+1所以这里不需要改变
                    TableTools.mergeCellsVertically(table, 0, startCol + 1, endCol);
                }
                classifyName = tempClassifyName;
                startCol = endCol;
            }
        }
    }
}
