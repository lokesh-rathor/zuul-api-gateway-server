package com.santander.zuulapigatewayserver.service;

import com.santander.zuulapigatewayserver.dto.AuthenticationRequest;
import com.santander.zuulapigatewayserver.dto.AuthenticationResponse;

public interface AuthenticationService {
	
	public AuthenticationResponse logIn(AuthenticationRequest loginDto) throws Exception ;
	
}
