/**
 * nasru - Jun 25, 2020
 * MasterApiFallback.java 
 */
package com.mypractice.microservices.userapi.util;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mypractice.microservices.userapi.config.MasterApiClient;
import com.mypractice.microservices.userapi.model.RoleResponseModel;

import feign.FeignException;
import feign.hystrix.FallbackFactory;

/**
 * @author nasru
 *
 */
@Component
public class MasterApiFallback implements FallbackFactory<MasterApiClient> {

	@Override
	public MasterApiClient create(Throwable cause) {
		// TODO Auto-generated method stub
		return new MasterApiClientFallBack(cause);
	}

	/**
	 * @param cause
	 * @return
	 */

}

class MasterApiClientFallBack implements MasterApiClient {
	final Throwable cause;

	public MasterApiClientFallBack(Throwable cause) {
		this.cause = cause;
	}

	@Override
	public RoleResponseModel getRoles(String name) {
		// TODO Auto-generated method stub
		if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
			System.out.println("Error found 4040 " + cause.getLocalizedMessage());
		} else {
			System.out.println("other error found 4040 " + cause.getLocalizedMessage());
		}
		return new RoleResponseModel();
	}

}