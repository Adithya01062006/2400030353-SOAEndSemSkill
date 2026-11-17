package com.example.tourist.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.tourist.entity.TokenData;
import com.example.tourist.service.TokenService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name = "2400030353-SOAEndSemSkill", description = "Direct JWT Token Generation & Validation")
public class TokenController {

    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Operation(summary = "Direct JWT Token Generation - Default Output (Alice)")
    @GetMapping("/token")
    public ResponseEntity<TokenData> getToken() {
        TokenData data = tokenService.getDefaultTokenData();
        String jwt = tokenService.generateSignedJwt(data.getName(), data.getEmail(), data.getToken(), data.getKey());

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + jwt);
        headers.set("X-JWT-Token", jwt);

        return ResponseEntity.ok().headers(headers).body(data);
    }

    @Operation(summary = "Direct JWT Token Generation with Request Body or Default")
    @PostMapping("/token")
    public ResponseEntity<TokenData> postToken(@RequestBody(required = false) TokenData request) {
        String name = (request != null && request.getName() != null) ? request.getName() : "Alice";
        String email = (request != null && request.getEmail() != null) ? request.getEmail() : "Alice@gmail.com";
        String token = (request != null && request.getToken() != null) ? request.getToken() : "18";
        String key = (request != null && request.getKey() != null) ? request.getKey() : "valid";

        TokenData data = tokenService.createTokenData(name, email, token, key);
        String jwt = tokenService.generateSignedJwt(name, email, token, key);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + jwt);
        headers.set("X-JWT-Token", jwt);

        return ResponseEntity.ok().headers(headers).body(data);
    }

    @Operation(summary = "Generate Token Output (GET)")
    @GetMapping("/generate-token")
    public ResponseEntity<TokenData> generateTokenGet() {
        return getToken();
    }

    @Operation(summary = "Generate Token Output (POST)")
    @PostMapping("/generate-token")
    public ResponseEntity<TokenData> generateTokenPost(
            @RequestParam(required = false, defaultValue = "Alice") String name,
            @RequestParam(required = false, defaultValue = "Alice@gmail.com") String email,
            @RequestParam(required = false, defaultValue = "18") String token,
            @RequestParam(required = false, defaultValue = "valid") String key) {
        TokenData data = tokenService.createTokenData(name, email, token, key);
        String jwt = tokenService.generateSignedJwt(name, email, token, key);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + jwt);
        headers.set("X-JWT-Token", jwt);

        return ResponseEntity.ok().headers(headers).body(data);
    }

    @Operation(summary = "Get JWT Signed Token String and Claims")
    @GetMapping("/jwt-token")
    public ResponseEntity<Map<String, Object>> getJwtDetails() {
        TokenData data = tokenService.getDefaultTokenData();
        String jwt = tokenService.generateSignedJwt(data.getName(), data.getEmail(), data.getToken(), data.getKey());

        Map<String, Object> response = new HashMap<>();
        response.put("Name", data.getName());
        response.put("E-mail", data.getEmail());
        response.put("token", data.getToken());
        response.put("key", data.getKey());
        response.put("jwtToken", jwt);

        return ResponseEntity.ok(response);
    }
}
