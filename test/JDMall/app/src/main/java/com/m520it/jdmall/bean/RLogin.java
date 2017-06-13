package com.m520it.jdmall.bean;

/**
 * @author 杨飞
 * @time 2016/9/1  20:05
 * @desc ${TODD}
 */
public class RLogin {
    private long id;
    private String userIcon;// 截取的字符串
    private int userLevel;
    private String userName;
    private int waitPayCount;
    private int waitReceiveCount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getWaitPayCount() {
        return waitPayCount;
    }

    public void setWaitPayCount(int waitPayCount) {
        this.waitPayCount = waitPayCount;
    }

    public int getWaitReceiveCount() {
        return waitReceiveCount;
    }

    public void setWaitReceiveCount(int waitReceiveCount) {
        this.waitReceiveCount = waitReceiveCount;
    }

    @Override
    public String toString() {
        return "RLogin{" +
                "id=" + id +
                ", userIcon='" + userIcon + '\'' +
                ", userLevel=" + userLevel +
                ", userName='" + userName + '\'' +
                ", waitPayCount=" + waitPayCount +
                ", waitReceiveCount=" + waitReceiveCount +
                '}';
    }
}
