package com.chat.dify4j.properties;

import com.chat.dify4j.enums.ChatUrl;

/**
 * 初始化enum ChatUrl的值
 */
public class ChatUrlInitializer {

    public ChatUrlInitializer(ChatUrlProperties chatUrlProperties) {
        ChatUrl.CHAT.setUrl(chatUrlProperties.getChatUrl());
        ChatUrl.FLOW.setUrl(chatUrlProperties.getFlowUrl());
    }
}
