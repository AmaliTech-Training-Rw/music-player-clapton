package com.kabera.musica.repository;

import com.kabera.musica.model.ArtistModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ArtistRepository extends JpaRepository<ArtistModel, Integer>{
}
