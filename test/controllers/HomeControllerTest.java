package controllers;

import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.mvc.Http.Status.FORBIDDEN;
import static play.test.Helpers.POST;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;
import static play.test.Helpers.contentAsString;

public class HomeControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void testIndex() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/");

        Result result = route(app, request);
        assertEquals(OK, result.status());
        org.junit.Assert.assertTrue(contentAsString(result).contains("Welcome to Play"));
    }

    @Test
    public void testPostWithoutCsrfTokenIsForbidden() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .uri("/send");

        Result result = route(app, request);
        assertEquals(FORBIDDEN, result.status());
    }

}
