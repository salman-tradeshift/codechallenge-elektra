package com.tradeshift.elektra.johannes;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TestMessageResource {

    @Test
    public void testMessageResource() {

        Recent mockRecent = mock(Recent.class);
        MessageService mockMessageService = mock(MessageService.class);
        MessageResource resource = new MessageResource(mockMessageService);

        assertEquals(resource.getRecent(), mockMessageService.createRecent());

        String testString = "abc123";
        MessageDTO message = new MessageDTO(testString);
        when(mockMessageService.createMessage(testString)).thenReturn(message);

        assertEquals(resource.postMessage(testString), message);
    }

}
