package com.santander.zuulapigatewayserver.dto;

public class RecaptchaResponse {

	private boolean success;

	private String challengeTs;

	private String hostname;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getChallengeTs() {
		return challengeTs;
	}

	public void setChallengeTs(String challengeTs) {
		this.challengeTs = challengeTs;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	@Override
	public String toString() {
		return "RecaptchaResponse [success=" + success + ", challengeTs=" + challengeTs + ", hostname=" + hostname
				+ "]";
	}
	
}
