package com.example.tourist.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.tourist.entity.TokenData;
import com.example.tourist.repository.TokenRepository;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

@Service
public class TokenService {

    public static final String JWT_SECRET = "01234567890123456789012345678901";

    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public TokenData getDefaultTokenData() {
        TokenData data = new TokenData("Alice", "Alice@gmail.com", "18", "valid");
        return tokenRepository.save(data);
    }

    public TokenData createTokenData(String name, String email, String token, String key) {
        String finalName = (name != null && !name.isBlank()) ? name : "Alice";
        String finalEmail = (email != null && !email.isBlank()) ? email : "Alice@gmail.com";
        String finalToken = (token != null && !token.isBlank()) ? token : "18";
        String finalKey = (key != null && !key.isBlank()) ? key : "valid";

        TokenData data = new TokenData(finalName, finalEmail, finalToken, finalKey);
        return tokenRepository.save(data);
    }

    public String generateSignedJwt(String name, String email, String token, String key) {
        try {
            JWSSigner signer = new MACSigner(JWT_SECRET.getBytes(StandardCharsets.UTF_8));

            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(email != null ? email : "Alice@gmail.com")
                    .claim("Name", name != null ? name : "Alice")
                    .claim("E-mail", email != null ? email : "Alice@gmail.com")
                    .claim("token", token != null ? token : "18")
                    .claim("key", key != null ? key : "valid")
                    .issueTime(new Date())
                    .expirationTime(new Date(System.currentTimeMillis() + 86400000)) // 24 hours
                    .build();

            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            signedJWT.sign(signer);

            return signedJWT.serialize();
        } catch (Exception e) {
            throw new RuntimeException("Error generating JWT token", e);
        }
    }
}
