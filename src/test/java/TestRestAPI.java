import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;
import org.mockito.Mockito;
import rest.*;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
/**
 * Created by ksp on 23/06/15.
 */
public class TestRestAPI extends JerseyTest {

    @Override
    protected Application configure() {
        List<Message> messages = new ArrayList<Message>();
        MessageDAO test = Mockito.mock(MessageDAO.class);
        Mockito.when(test.getLatestMessages()).thenReturn(messages);
        MessageService messageService = new MessageService(test);
        MessageResource messageResource = new MessageResource(messageService);

        ResourceConfig rc = new ResourceConfig();
        rc.register(messageResource);
        rc.property("contextConfigLocation", "test-applicationContext.xml");

        return rc;
    }

    @Test
    public void testRecent(){
        Response response = target("/messages/recent").request().get();
        assertEquals(200, response.getStatus());
    }

    @Test
    public void testName(){
        Response response = target("/messages/names/peter").request().post(null);
        assertEquals(200, response.getStatus());
    }

}
