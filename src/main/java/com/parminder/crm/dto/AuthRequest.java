package com.parminder.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @NoArgsConstructor @ToString @AllArgsConstructor
public class AuthRequest {

	String username;
	
	String password;
	
}
