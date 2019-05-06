package com.kwb.util.common;

/**
 遇到的问题：
 1.防火墙阻止
 2.代理软件限制,服务器需要通过代理访问外网，但FTP是内网直接可以访问的.（花费我一下午找该问题- -）
 3.解决中文目录问题
 支持JAR ：commons-net-3.6.jar
 **/
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class FTP {
    private static final Logger log = LoggerFactory.getLogger(FTP.class);
    public static boolean downFile(
            String url, //FTP服务器hostname
            int port,//FTP服务器端口
            String username, //FTP登录账号
            String password, //FTP登录密码
            String fileName,//要下载的文件名
            String localPath,//下载后保存到本地的路径
            String remotePath//FTP服务器上的相对路径
    ) {
        boolean success = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(url, port);
            //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            boolean login=ftp.login(username, password);//登录
            ftp.setControlEncoding("GBK");
            //设置环境
            FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
            conf.setServerLanguageCode("zh");
            ftp.configure(conf);
            reply = ftp.getReplyCode();
            log.error("remotePath:"+remotePath);
            System.out.println("login:"+login+"    reply:"+reply);
            log.error("login:"+login+"    reply:"+reply);
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return success;
            }
            boolean chg=ftp.changeWorkingDirectory(new String(remotePath.getBytes("GBK"),"ISO-8859-1"));//转移到FTP服务器目录
            log.error("remote path:" + ftp.printWorkingDirectory());
            System.out.println("changeWorkingDirectory:"+chg);
            if(chg){
                FTPFile[] fs = ftp.listFiles();
                System.out.println("files...."+fs.length);
                log.error("files...."+fs.length);
                for(FTPFile ff:fs){
                    if(ff.getName().equals(fileName)){
                        File localFile = new File(localPath+"/"+ff.getName());
                        OutputStream is = new FileOutputStream(localFile);
                        ftp.retrieveFile(ff.getName(), is);
                        is.close();
                    }
                }
                success = true;
            }else{
                success = false;
            }
            ftp.logout();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }

}

