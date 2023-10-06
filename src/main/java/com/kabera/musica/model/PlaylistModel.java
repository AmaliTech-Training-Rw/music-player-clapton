package com.kabera.musica.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.transaction.reactive.GenericReactiveTransaction;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_playlists")
public class PlaylistModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer playlistId;
    @Column(name = "playlist_name")
    private String playlistName;
    @ManyToMany()
    @JoinTable(name = "tbl_playlist_songs",
        joinColumns = @JoinColumn(name = "playlist_id"),
        inverseJoinColumns = @JoinColumn(name = "song_id"))
    private List<MusicModel> songs = new ArrayList<>();
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
}
