package com.example.demo.model.users;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class UserWithToken {
	
	private String username;
	private Set<SimpleGrantedAuthority> roles=new HashSet<>();
	HashMap<String, String> tokens=new HashMap<>();
	public UserWithToken(String username, Set<SimpleGrantedAuthority> roles, HashMap<String, String> tokens) {
		super();
		this.username = username;
		this.roles = roles;
		this.tokens = tokens;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Set<SimpleGrantedAuthority> getRoles() {
		return roles;
	}
	public void setRoles(Set<SimpleGrantedAuthority> roles) {
		this.roles = roles;
	}
	public HashMap<String, String> getTokens() {
		return tokens;
	}
	public void setTokens(HashMap<String, String> tokens) {
		this.tokens = tokens;
	}
	@Override
	public String toString() {
		return "UserWithToken [username=" + username + ", roles=" + roles + ", tokens=" + tokens + "]";
	}
	
}
