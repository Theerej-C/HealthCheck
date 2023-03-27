package com.authenticate.security.services;

import com.authenticate.security.model.HealthResponse;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EndPointService {
    public HealthResponse testingEndPointGet(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        int a = response.code();
        if(a<400) {
            RestTemplate restTemplate = new RestTemplate();
            String userJson = restTemplate.getForObject(url, String.class, Map.of("id", "1"));
            HealthResponse healthResponse = HealthResponse.builder()
                    .statusNo(a)
                    .response(userJson.substring(0,200))
                    .bool(1)
                    .build();
            return healthResponse;
        }
        else{
            HealthResponse healthResponse = HealthResponse.builder()
                    .statusNo(a)
                    .response("There is a error which is caused due to")
                    .bool(0)
                    .build();
            return healthResponse;
        }
    }

}
