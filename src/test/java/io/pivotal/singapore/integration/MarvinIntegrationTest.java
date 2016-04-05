package io.pivotal.singapore.integration;

import io.pivotal.singapore.MilliwaysApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringStartsWith.startsWith;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MilliwaysApplication.class)
@WebAppConfiguration
@IntegrationTest
public class MarvinIntegrationTest {

    @Value("${api.marvin.register}")
    String marvinUrl;

    @Value("${api.marvin.root}")
    String marvinRoot;

    @Test
    public void testThatACommandIsRegisteredWithMarvinOnStartup() {
        when()
                .get(marvinUrl).
        then()
                .body("_embedded.commands.find { command -> command.name == \"time\" }.name", is("time"));
    }


    @Test
    public void testThatOurCommandReturnsTheRightResponseToSlack() {
        given()
                .param("text", "time in London")
                .param("token", "FAKE_TEST_TOKEN").
                when()
                .get(marvinRoot).
                then()
                .statusCode(200)
                .body("response_type", is("in_channel"))
                .body("text", startsWith("The time in London is "));
    }
}
