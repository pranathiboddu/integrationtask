package com.stackroute.muzixapplication.repository;

import com.stackroute.muzixapplication.domain.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MusicRepository extends MongoRepository<Music,Integer>
{
/*
    @Query(value = "select c from Music c where c.trackName = ?1")
    List<Music> findTrackByName(String trackName);*/
}

