package pl.klewek.springbootmusic.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.json.JsonParseException;
import org.springframework.stereotype.Service;
import pl.klewek.springbootmusic.model.youtube.Root;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class YouTubeService implements IYouTubeService {

    private final static Logger LOGGER = LogManager.getLogger(YouTubeService.class);

    @Override
    public String getPlaylistId(String url) {
        int startIdx = url.indexOf("&list=") + 6;
        String id;
        LOGGER.info(url);

        if(url.contains("&start")){
            int endIdx = url.indexOf("&start");
            id = url.substring(startIdx,endIdx);
        } else if(url.contains("&index=")){
            int endIdx = url.indexOf("&index=");
            id = url.substring(startIdx,endIdx);
        }else{
            id = url.substring(startIdx);
        }
        return id;
    }

    @Override
    public List<String> getTitlesFromPlaylist(String playlistId) throws IOException, JsonParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> titlesList = new ArrayList<>();
        String apiCall = String.format("https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=%s&maxResults=25&key=AIzaSyA36In9twLwjcpQYRX3wdp7KEdzGcE_roA&pageToken=", playlistId);
        URL url = new URL(apiCall);
        Root root;
        LOGGER.info(apiCall);
        StringBuilder builder = new StringBuilder(apiCall);
        int apiCallLength = builder.length();

        do {
            root = objectMapper.readValue(url,Root.class);
            root.items.stream().forEach(g -> titlesList.add(g.snippet.getTitle()));
             if((root.nextPageToken != null) && !root.nextPageToken.isEmpty()){
                builder.setLength(apiCallLength);
                builder.append(root.nextPageToken);
                 url = new URL(builder.toString());
             }
        } while ((root.nextPageToken != null) && !root.nextPageToken.isEmpty());
        return titlesList;
    }
}
