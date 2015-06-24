package com.tradeshift.elektra.johannes;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.*;

public class MessageDTOTest {

    @Test
    public void MessageDTO_can_be_created_and_compared_to_strings() {
        MessageDTO message1 = new MessageDTO("foo");
        MessageDTO message2 = new MessageDTO("foo");
        MessageDTO message3 = new MessageDTO("message3");

        assertEquals(message1   , message2);
        assertEquals(message3   , message3);
        assertEquals("foo"      , message1.toString());

        assertNotEquals(message1, message3);
        assertNotEquals(message1, null);
        assertNotEquals(message1, new String("a"));
    }
}
