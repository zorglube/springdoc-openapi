/*
 *
 *  *
 *  *  *
 *  *  *  *
 *  *  *  *  *
 *  *  *  *  *  * Copyright 2019-2025 the original author or authors.
 *  *  *  *  *  *
 *  *  *  *  *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  *  *  *  * you may not use this file except in compliance with the License.
 *  *  *  *  *  * You may obtain a copy of the License at
 *  *  *  *  *  *
 *  *  *  *  *  *      https://www.apache.org/licenses/LICENSE-2.0
 *  *  *  *  *  *
 *  *  *  *  *  * Unless required by applicable law or agreed to in writing, software
 *  *  *  *  *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  *  *  *  *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  *  *  *  * See the License for the specific language governing permissions and
 *  *  *  *  *  * limitations under the License.
 *  *  *  *  *
 *  *  *  *
 *  *  *
 *  *
 *
 */

package org.springdoc.webflux.api;

import java.util.List;
import java.util.Locale;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.core.customizers.SpringDocCustomizers;
import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.properties.SpringDocConfigProperties;
import org.springdoc.core.providers.SpringDocProviders;
import org.springdoc.core.service.AbstractRequestService;
import org.springdoc.core.service.GenericResponseService;
import org.springdoc.core.service.OpenAPIService;
import org.springdoc.core.service.OperationService;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springdoc.core.utils.Constants.API_DOCS_URL;
import static org.springdoc.core.utils.Constants.APPLICATION_OPENAPI_YAML;
import static org.springdoc.core.utils.Constants.DEFAULT_API_DOCS_URL_YAML;
import static org.springframework.util.AntPathMatcher.DEFAULT_PATH_SEPARATOR;

/**
 * The type Multiple open api resource.
 *
 * @author bnasslahsen
 */
@RestController
public class MultipleOpenApiWebFluxResource extends MultipleOpenApiResource {

	/**
	 * Instantiates a new Multiple open api resource.
	 *
	 * @param groupedOpenApis           the grouped open apis
	 * @param defaultOpenAPIBuilder     the default open api builder
	 * @param requestBuilder            the request builder
	 * @param responseBuilder           the response builder
	 * @param operationParser           the operation parser
	 * @param springDocConfigProperties the spring doc config properties
	 * @param springDocProviders        the spring doc providers
	 * @param springDocCustomizers      the spring doc customizers
	 */
	public MultipleOpenApiWebFluxResource(List<GroupedOpenApi> groupedOpenApis, ObjectFactory<OpenAPIService> defaultOpenAPIBuilder,
			AbstractRequestService requestBuilder, GenericResponseService responseBuilder, OperationService operationParser,
			SpringDocConfigProperties springDocConfigProperties, SpringDocProviders springDocProviders, SpringDocCustomizers springDocCustomizers) {
		super(groupedOpenApis, defaultOpenAPIBuilder, requestBuilder, responseBuilder, operationParser, springDocConfigProperties, springDocProviders, springDocCustomizers);
	}

	/**
	 * Openapi json mono.
	 *
	 * @param serverHttpRequest the server http request
	 * @param apiDocsUrl        the api docs url
	 * @param group             the group
	 * @param locale            the locale
	 * @return the mono
	 * @throws JsonProcessingException the json processing exception
	 */
	@Operation(hidden = true)
	@GetMapping(value = API_DOCS_URL + "/{group}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<byte[]> openapiJson(ServerHttpRequest
			serverHttpRequest, @Value(API_DOCS_URL) String apiDocsUrl, @PathVariable String
			group, Locale locale) throws JsonProcessingException {
		return getOpenApiResourceOrThrow(group).openapiJson(serverHttpRequest, apiDocsUrl + DEFAULT_PATH_SEPARATOR + group, locale);
	}

	/**
	 * Openapi yaml mono.
	 *
	 * @param serverHttpRequest the server http request
	 * @param apiDocsUrl        the api docs url
	 * @param group             the group
	 * @param locale            the locale
	 * @return the mono
	 * @throws JsonProcessingException the json processing exception
	 */
	@Operation(hidden = true)
	@GetMapping(value = DEFAULT_API_DOCS_URL_YAML + "/{group}", produces = APPLICATION_OPENAPI_YAML)
	public Mono<byte[]> openapiYaml(ServerHttpRequest serverHttpRequest,
			@Value(DEFAULT_API_DOCS_URL_YAML) String apiDocsUrl, @PathVariable String
			group, Locale locale) throws JsonProcessingException {
		return getOpenApiResourceOrThrow(group).openapiYaml(serverHttpRequest, apiDocsUrl + DEFAULT_PATH_SEPARATOR + group, locale);
	}

}