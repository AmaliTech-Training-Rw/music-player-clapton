package com.kabera.musica.controller;

import com.kabera.musica.model.MusicModel;
import com.kabera.musica.service.ArtistService;
import com.kabera.musica.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pub")
public class MusicController {
    @Value("${file.upload-dir}")
    private String songLocation;
    @Autowired
    private MusicService musicService;
    @Autowired
    private ArtistService artistService;

    @GetMapping("/upload")
    public String upload(){
        return "upload";
    }

    @PostMapping("/create-song")
    public ResponseEntity<?> uploadSong(@RequestParam("file") MultipartFile file,
                                        @RequestParam("title") String songTitle,
                                        @RequestParam("descr") String description,
                                        @RequestParam("artist") String artists){
        Path filePath = musicService.storeFile(file);

        MusicModel song = MusicModel.builder()
                .songUrl(filePath.toString())
                .songTitle(songTitle)
                .songDescription(description)
                .build();
        song.setArtists(artistService.getArtistByName(artists));

        musicService.saveSong(song, file);
        return new ResponseEntity<>("song saved", HttpStatus.CREATED);
    }

    @GetMapping("/songs")
    public String getAllSongs(Model model) throws IOException {
        model.addAttribute("songList", musicService.findAllSongs());
        return "songs";
    }

    @PutMapping("/song/update")
    public ResponseEntity<?> updateSongDetails(@RequestParam Integer songId){
        Optional<MusicModel> song = musicService.findSongById(songId);

        if(song.isEmpty()){
            return new ResponseEntity<>("The song requested for does not exist", HttpStatus.NOT_FOUND);
        }

        MusicModel songToUpdate = song.get();
        musicService.saveSong(songToUpdate);
        return new ResponseEntity<>("Song updated", HttpStatus.OK);
    }

    @DeleteMapping("/song/delete")
    public ResponseEntity<?> deleteSong(@RequestParam Integer songId){
        Optional<MusicModel> song = musicService.findSongById(songId);

        if(song.isEmpty()){
            return new ResponseEntity<>("The song requested for does not exist", HttpStatus.NOT_FOUND);
        }

        MusicModel songToDelete = song.get();
        musicService.deleteSong(songToDelete);
        return new ResponseEntity<>("Song deleted", HttpStatus.OK);
    }

    @GetMapping("/test")
    public String getTestPage(){
        return "test";
    }
}
