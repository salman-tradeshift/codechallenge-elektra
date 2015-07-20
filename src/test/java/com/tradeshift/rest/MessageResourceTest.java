package com.tradeshift.rest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.tradeshift.dto.Message;
import com.tradeshift.dto.RestResponse;
import com.tradeshift.exception.ServiceException;
import com.tradeshift.rest.impl.MessageResourceImpl;
import com.tradeshift.service.MessageService;

public class MessageResourceTest {

    private static final String TEST_NAME = "SALMAN";
    MessageService messageServiceMock = Mockito.mock(MessageService.class);

    @Before
    public void setup() throws ServiceException {
        Message mockedMessage = new Message();
        mockedMessage.setName(TEST_NAME);
        Mockito.when(messageServiceMock.processContent(TEST_NAME)).thenReturn(mockedMessage);
    }

    @Test
    public void testCreateName() {
        MessageResourceImpl messageResource = new MessageResourceImpl();
        messageResource.setMessageService(messageServiceMock);

        Response response = messageResource.createName(TEST_NAME);

        assertNotNull(response);
        assertTrue(response.getStatus() == Status.OK.getStatusCode());
        assertTrue(response.getEntity() instanceof RestResponse);

        RestResponse restResponse = (RestResponse) response.getEntity();

        assertTrue(restResponse.getMessage().getContent().equals("Hello " + TEST_NAME));
    }

    @Test
    public void testErrorStatusCodeByRestCall() throws ServiceException {
        Mockito.when(messageServiceMock.processContent(TEST_NAME))
                .thenThrow(ServiceException.class);

        MessageResourceImpl messageResource = new MessageResourceImpl();
        messageResource.setMessageService(messageServiceMock);

        Response response = messageResource.createName(TEST_NAME);

        assertTrue(response.getStatus() == Status.INTERNAL_SERVER_ERROR.getStatusCode());
    }

}
