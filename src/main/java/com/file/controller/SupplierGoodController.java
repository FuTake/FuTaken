package com.file.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.file.service.SupplierGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * <p>
 * 供应商商品管理 前端控制器
 * </p>
 *
 * @author andy
 * @since 2022-10-25
 */
@Controller
@RequestMapping("/supplierGood")
public class SupplierGoodController {
    @Autowired
    private SupplierGoodService supplierGoodService;

    @PostConstruct
    public void test(){
        Map result = supplierGoodService.getMaps();
        System.out.println("result : " + JSONObject.toJSONString(result, SerializerFeature.PrettyFormat));
    }

}
