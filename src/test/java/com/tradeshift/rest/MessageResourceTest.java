package com.tradeshift.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.google.common.collect.Lists;
import com.tradeshift.dto.ContentDTO;
import com.tradeshift.dto.MessageDTO;
import com.tradeshift.exception.ServiceException;
import com.tradeshift.rest.response.CreateMessageResponse;
import com.tradeshift.rest.response.GetRecentMessagesResponse;
import com.tradeshift.service.MessageService;

public class MessageResourceTest {

    private static final String TEST_NAME = "Hello SALMAN";
    private static final Timestamp TEST_TIMESTAMP = Timestamp.valueOf("2015-07-22 19:49:03.195");
    MessageService messageServiceMock = Mockito.mock(MessageService.class);

    @Before
    public void setup() throws ServiceException {
        CreateMessageResponse mockedMessage =
                new CreateMessageResponse(new ContentDTO(TEST_NAME));
        MessageDTO mockedMessageDTO =
                new MessageDTO(Lists.newArrayList(mockedMessage), TEST_TIMESTAMP);
        GetRecentMessagesResponse mockedResponse = new GetRecentMessagesResponse(mockedMessageDTO);
        Mockito.when(messageServiceMock.createMessage(TEST_NAME)).thenReturn(mockedMessage);
        Mockito.when(messageServiceMock.getRecentMessages()).thenReturn(mockedResponse);
    }

    @Test
    public void testCreateMessageEndpoint() {
        MessageResource messageResource = new MessageResource(messageServiceMock);

        Response response = messageResource.createMessage(TEST_NAME);

        assertNotNull(response);
        assertEquals(Status.OK.getStatusCode(), response.getStatus());
        assertTrue(response.getEntity() instanceof CreateMessageResponse);

        CreateMessageResponse restResponse = (CreateMessageResponse) response.getEntity();

        assertEquals(TEST_NAME, restResponse.getMessage().getContent());
    }

    @Test
    public void testErrorStatusCodeByRestCall() throws ServiceException {
        Mockito.when(messageServiceMock.createMessage(TEST_NAME))
                .thenThrow(ServiceException.class);

        MessageResource messageResource = new MessageResource(messageServiceMock);
        Response response = messageResource.createMessage(TEST_NAME);

        assertEquals(Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetRecentMessagesEndpoint() {
        MessageResource messageResource = new MessageResource(messageServiceMock);

        Response response = messageResource.getRecentMessages();
        assertNotNull(response);
        assertEquals(response.getStatus(), Status.OK.getStatusCode());
        assertTrue(response.getEntity() instanceof GetRecentMessagesResponse);

        GetRecentMessagesResponse restResponse = (GetRecentMessagesResponse) response.getEntity();
        assertEquals(1, restResponse.getMessageCount());
        assertEquals("2015-07-22T17:49:03.195Z", restResponse.getLastMessage());
        assertEquals(TEST_NAME, restResponse.getMessages().get(0).getMessage().getContent());
    }

    @Test
    public void testExceptionThrownByGetRecentMessages() throws ServiceException {
        Mockito.when(messageServiceMock.getRecentMessages())
                .thenThrow(ServiceException.class);

        MessageResource messageResource = new MessageResource(messageServiceMock);
        Response response = messageResource.getRecentMessages();
        assertEquals(Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
    }

}
