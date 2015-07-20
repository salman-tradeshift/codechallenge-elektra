package com.tradeshift.service;

import com.tradeshift.dto.Message;
import com.tradeshift.exception.ServiceException;

public interface MessageService {

    public Message processContent(String name) throws ServiceException;
}
