package com.example.tourist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tourist.entity.TokenData;

@Repository
public interface TokenRepository extends JpaRepository<TokenData, Long> {
}
