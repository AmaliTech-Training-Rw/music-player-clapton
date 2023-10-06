package com.kabera.musica.repository;

import com.kabera.musica.model.MusicModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends JpaRepository<MusicModel, Integer> {
}
