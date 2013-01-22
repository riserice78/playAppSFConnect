import org.junit.Test;
import play.mvc.Result;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

public class TestRoutes {

    @Test
    public void testAccountRoutes() {
        Result result = routeAndCall(fakeRequest(GET, "/searchAccount"));
        assertThat(result).isNotNull();
        result = routeAndCall(fakeRequest(GET, "/accounts"));
        assertThat(result).isNotNull();        
    }

}