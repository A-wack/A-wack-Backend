package com.example.musicrequestapp.domain.email.repository;

import com.example.musicrequestapp.domain.email.entity.UserEmail;
import org.springframework.data.repository.CrudRepository;

public interface UserEmailRepository extends CrudRepository<UserEmail, String> {
}
