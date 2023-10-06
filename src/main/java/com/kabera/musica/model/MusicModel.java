package com.kabera.musica.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_music")
public class MusicModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "song_id")
    private Integer songId;
    @Column(name = "song_title")
    private String songTitle;
    @Column(name = "song_url")
    private String songUrl;
    @Column(name = "song_description")
    private String songDescription;
    @ManyToMany()
    @JoinTable(name = "music_artists",
        joinColumns = @JoinColumn(name = "song_id"),
        inverseJoinColumns = @JoinColumn(name = "artist_id"))
    private List<ArtistModel> artists = new ArrayList<>();
    @ManyToMany(mappedBy = "songs")
    private List<PlaylistModel> playlistModels = new ArrayList<>();
}
