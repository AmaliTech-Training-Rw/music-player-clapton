package com.kabera.musica.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tbl_artist")
public class ArtistModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_id")
    private Integer artistId;
    @Column(name = "artist_name", unique = true)
    private String artistName;
    @Column(name = "artist_genre")
    private String artistGenre;
    @Column(name = "artist_bio")
    private String artistBio;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany(mappedBy = "artists")
    private List<MusicModel> songs = new ArrayList<>();

}
