package temp.generate.tempGenerate.utils;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.Map;

public class TemplateRun {
    private static String path = "C:\\Temp\\统一需求支撑月度分析报告模板.docx";
    private static String diagramPath = "C:\\Temp\\diagram.docx";
    private static String destPath = "C:\\Temp\\output.docx";
    private static Map<String, Object> data = new HashMap<>();

    public static void main(String[] args) throws IOException {
        LocalDate localDate = LocalDate.now();
        FileOutputStream outputStream = new FileOutputStream(destPath);

        data.put("currentYear", localDate.getMonth().getValue() == 1 ? localDate.minusYears(1).getYear() : localDate.getYear());
        data.put("currentMonth", localDate.getMonth().getValue());
        data.put("lastMonth", localDate.now().getMonth().minus(1).getValue());
        data.put("lastDay", localDate.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth());

        XWPFTemplate template = XWPFTemplate.compile(path).render(
                new HashMap<String, Object>() {{

                }}
        );
        template.writeAndClose(outputStream);
    }

    private static Configure getConfigure(){
        return null;
    }
}
