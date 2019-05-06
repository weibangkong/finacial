package com.kwb.saller.aop;

import com.kwb.saller.service.SignService;
import com.kwb.util.common.RSAUtil;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.print.DocFlavor;

@Component
@Aspect
@ComponentScan
public class SignAop {

    /**
     * 一般authId，publickey会放到数据库中或者配置文件中
     */

    @Autowired
    private SignService signService;

    /**
     * 验签Aop
     * @param authId
     * @param sign
     * @param text
     */
    @Before(value = "execution(* com.kwb.saller.controller.*.*(..)) && args(authId,sign,text,..)")
    public void verifySign(String authId, String sign, String text) {
        String publicKey = signService.getPublicKey(authId);
        try {
            Assert.isTrue(RSAUtil.verify(text.getBytes(), publicKey, sign), "验签失败");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After(value = "execution(* com.kwb.saller.controller.*.*(..))")
    public void verifySign2() {
        System.err.println("-------------------- After-Advice -------------------");
    }

    @AfterThrowing(value="execution(* com.kwb.saller.controller.*.*(..))")
    public void verifySign3(){
        System.err.println("-------------------- Throw-Advice -------------------");
    }

    @AfterReturning("execution(* com.kwb.saller.controller.*.*(..))")
    public void verifySign4() {

    }
}
