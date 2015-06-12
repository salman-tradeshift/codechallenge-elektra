package com.tradeshift.elektra.johannes;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

public class RESTApplication extends ResourceConfig {

    public RESTApplication() {
        register(RequestContextFilter.class);
        register(MessageResource.class);
        register(MessageService.class);
    }
}
