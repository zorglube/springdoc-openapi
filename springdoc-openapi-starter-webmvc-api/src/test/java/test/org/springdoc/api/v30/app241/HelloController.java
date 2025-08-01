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

package test.org.springdoc.api.v30.app241;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@PostMapping("/parent")
	public void parentEndpoint(@RequestBody Superclass parent) {

	}

}

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
sealed class Superclass permits IntermediateClass {

	public Superclass() {
	}
}

@Schema(name = IntermediateClass.SCHEMA_NAME)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
sealed class IntermediateClass extends Superclass permits FirstChildClass, SecondChildClass {

	public static final String SCHEMA_NAME = "IntermediateClass";
}

@Schema(name = FirstChildClass.SCHEMA_NAME)
final class FirstChildClass extends IntermediateClass {

	public static final String SCHEMA_NAME = "Image";
}

@Schema(name = SecondChildClass.SCHEMA_NAME)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
sealed class SecondChildClass extends IntermediateClass {

	public static final String SCHEMA_NAME = "Mail";
}

@Schema(name = ThirdChildClass.SCHEMA_NAME)
final class ThirdChildClass extends SecondChildClass {

	public static final String SCHEMA_NAME = "Home";
}
