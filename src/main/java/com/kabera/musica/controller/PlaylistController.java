package com.kabera.musica.controller;

import com.kabera.musica.model.MusicModel;
import com.kabera.musica.model.PlaylistModel;
import com.kabera.musica.service.MusicService;
import com.kabera.musica.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PlaylistController {

    private final MusicService musicService;
    private final PlaylistService playlistService;

    @GetMapping("/pub/playlist")
    public String createPlaylist(Model model){
        model.addAttribute("songList", musicService.findAllSongs());
        return "playlist";
    }

    @PostMapping("/pub/create-playlist")
    public String addSongToPlaylist(@RequestParam("song")MusicModel song,
                                    @RequestParam("name") String playlistName){
        List<MusicModel> songs = new ArrayList<>();
        songs.add(song);
        Optional<PlaylistModel> playlistOptional = playlistService.findPlaylistByName(playlistName);

        if(playlistOptional.isPresent()){
            PlaylistModel playlistToUpdate = playlistOptional.get();
            playlistToUpdate.setSongs(songs);
            playlistToUpdate.setPlaylistName(playlistName);
            playlistService.savePlaylist(playlistToUpdate);
        }

        PlaylistModel playlistModel = PlaylistModel.builder()
                .playlistName(playlistName)
                .songs(songs)
                .build();

        return "allPlaylists";
    }
}
