package com.ccc.admin.dto;

public class AdminMemberListDTO {

    private int userNumber;
    private String userId;
    private String userName;
    private String userPhone;
    private String userJoinDate;
    private String jobResultStatus;

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserJoinDate() {
        return userJoinDate;
    }

    public void setUserJoinDate(String userJoinDate) {
        this.userJoinDate = userJoinDate;
    }

    public String getJobResultStatus() {
        return jobResultStatus;
    }

    public void setJobResultStatus(String jobResultStatus) {
        this.jobResultStatus = jobResultStatus;
    }

	@Override
	public String toString() {
		return "AdminMemberListDTO [userNumber=" + userNumber + ", userId=" + userId + ", userName=" + userName
				+ ", userPhone=" + userPhone + ", userJoinDate=" + userJoinDate + ", jobResultStatus="
				+ jobResultStatus + "]";
	}
}