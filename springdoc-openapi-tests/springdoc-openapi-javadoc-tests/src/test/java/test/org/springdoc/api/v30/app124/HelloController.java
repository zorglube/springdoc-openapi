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

package test.org.springdoc.api.v30.app124;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Hello controller.
 *
 * @param <T> the type parameter
 */
@RestController
class HelloController<T> {

	/**
	 * Bad.
	 *
	 * @param e the e
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	@ApiResponse(responseCode = "404", description = "Not here", content = @Content)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void bad(IllegalArgumentException e) {

	}

	/**
	 * Index t.
	 *
	 * @param numTel  the num tel
	 * @param adresse the adresse
	 * @return the t
	 */
	@GetMapping(value = "/hello/{numTelco}")
	@Operation(summary = "GET Persons", responses = @ApiResponse(responseCode = "418"))
	public T index(@PathVariable("numTelco") String numTel, String adresse) {
		throw new IllegalArgumentException();

	}
}
