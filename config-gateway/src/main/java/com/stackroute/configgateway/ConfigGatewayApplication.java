package com.stackroute.configgateway;

import com.stackroute.configgateway.filters.ErrorFilter;
import com.stackroute.configgateway.filters.PostFilter;
import com.stackroute.configgateway.filters.PreFilter;
import com.stackroute.configgateway.filters.RouteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/*
Zuul is a JVM-based router and server-side load balancer from Netflix.
 */

@SpringBootApplication
//lives in spring-cloud-commons and picks the implementation on the classpath(eureka, zookeeper etc.)
@EnableDiscoveryClient
//activates the Netflix Eureka DiscoveryClient
@EnableEurekaClient
//Spring Cloud Netflix includes an embedded Zuul proxy
//to turn the Gateway application into a reverse proxy that forwards relevant calls to other services
@EnableZuulProxy
public class ConfigGatewayApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(ConfigGatewayApplication.class, args);
	}

	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}
	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}
	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}
	@Bean
	public RouteFilter routeFilter() {
		return new RouteFilter();
	}
}
