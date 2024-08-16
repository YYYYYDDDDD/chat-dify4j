# java对接dify应用的API

# 项目简介

本项目旨在对接 Dify 大模型不同应用的 API，实现与 Dify 应用的对话流处理，将对话结果流式返回给前端，并将对话结果分发给开发者自行处理

## 主要功能

- **对话流处理**: 根据不同的对话类型：对话型应用和Workflow应用，通过 Dify 的 API 处理用户输入，并返回相应的响应
- **多处理器支持**: 实现`ResponseProcessor`，处理不同的对话类型，方便日后系统拓展新的对话类型
- **基于 SSE (Server-Sent Events)**: 处理和推送来自 Dify 平台的对话响应，确保流式数据处理和实时更新

## 使用说明

> [!IMPORTANT]
> 确保在请求时使用有效的 Dify 应用 `apiKey`

### 配置文件
```properties
# 对话型应用URL
dify.chat-url=https://api.dify.ai/v1/chat-messages
# Workflow应用URL
dify.flow-url=https://api.dify.ai/v1/workflows/run
```




### 示例代码
- 方法调用

```java
@Autowired
private DifyApplicationChatHandler chatHandler;

  public void chat() throws JsonProcessingException {
        // 对话型应用:chat  Workflow应用:flow
        String type = "chat";

        // dify apikey
        String apiKey = "your-dify-api-key";

        // 对话型应用的用户输入
        String query = "用户输入的查询";

        // Dify App 定义的各变量值。 inputs 参数包含了多组键值对（Key/Value pairs），每组的键对应一个特定变量，每组的值则是该变量的具体值
        String inputs = "{\"key1\":\"value1\", \"key2\":\"key2\"}";
        
        // 会话ID
        String conversationId = "会话ID";
        
        // 用户标识
        String userId = "用户标识";

        SseEmitter emitter = chatHandler.handle(type, apiKey, query, inputs, conversationId, userId);
    }
```

- controller demo

```java
   @Autowired
   private DifyApplicationChatHandler chatHandler;

   @GetMapping("/chat")
    public ResponseEntity<SseEmitter> chat(@RequestParam("type") String type,
                                             @RequestParam("apiKey") String apiKey,
                                             @RequestParam(value = "query", required = false) String query,
                                             @RequestParam(value = "inputs", required = false) String inputs,
                                             @RequestParam(value = "conversationId", required = false) String conversationId,
                                             @RequestParam("userId") String userId) throws JsonProcessingException {

        SseEmitter emitter = difyApplicationChatHandler.handle(type, apiKey, query, inputs, conversationId, userId);

        // 设置响应头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new org.springframework.http.MediaType("text", "event-stream", StandardCharsets.UTF_8));

        return new ResponseEntity<>(emitter, headers, HttpStatus.OK);
    }
```
