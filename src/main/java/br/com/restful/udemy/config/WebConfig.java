package br.com.restful.udemy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
	
	/**
	 * habilitando CORS globalmente na aplicação
	 */
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		/**
		 * VIA EXTENSÃO .JSON OU .XML
		 * http://localhost:8080/api/v1/person/2.xml
		 * http://localhost:8080/api/v1/person/2.json
		 */
		/**
		configurer
			.favorParameter(false)
			.ignoreAcceptHeader(false)
			.defaultContentType(MediaType.APPLICATION_JSON)
			.mediaType("json", MediaType.APPLICATION_JSON)
			.mediaType("xml", MediaType.APPLICATION_XML);
		*/
		
		
		
		/**
		 * VIA QUERY STRING
		 * http://localhost:8080/api/v1/person/2?mediaType=xml
		 * http://localhost:8080/api/v1/person/2?mediaType=json
		 */
		/**
		configurer
			.favorPathExtension(false)
			.favorParameter(true)
			.parameterName("mediaType")
			.useRegisteredExtensionsOnly(false)
			.ignoreAcceptHeader(false)
			.defaultContentType(MediaType.APPLICATION_JSON)
			.mediaType("json", MediaType.APPLICATION_JSON)
			.mediaType("xml", MediaType.APPLICATION_XML);
		*/
		
		
		/**
		 * VIA HEADER DA REQUEST
		 * http://localhost:8080/api/v1/person/2?mediaType=xml
		 * http://localhost:8080/api/v1/person/2?mediaType=json
		 */
		configurer
			.favorPathExtension(false)
			.favorParameter(false)
			.ignoreAcceptHeader(false)
			.useRegisteredExtensionsOnly(false)
			.defaultContentType(MediaType.APPLICATION_JSON)
			.mediaType("json", MediaType.APPLICATION_JSON)
			.mediaType("xml", MediaType.APPLICATION_XML);
	}
}