package com.kabera.musica.service;

import com.kabera.musica.model.PlaylistModel;
import com.kabera.musica.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final PlaylistRepository playlistRepository;

    public Optional<PlaylistModel> findPlaylistByName(String name){
        return playlistRepository.findByPlaylistName(name);
    }

    public void updatePlaylist(PlaylistModel playlistModel){
        playlistRepository.save(playlistModel);
    }

    public void savePlaylist(PlaylistModel playlistModel){
        playlistRepository.save(playlistModel);
    }
}
