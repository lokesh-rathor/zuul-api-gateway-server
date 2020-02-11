package com.santander.zuulapigatewayserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.santander.zuulapigatewayserver.model.Authentication;

@Repository
public interface AuthenticationRepository extends JpaRepository<Authentication, Integer>{
	
	public Authentication findByEmail(@Param(value = "email") String email);
	

}