package com.kwb.manage.error;

import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class MyErrorController extends BasicErrorController {
    public MyErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties, List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorProperties, errorViewResolvers);
    }
    //设计自定义错误页面
    //需要异常描述
    /*
    {
        "timestamp": "2018-07-25 11:58:37",
            "status": 500,
            "error": "Internal Server Error",
            "exception": "java.lang.IllegalArgumentException",
            "message": "Paramary Key ： The num of Product can't be empty",
            "path": "/manager/products"
    }
    */
    @Override
    protected Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        Map<String, Object> attrs = super.getErrorAttributes(request, includeStackTrace);
        attrs.remove("timestamp");
        attrs.remove("error");
        attrs.remove("exception");
        attrs.remove("path");
        attrs.remove("status");
        ErrorEnum errorEnum = ErrorEnum.getByCode(attrs.get("message").toString());
        attrs.put("message",errorEnum.getMessage());
        attrs.put("code", errorEnum.getCode());
        attrs.put("cantry",errorEnum.isCantry());
        return attrs;
    }
}
