/**
 * nasru - Jun 25, 2020
 * FeignErrorDecoder.java 
 */
package com.mypractice.microservices.userapi.util;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * @author nasru
 *
 */
@Component
public class FeignErrorDecoder implements ErrorDecoder {

	@Override
	public Exception decode(String methodKey, Response response) {
		// TODO Auto-generated method stub
		switch (response.status()) {
		case 400:
			break;
		case 404:
			if(methodKey.contains("getRoles(String)"))
				return new ResponseStatusException(HttpStatus.valueOf(response.status()), "role not founds");
		default:
			break;
		}
		return null;
	}

}
