package com.ananops.system.util;

import com.ananops.common.utils.security.Md5Utils;
import com.ananops.common.utils.security.Md5Utils4BCryptEncoder;
import com.ananops.system.domain.SysUser;

public class PasswordUtil
{
    public static boolean matches(SysUser user, String newPassword)
    {
        return user.getPassword().equals(encryptPassword(user.getLoginName(), newPassword, user.getSalt()));
    }

    public static String encryptPassword(String username, String password, String salt)
    {
        return Md5Utils.hash(username + password + salt);
    }

    //原来的ananops的加密
    public static boolean matches(CharSequence rawPassword, String encodedPassword){
        return Md5Utils4BCryptEncoder.matches(rawPassword,encodedPassword);
    }

    public static String encryptPassword(String password){
        return Md5Utils4BCryptEncoder.encrypt(password);
    }
}