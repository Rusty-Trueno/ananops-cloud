package com.ananops.system.controller;

import com.ananops.common.core.controller.BaseController;
import com.ananops.common.core.domain.R;
import com.ananops.system.dto.UserRegisterDto;
import com.ananops.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created By ChengHao On 2020/6/3
 */
@RestController
@RequestMapping(value = "/auth")
@Api(value = "Web-AuthRestController")
public class AuthRestController extends BaseController {
    @Resource
    ISysUserService sysUserService;

    /**
     * 注册用户.
     *
     * @param user the user
     *
     * @return the wrapper
     */
    @PostMapping(value = "/register")
    @ApiOperation(httpMethod = "POST", value = "注册用户")
    public R registerUser(UserRegisterDto user) {
        sysUserService.register(user);
        return R.ok("注册成功");
    }
}
