package io.pivotal.singapore.integration;

import io.pivotal.singapore.MilliwaysApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MilliwaysApplication.class)
@WebAppConfiguration
public class MarvinIntegrationTest {

    @Value("${api.marvin.register}")
    String marvinUrl;

    @Before
    public void setUp() {

    }

    @Test
    public void testRegisteringCommands() {
        when()
                .get(marvinUrl).
        then()
                .statusCode(200);

    }
}
