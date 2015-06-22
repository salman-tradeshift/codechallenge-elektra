package com.tradeshift.messages;

import com.tradeshift.messages.dao.MessageDao;
import com.tradeshift.messages.resource.MessageResource;
import com.tradeshift.messages.service.MessageService;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

/**
 * Created by ajo on 09/06/15.
 * <p/>
 *
 * @{MessageConfigClass} is used from web.xml as value for javax.ws.rs.Application servlet parameter.
 * It registers Spring beans in this webapp.
 */

public class MessageConfigClass extends ResourceConfig {
    public MessageConfigClass() {
        register(RequestContextFilter.class);
        register(MessageService.class);
        register(MessageResource.class);
        register(MessageDao.class);
    }
}
