package com.linbsoft.microserviceribbonhystrix8803;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class UserManagementRibbonClient {
	   @Autowired
	    RestTemplate restTemplate;
	   @Value("${server.port}")
	   String port;
	   
	   @GetMapping("/listUsersByRibbon")
	   @HystrixCommand(fallbackMethod="listUsersByRibbonFallback")
	   public String listUsersByRibbon(){
		   // MicroserviceServerA8801 是提供服务的多个应用的同名名称
		   String result = this.restTemplate.getForObject("http://MicroserviceServerA8801/listusers", String.class);
		   return result;
		   }
	   
	   public String listUsersByRibbonFallback(){
		   return "listUsersByRibbon异常，端口：" + port;
		   }

}
