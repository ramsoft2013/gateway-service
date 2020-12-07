package com.authservice.feignInterface;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.authservice.constants.MicroServiceConstants;
import com.authservice.responseDTO.AdminResponseDTO;

@FeignClient(name = "employee-service")
@RequestMapping("/api/v1")
public interface AdminInterface {

	 @RequestMapping(value="/getusers/{username}",method=RequestMethod.GET)
	 AdminResponseDTO fetchAdminByUsername(@PathVariable("username") String username);
}
