package com.kwb.manage.error;

/**
 * 错误种类
 */
public enum ErrorEnum {
    ID_NOT_NULL("F001","编号不能为空",false),
    UNKONW("999","未知异常",false);
    private String code;
    private String message;
    private boolean cantry;

    ErrorEnum(String code, String message, boolean cantry) {
        this.code = code;
        this.message = message;
        this.cantry = cantry;
    }

    public static  ErrorEnum getByCode(String code) {
        for (ErrorEnum errorEnum : ErrorEnum.values()) {
            if(errorEnum.code.equals(code)){
                return errorEnum;
            }
        }
        return UNKONW;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isCantry() {
        return cantry;
    }
}
