package com.example.demo.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRouting {
	
	@Bean
	RouteLocator configureRoute(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("hospital_route", r->r.path("/hospital-feign/**").uri("lb://HOSPITALFINDDOCTORS/hospital-feign"))
				.route("doctor_all_route", r->r.path("/doctors/**").filters(s->s.setRequestHeader("sort", "Medicine")).uri("lb://DOCTORGETALLSERVICE/doctors/"))
				.route("doctor_add_route", r->r.path("/doctor").uri("lb://DOCTORADDSERVICE/doctor"))
				.route("doctor_id_route", r->r.path("/doctor/**").and().method("GET").uri("lb://DOCTORGETBYIDSERVICE/doctor"))
				.route("doctor_put_route", r->r.path("/doctor/**").and().method("PUT").uri("lb://DOCTOREDITSERVICE/doctor"))
				.route("doctor_delete_route", r->r.path("/doctor/**").and().method("DELETE").uri("lb://DOCTORDELETESERVICE/doctor"))
				.build();
	}
}
