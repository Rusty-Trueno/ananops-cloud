package com.ananops.imc.controller;

import com.ananops.common.core.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by rongshuai on 2020/5/15 17:50
 */
@RestController
@RequestMapping("test")
@Api("巡检测试")
public class ImcTestController extends BaseController {
    @ApiOperation(value = "hello ~", notes = "欢迎")
    @ApiImplicitParams({@ApiImplicitParam(name = "name", value = "名字", required = true)})
    @GetMapping("/hell")
    public String get(String name)
    {
        return "hello " + name;
    }
}
