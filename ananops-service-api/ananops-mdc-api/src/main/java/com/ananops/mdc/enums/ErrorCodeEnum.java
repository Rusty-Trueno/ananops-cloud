package com.ananops.mdc.enums;

/**
 * The class Error code enum.
 *
 * @author ananops.net @gmail.com
 */
public enum ErrorCodeEnum {

    MDC10021034(10021034,"字典项名称不能是空"),
    MDC10021035(10021035,"字典项编码不能是空"),
    MDC10021036(10021036,"字典项排序码不能是空"),
    MDC10021027(10021027,"字典项id不能是空"),
    MDC10021025(10021025,"不存在此字典项，dictItemId=%s"),
    RDC100000000(10000000, "新建字典子项失败，数据库操作异常"),
    RDC100000001(10000001, "更新字典子项失败，数据库操作异常"),

    MDC10021026(10021026,"字典库id不能是空"),
    MDC10021024(10021024,"不存在此字典库，dictId=%s"),
    MDC10021033(10021033,"字典库名称不能是空"),

    MDC10021037(10021037,"该动态表单模板不存在，templateId=%s"),
    MDC10021038(10021038,"该项目已绑定动态表单模板，templateId=%s"),
    ;
    private int code;
    private String msg;

    /**
     * Msg string.
     *
     * @return the string
     */
    public String msg() {
        return msg;
    }

    /**
     * Code int.
     *
     * @return the int
     */
    public int code() {
        return code;
    }

    ErrorCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * Gets enum.
     *
     * @param code the code
     *
     * @return the enum
     */
    public static ErrorCodeEnum getEnum(int code) {
        for (ErrorCodeEnum ele : ErrorCodeEnum.values()) {
            if (ele.code() == code) {
                return ele;
            }
        }
        return null;
    }
}

