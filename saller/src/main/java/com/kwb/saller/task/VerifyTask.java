package com.kwb.saller.task;

import com.kwb.saller.enums.ChanEnum;
import com.kwb.saller.service.VerificationOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class VerifyTask {

    @Autowired
    private VerificationOrderService verificationOrderService;


    @Scheduled(cron = "0/5 * * * * ? ")
    public void sayHello() {
        System.err.println("Hello");
    }

    //定时任务注解
    //cron表达式在线搜索配置
    @Scheduled(cron = "0 0 1，3，5 * * ？")
    public void mkVerificationFile() {
        Date yesterday = new Date(System.currentTimeMillis() - 24 * 64 * 60 * 1000);
        for (ChanEnum value : ChanEnum.values()) {
            verificationOrderService.mkVerificationFile(value.getChanId(),yesterday);
        }
    }

    @Scheduled(cron = "0 0 2,4 * * ?")
    public void verify() {
        Date yesterday = new Date(System.currentTimeMillis() - 24 * 64 * 60 * 1000);
    }
}
