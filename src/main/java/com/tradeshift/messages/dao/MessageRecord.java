package com.tradeshift.messages.dao;

import org.joda.time.DateTime;

/**
 * Created by ajo on 18/06/15.
 * <p/>
 * Class @{MessageRecord} maps one entry in the database to an object.
 * Objects of this class are used by @{MessageDao} and @{MessageService}.
 */
public class MessageRecord {
    public final int id;
    public final String name;
    public final DateTime receivedAt;

    public MessageRecord(int id, String name, DateTime receivedAt) {
        this.id = id;
        this.name = name;
        this.receivedAt = receivedAt;
    }
}
