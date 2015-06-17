package com.tradeshift.messages;

import org.springframework.stereotype.Service;

/**
 * Created by ajo on 08/06/15.
 * <p/>
 *
 * @{MessageService} class performs the logic of the webapp.
 * It responds to function calls from @{MessageResource} class.
 */

@Service
public class MessageService {
    public ResponseMessage getResponseMessage(String name) {
        
        return new ResponseMessage(name);
    }
}
