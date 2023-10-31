package com.example.musicrequestapp.domain.music.repository;

import com.example.musicrequestapp.domain.music.entity.Music;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface MusicRepository extends CrudRepository<Music, Long> {
    @Override
    List<Music> findAll();

    Optional<Music> findByUrl(String url);

}