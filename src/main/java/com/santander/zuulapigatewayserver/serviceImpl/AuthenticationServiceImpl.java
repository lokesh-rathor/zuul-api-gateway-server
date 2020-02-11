package com.santander.zuulapigatewayserver.serviceImpl;

import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.santander.zuulapigatewayserver.dto.AuthenticationRequest;
import com.santander.zuulapigatewayserver.dto.AuthenticationResponse;
import com.santander.zuulapigatewayserver.model.Authentication;
import com.santander.zuulapigatewayserver.repository.AuthenticationRepository;
import com.santander.zuulapigatewayserver.service.AuthenticationService;
import com.santander.zuulapigatewayserver.util.CaptchaUtil;
import com.santander.zuulapigatewayserver.util.JwtUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService, UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

	@Autowired
	AuthenticationRepository authenticationRepository;

	@Autowired
	private CaptchaUtil captchaUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Authentication user = authenticationRepository.findByEmail(email);

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				new ArrayList<>());
	}

	@Override
	public AuthenticationResponse logIn(final AuthenticationRequest authenticationRequest) throws Exception {
		System.out.println("captcha : " + authenticationRequest.getRecaptchaResponse());
		boolean captchaVerified = captchaUtil.verify(authenticationRequest.getRecaptchaResponse());

		if (!captchaVerified) {
			throw new Exception("Captcha invalid!!!");
		}

		final AuthenticationResponse loginResponse = new AuthenticationResponse();
		loginResponse.setMessage("An unknown error occured!");
		Authentication userRegistrationData = null;

		final Optional<Authentication> logIn = Optional
				.ofNullable(authenticationRepository.findByEmail(authenticationRequest.getEmail().toLowerCase()));

		if (logIn.isPresent()) { // if login is present then it will return true
			userRegistrationData = logIn.get();

			try {
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
						authenticationRequest.getEmail(), authenticationRequest.getPwd()));

				loginResponse.setUserId(userRegistrationData.getUserId());
				loginResponse.setEmail(userRegistrationData.getEmail().toLowerCase());
				loginResponse.setFirstName(userRegistrationData.getFirstName());
				loginResponse.setLastName(userRegistrationData.getLastName());
				loginResponse.setMessage("User is authenticated");
			} catch (BadCredentialsException e) {
				throw new Exception("Incorrect username or password", e);
			}

		} else {
			loginResponse.setMessage("Email id doesn't match!");
			// throw new InvalidInputException("Email id doesn't match!");
			logger.info("Email id doesn't match! : {}");
		}

		final String jwt = jwtTokenUtil.generateToken(loginResponse);
		loginResponse.setJwt(jwt);

		return loginResponse;
	}

}
