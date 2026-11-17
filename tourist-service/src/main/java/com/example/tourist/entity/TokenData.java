package com.example.tourist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "token_data")
@JsonPropertyOrder({ "Name", "E-mail", "token", "key" })
public class TokenData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "name")
    @JsonProperty("Name")
    private String name = "Alice";

    @Column(name = "email")
    @JsonProperty("E-mail")
    private String email = "Alice@gmail.com";

    @Column(name = "token_val")
    @JsonProperty("token")
    private String token = "18";

    @Column(name = "key_val")
    @JsonProperty("key")
    private String key = "valid";

    public TokenData() {
    }

    public TokenData(String name, String email, String token, String key) {
        this.name = name;
        this.email = email;
        this.token = token;
        this.key = key;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
