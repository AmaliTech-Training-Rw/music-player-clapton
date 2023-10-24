package com.kabera.musica.repository;

import com.kabera.musica.model.PlaylistModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaylistRepository extends JpaRepository<PlaylistModel, Integer> {
    Optional<PlaylistModel> findByPlaylistName(String name);
}
