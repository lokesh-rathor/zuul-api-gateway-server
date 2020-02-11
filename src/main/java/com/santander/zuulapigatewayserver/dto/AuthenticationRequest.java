/**
 * 
 */
package com.santander.zuulapigatewayserver.dto;

import com.sun.istack.NotNull;

/**
 * @author Harvindar.Raghav
 *
 */
public class AuthenticationRequest {
	
	@NotNull
	String email;
	
	@NotNull
	String pwd;
	
	@NotNull
    private String recaptchaResponse;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRecaptchaResponse() {
		return recaptchaResponse;
	}

	public void setRecaptchaResponse(String recaptchaResponse) {
		this.recaptchaResponse = recaptchaResponse;
	}

}
