package pl.klewek.springbootmusic.service;

import org.springframework.boot.json.JsonParseException;

import java.io.IOException;
import java.util.List;

public interface IYouTubeService {

    String getPlaylistId(String url);

    List<String> getTitlesFromPlaylist(String playlistId) throws IOException, JsonParseException;
}
