/**
 * nasru - Jun 25, 2020
 * MasterApiClient.java 
 */
package com.mypractice.microservices.userapi.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mypractice.microservices.userapi.model.RoleResponseModel;
import com.mypractice.microservices.userapi.util.MasterApiFallback;

/**
 * @author nasru
 *
 */
@FeignClient(name = "master-ws", fallbackFactory = MasterApiFallback.class )
public interface MasterApiClient {

	@GetMapping("/roles/role/{name}/names")
	public RoleResponseModel getRoles(@PathVariable("name") String name);
}
