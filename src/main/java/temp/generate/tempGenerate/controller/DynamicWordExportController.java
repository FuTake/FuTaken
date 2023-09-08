package temp.generate.tempGenerate.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import temp.generate.tempGenerate.service.DynamicWordExportService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhg
 * @since 2023-03-27
 */
@RestController
@RequestMapping("/dynamic")
public class DynamicWordExportController {

    @Resource
    public DynamicWordExportService dynamicWordExportService;

    @RequestMapping("/export")
    public String export(String monthId, HttpServletResponse response){
        dynamicWordExportService.export(monthId, response);
        return null;
    }

}
