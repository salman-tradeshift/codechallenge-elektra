package com.tradeshift.elektra.johannes;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.*;

public class TestMessageDTO {

    @Test
    public void testMessageDTO() {

        MessageDTO foo0 = new MessageDTO("foo");
        MessageDTO foo1 = new MessageDTO("foo");
        MessageDTO bar = new MessageDTO("bar");

        assertEquals(foo0,foo1);
        assertEquals(bar,bar);
        assertEquals(foo0.toString(), new String("foo"));

        assertTrue(!foo0.equals(bar));
        assertTrue(!foo0.equals(null));
        assertTrue(!foo0.equals(new String("a")));
    }
}
