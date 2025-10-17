package org.example.gispractice.controller;

import org.example.gispractice.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@RestController
@RequestMapping("/api/chat")
@CrossOrigin // 允许前端跨域访问
public class ChatController {

    @Autowired
    private AIService aiService;

    @PostMapping
    public Map<String, String> chat(@RequestBody Map<String, String> request) {
        String question = request.get("message");

        String answer = aiService.ask(question);

        Map<String, String> response = new HashMap<>();
        response.put("reply", answer);
        return response;
    }
}
