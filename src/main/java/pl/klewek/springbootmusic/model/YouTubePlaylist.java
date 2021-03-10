package pl.klewek.springbootmusic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class YouTubePlaylist {

    private String playlistId;

    private String url;

    private int numbOfItems;

    private List<String> itemsList = new ArrayList<>();

}
