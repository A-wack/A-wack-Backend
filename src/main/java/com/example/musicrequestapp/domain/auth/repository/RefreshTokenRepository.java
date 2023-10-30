package com.example.musicrequestapp.domain.auth.repository;

import com.example.musicrequestapp.domain.auth.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}