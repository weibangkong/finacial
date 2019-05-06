package com.kwb.saller.enums;

public enum ChanEnum {

    ABC("kwb","Weibang Kong","D:\\verification");

    private String chanId;
    private String chanName;
    private String ftpPath,ftpUser, ftpPWD;
    private String rootDir;

    ChanEnum(String chanId, String chanName, String rootDir) {
        this.chanId = chanId;
        this.chanName = chanName;
        this.rootDir = rootDir;
    }

    ChanEnum(String chanId, String chanName, String ftpPath, String ftpUser, String ftpPWD, String rootDir) {
        this.chanId = chanId;
        this.chanName = chanName;
        this.ftpPath = ftpPath;
        this.ftpUser = ftpUser;
        this.ftpPWD = ftpPWD;
        this.rootDir = rootDir;
    }

    public String getChanId() {
        return chanId;
    }

    public void setChanId(String chanId) {
        this.chanId = chanId;
    }

    public String getChanName() {
        return chanName;
    }

    public void setChanName(String chanName) {
        this.chanName = chanName;
    }

    public String getRootDir() {
        return rootDir;
    }

    public void setRootDir(String rootDir) {
        this.rootDir = rootDir;
    }

    /**
     * 根据渠道编号渠道配置
     * @param chanId
     * @return
     */
    public static ChanEnum getByChanId(String chanId) {
        for (ChanEnum chanEnum : ChanEnum.values()) {
            if (chanEnum.getChanId().equals(chanId)) {
                return chanEnum;
            }
        }
        return null;
    }
}
