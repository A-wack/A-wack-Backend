package com.example.musicrequestapp.domain.music.repository;

import com.example.musicrequestapp.domain.music.entity.Music;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface MusicRepository extends CrudRepository<Music, Long> {
    @Override
    List<Music> findAll();

}