package com.example.demo.model.users;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
public class AppUser implements UserDetails {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
	private boolean isAccountNonExpired;
	private boolean isAccountNonLocked;
	private boolean isCredentialsNonExpired;
	private boolean isEnabled;
	@ElementCollection(fetch = FetchType.EAGER)
    private final Set<SimpleGrantedAuthority> roles=new HashSet<>();

	public AppUser() {
		super();
	}

	public AppUser(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.isAccountNonExpired = true;
		this.isAccountNonLocked = true;
		this.isCredentialsNonExpired = true;
		this.isEnabled = true;
	}

	public Long getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.isEnabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	public void addRole(String role){
		roles.add(new SimpleGrantedAuthority(role));
	}


	public User parseToUser() {
		return (User) User.builder()
				.username(this.username)
				.password(this.password)
				.roles(this.roles.stream().findFirst().get().getAuthority())
				.accountExpired(!this.isAccountNonExpired)
				.accountLocked(!this.isAccountNonLocked)
				.credentialsExpired(!this.isCredentialsNonExpired)
				.build();
	}
}
