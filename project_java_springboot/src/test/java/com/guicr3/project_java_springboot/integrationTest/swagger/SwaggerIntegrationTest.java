package com.guicr3.project_java_springboot.integrationTest.swagger;

import com.guicr3.project_java_springboot.config.TestConfigs;
import com.guicr3.project_java_springboot.integrationTest.testcontainers.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SwaggerIntegrationTest extends AbstractIntegrationTest {

	@Test
	void shouldDisplaySwaggerUIPage() {
		var content = given().basePath("/swagger-ui/index.html").port(TestConfigs.SERVER_PORT)
						.when()
							.get()
						.then()
							.statusCode(200)
						.extract()
							.body()
							.asString();

		assertTrue(content.contains("Swagger UI"));
	}

}
