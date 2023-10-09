package com.kabera.musica.service;

import com.kabera.musica.model.ArtistModel;
import com.kabera.musica.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {
    @Autowired
    private ArtistRepository artistRepository;

    public List<ArtistModel> getArtistByName(String name){
        return artistRepository.findByArtistName(name);
    }
}
