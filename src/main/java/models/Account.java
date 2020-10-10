package models;

import java.util.Date;

public class Account {

	private String nickName;
	private String email;
	private String password;
	private Date lastDateFollowFlow;
	private Boolean canFollowNow;

	public Account(String nickName, String email, String password) {
		this.nickName = nickName;
		this.email = email;
		this.password = password;
		this.lastDateFollowFlow = null;
		this.canFollowNow = true;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastDateFollowFlow() {
		return lastDateFollowFlow;
	}

	public void setLastDateFollowFlow(Date lastDateFollowFlow) {
		this.lastDateFollowFlow = lastDateFollowFlow;
	}

	public Boolean getCanFollowNow() {
		return canFollowNow;
	}

	public void setCanFollowNow(Boolean canFollowNow) {
		this.canFollowNow = canFollowNow;
	}

}
