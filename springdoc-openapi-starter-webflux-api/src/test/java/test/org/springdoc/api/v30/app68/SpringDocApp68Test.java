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

package test.org.springdoc.api.v30.app68;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import test.org.springdoc.api.v30.AbstractSpringDocTest;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(properties = {
		"springdoc.group-configs[0].group=stream",
		"springdoc.group-configs[0].pathsToMatch=/stream/**"
})
public class SpringDocApp68Test extends AbstractSpringDocTest {

	public SpringDocApp68Test() {
		this.groupName = "/stream";
	}


	@SpringBootApplication
	@ComponentScan(basePackages = { "org.springdoc", "test.org.springdoc.api.v30.app68" })
	static class SpringDocTestApp {
		@Bean
		public OpenAPI customOpenAPI() {
			return new OpenAPI()
					.components(new Components().addSecuritySchemes("basicScheme",
							new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
					.info(new Info().title("Tweet API").version("v0")
							.license(new License().name("Apache 2.0").url("http://springdoc.org")));
		}
	}
}
