package com.kabera.musica.controller;

import com.kabera.musica.model.MusicModel;
import com.kabera.musica.repository.MusicRepository;
import com.kabera.musica.service.ArtistService;
import com.kabera.musica.service.MusicService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Controller
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
    public ResponseEntity<Resource> downloadFile(HttpServletRequest request) {
        // Load file as Resource
        Resource resource = musicService.loadFileAsResource();

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
