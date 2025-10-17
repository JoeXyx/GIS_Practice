package org.example.gispractice.service;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*; import java.util.*;

@Service public class AIService {
    private static final String OPENAI_API_KEY = "";
    private static final String OPENAI_URL = "https://api.deepseek.com/v1/chat/completions";
    private static final String SYSTEM_PROMPT = "你是KFC智能客服，回答门店信息相关问题。";
    public String ask(String question) {
        try { // 如果未配置Key，则直接返回固定回答
             if (OPENAI_API_KEY.equals("sk-你的APIKey")) {
                 return "（测试模式）你问了：" + question + "。我还在学习中~";
             }
             RestTemplate restTemplate = new RestTemplate();
             Map<String, Object> message = new HashMap<>();
             message.put("role", "user"); message.put("content", question);
             Map<String, Object> body = new HashMap<>();
             body.put("model", "deepseek-chat");
             body.put("messages", Collections.singletonList(message));
             HttpHeaders headers = new HttpHeaders();
             headers.setContentType(MediaType.APPLICATION_JSON);
             headers.setBearerAuth(OPENAI_API_KEY);
             HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
             Map<String, Object> response = restTemplate.postForObject(OPENAI_URL, entity, Map.class);
             List<Map<String, Object>> choices = (List<Map<String, Object>>) response.get("choices");
             Map<String, Object> messageObj = (Map<String, Object>) choices.get(0).get("message");
             return messageObj.get("content").toString().trim();
        } catch (Exception e) {
            e.printStackTrace();
            return "AI客服暂时无法回答，请稍后再试。";
        }
    }
}
