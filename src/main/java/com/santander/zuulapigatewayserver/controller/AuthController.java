package com.santander.zuulapigatewayserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.santander.zuulapigatewayserver.dto.AuthenticationRequest;
import com.santander.zuulapigatewayserver.dto.AuthenticationResponse;
import com.santander.zuulapigatewayserver.service.AuthenticationService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthController {

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private AuthenticationService authenticationService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest user) throws Exception {

		final AuthenticationResponse userDetails = authenticationService.logIn(user);
		logger.info("{}", "Inside authenticate");
		return new ResponseEntity<>(userDetails, HttpStatus.OK);
	}
	
	

}
