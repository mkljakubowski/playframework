package javaguide.tests;

import java.io.IOException;
import java.util.concurrent.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.libs.F.*;
import play.libs.ws.*;

import static play.test.Helpers.*;
import static org.junit.Assert.*;

import static play.test.Helpers.NOT_FOUND;

// #test-withserver
public class ServerFunctionalTest extends WithServer {

    @Test
    public void testInServer() throws Exception {
        int timeout = 5000;
        String url = "http://localhost:" + this.testServer.port() + "/";
        try (WSClient ws = WS.newClient(this.testServer.port())) {
            CompletionStage<WSResponse> stage = ws.url(url).get();
            WSResponse response = stage.toCompletableFuture().get();
            assertEquals(NOT_FOUND, response.getStatus());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
// #test-withserver
