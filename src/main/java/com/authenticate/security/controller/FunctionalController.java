package com.authenticate.security.controller;

import com.authenticate.security.model.HealthRequest;
import com.authenticate.security.model.HealthResponse;
import com.authenticate.security.services.EndPointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/function")
@RequiredArgsConstructor
public class FunctionalController {
    private final EndPointService endPointService;
    @PostMapping("/endpoint")
    public ResponseEntity<HealthResponse> endpoint(@RequestBody HealthRequest healthRequest) throws IOException {
        String url = healthRequest.getUrl();
        String username = healthRequest.getUsername();
        String password = healthRequest.getPassword();
        HealthResponse healthResponse = HealthResponse.builder()
                        .statusNo(endPointService.testingEndPoint(url))
                                .status("")
                                        .build();
        return ResponseEntity.ok(healthResponse);
    }

}
