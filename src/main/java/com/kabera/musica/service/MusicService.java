package com.kabera.musica.service;

import com.kabera.musica.exception.MusicFileNotFoundException;
import com.kabera.musica.model.MusicModel;
import com.kabera.musica.property.FileStorageProperty;
import com.kabera.musica.exception.FileStorageException;
import com.kabera.musica.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
public class MusicService {
    private final Path fileStorageLocation;

    @Autowired
    public MusicService(FileStorageProperty fileStorageProperty) {
        this.fileStorageLocation = Paths.get(fileStorageProperty.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Autowired
    private MusicRepository musicRepository;

    public String saveSong(MusicModel song, MultipartFile file){
        musicRepository.save(song);
        return "song saved successfully";
    }

    public String saveSong(MusicModel song){
        musicRepository.save(song);
        return "Song updated!";
    }

    public List<MusicModel> findAllSongs(){
        return musicRepository.findAll();
    }

    public Path storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(),
                    targetLocation,
                    StandardCopyOption.REPLACE_EXISTING);

            return targetLocation;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource() {
        List<MusicModel> songs = musicRepository.findAll();
        try {
            Path filePath = null;
            Resource resource = null;
            for(MusicModel song : songs){
                filePath = this.fileStorageLocation.resolve(song.getSongUrl()).normalize();
                resource = new UrlResource(filePath.toUri());
                if(resource.exists()) {
                    return resource;
                } else {
                    throw new MusicFileNotFoundException("File not found ");
                }
            }
        } catch (MalformedURLException ex) {
            throw new MusicFileNotFoundException("File not found " + ex);
        }
        return null;
    }

    public Optional<MusicModel> findSongById(Integer songId) {
        return musicRepository.findById(songId);
    }

    public String deleteSong(MusicModel songToDelete) {
        musicRepository.delete(songToDelete);
        return "Song deleted!";
    }
}
