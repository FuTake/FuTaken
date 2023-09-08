package temp.generate.tempGenerate.service;

import javax.servlet.http.HttpServletResponse;

public interface DynamicWordExportService {

    /**
     * 导出指定月份的分析文件
     * @param monthId
     * @param response
     * @return
     */
    String export(String monthId, HttpServletResponse response);

}
