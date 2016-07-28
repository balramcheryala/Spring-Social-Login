package com.bridgelabz.ipl.exception;

import org.springframework.security.core.AuthenticationException;

public class UserAlreadyExistAuthenticationException extends AuthenticationException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4965959220936960984L;

	public UserAlreadyExistAuthenticationException(final String msg) {
        super(msg);
        System.out.println("IPL Exception User Alredy Exsist result:"+msg);
    }

}
