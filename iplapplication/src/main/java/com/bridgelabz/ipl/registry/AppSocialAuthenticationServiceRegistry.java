package com.bridgelabz.ipl.registry;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.social.security.SocialAuthenticationServiceRegistry;
import org.springframework.social.security.provider.SocialAuthenticationService;

public class AppSocialAuthenticationServiceRegistry extends SocialAuthenticationServiceRegistry {

	private List<SocialAuthenticationService<?>> authenticationServices;

	public AppSocialAuthenticationServiceRegistry(final List<SocialAuthenticationService<?>> authenticationServices) {
		this.authenticationServices = authenticationServices;
	}

	// When the constructor is called, the bean is not yet initialized -
	// i.e. no dependencies are injected
	// @PostConstruct method the bean is fully initialized and you can use the
	// dependencies.
	@PostConstruct
	public void init() {
		if (authenticationServices != null) {
			for (SocialAuthenticationService authenticationService : authenticationServices) {
				System.out.println("AppSocial Authentication Service Registry" + authenticationService);
				super.addAuthenticationService(authenticationService);
			}
		}
	}

}
