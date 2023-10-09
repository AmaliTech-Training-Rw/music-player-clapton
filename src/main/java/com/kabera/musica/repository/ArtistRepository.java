package com.kabera.musica.repository;

import com.kabera.musica.model.ArtistModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ArtistRepository extends JpaRepository<ArtistModel, Integer>{
    List<ArtistModel> findByArtistName(String name);
}
