package com.kabera.musica.dto;

import com.kabera.musica.model.ArtistModel;

import java.util.ArrayList;
import java.util.List;

public class MusicResponse {
    private Integer song;
    private String songTitle;
    private String songUrl;
    private String songDescription;
    private List<ArtistModel> artists = new ArrayList<>();
}
