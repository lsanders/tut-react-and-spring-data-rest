/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.greglturnquist.payroll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.boot.context.embedded.FilterRegistrationBean;

/**
 * @author Greg Turnquist
 */
// tag::code[]
@SpringBootApplication
public class ReactAndSpringDataRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactAndSpringDataRestApplication.class, args);
	}

        
/**
 * http://stackoverflow.com/a/31748398/122441 until https://jira.spring.io/browse/DATAREST-573
 * @return
 */
    @Bean
    public FilterRegistrationBean corsFilter() {
	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	CorsConfiguration config = new CorsConfiguration();
	config.setAllowCredentials(true);
	config.addAllowedOrigin("http://localhost:3000");
	config.addAllowedHeader("*");
	config.addAllowedMethod("*");
	source.registerCorsConfiguration("/**", config);
	// return new CorsFilter(source);
	final FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
	bean.setOrder(0);
	return bean;
    }

/*
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		// silly spring, no support for using application.properties yet
		// https://jira.spring.io/browse/DATAREST-573
		CorsConfiguration config = new CorsConfiguration();
		//config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("HEAD");
		config.addAllowedMethod("DELETE");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
*/
}
// end::code[]
