package org.example.gispractice.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/api")
public class NearbyController {

    private final String BAIDU_API_URL = "https://api.map.baidu.com/place/v2/search";
    private final String BAIDU_AK = "你的百度AK"; // ⚠️ 替换为你的真实AK

    @GetMapping("/nearby")
    public Map<String, Object> searchNearby(
            @RequestParam String query,
            @RequestParam double lat,
            @RequestParam double lng,
            @RequestParam(defaultValue = "5000") int radius) {

        // 拼接百度API请求URL
        String url = String.format(
                "%s?query=%s&location=%f,%f&radius=%d&output=json&ak=%s",
                BAIDU_API_URL, query, lat, lng, radius, BAIDU_AK
        );

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        // 返回结果原样给前端（或根据需要提取字段）
        return response;
    }
}
