package com.kabera.musica.repository;

import com.kabera.musica.model.PlaylistModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<PlaylistModel, Integer> {
}
