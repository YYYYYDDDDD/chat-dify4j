package com.chat.dify4j.customer.rebuild.listener;

import com.chat.dify4j.customer.rebuild.event.CustomerRebuildAnswerEvent;
import org.springframework.stereotype.Component;

@Component
public class CustomerRebuildAnswerListener implements ICustomerRebuildAnswerListener{
    @Override
    public void rebuildAnswer(CustomerRebuildAnswerEvent event) {
        StringBuilder stringBuilder = event.getBody().get();
        stringBuilder.setLength(0);
        stringBuilder.append("this is customer rebuild answer");
    }
}
