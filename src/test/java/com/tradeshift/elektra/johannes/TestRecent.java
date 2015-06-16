package com.tradeshift.elektra.johannes;


import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.Array;
import java.util.*;

import com.fasterxml.jackson.core.*;

public class TestRecent {

    @Test
    public void testRecent()  {

        Date date = new Date(1);

        MessageDTO m1 = new MessageDTO("foo");
        MessageDTO m2 = new MessageDTO("bar");
        MessageDTO m3 = new MessageDTO("foo");

        ArrayList<MessageDTO> ml1 = new ArrayList<MessageDTO>(Arrays.asList(m1, m2));
        ArrayList<MessageDTO> ml2 = new ArrayList<MessageDTO>(Arrays.asList(m3, m2));

        Recent r1 = new Recent(ml1, date);
        Recent r2 = new Recent(ml2, date);

        assertEquals(2, r1.getMessageCount());
        assertEquals(r1.getMessages(), ml1);
        assertEquals(r1.getLastMessage(), r2.getLastMessage());
    }
}
