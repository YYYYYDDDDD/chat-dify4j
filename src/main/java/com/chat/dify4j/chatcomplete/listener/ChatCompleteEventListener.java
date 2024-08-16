package com.chat.dify4j.chatcomplete.listener;

import com.chat.dify4j.chatcomplete.event.ChatCompleteEvent;
import com.chat.dify4j.model.DifyApplicationResponse;
import org.springframework.stereotype.Component;

@Component
public class ChatCompleteEventListener implements IChatCompleteEventListener{

    public void onChatCompleteEvent(ChatCompleteEvent event) {
        // 处理对话结束后的结果
        String fullAnswer = event.getFullAnswer();
        DifyApplicationResponse fullResult = event.getFullResult();
        System.out.println("Received chat completion fullAnswer: " + fullAnswer);
        System.out.println("Received chat completion fullResult: " + fullResult);
    }
}
