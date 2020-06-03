package com.ananops.system.controller;

import com.ananops.system.dto.UserRegisterDto;
import com.ananops.system.service.ISysUserService;
import com.ananops.wrapper.WrapMapper;
import com.ananops.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created By ChengHao On 2020/6/2
 */
@RestController
@RequestMapping("/auth")
@Api("Web-AuthRestController")
public class AuthRestController {
    @Resource
    ISysUserService sysUserService;
    /**
     * 注册用户.
     *
     * @param user the user
     *
     * @return the wrapper
     */
    @PostMapping("/register")
    @ApiOperation(value = "注册用户")
    public Wrapper registerUser(UserRegisterDto user) {
        sysUserService.register(user);
        return WrapMapper.ok();
    }
}
