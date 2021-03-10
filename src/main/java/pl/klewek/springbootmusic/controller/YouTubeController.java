package pl.klewek.springbootmusic.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.klewek.springbootmusic.model.YouTubePlaylist;
import pl.klewek.springbootmusic.service.YouTubeService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/youtube")
public class YouTubeController {

    private final static Logger LOGGER = LogManager.getLogger(YouTubeController.class);

    private YouTubeService youTubeService;

    @Autowired
    public YouTubeController(YouTubeService youTubeService) {
        this.youTubeService = youTubeService;
    }

    @GetMapping()
    public String getPlaylist(Model model){
        model.addAttribute("ytPlaylist", new YouTubePlaylist());
        return "homepage";
    }

    @GetMapping("/playlist")
    public String getYoutubePlaylist(@ModelAttribute("ytPlaylist") YouTubePlaylist youTubePlaylist, Model model){
        String playlistId = youTubeService.getPlaylistId(youTubePlaylist.getUrl());
        LOGGER.info(playlistId);
//        try {
//            youTubePlaylist.setItemsList(youTubeService.getTitlesFromPlaylist(playlistId));
            model.addAttribute("ytPlaylist", playlistId);
//            LOGGER.info(youTubePlaylist.getItemsList());
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        } catch (JsonParseException e) {
//            e.printStackTrace();
//        }
        return "index";
    }

    @RequestMapping("/to_spotify/{id}")
    public String getYoutubeToSpotify(@PathVariable String id){
//        LOGGER.info("spotify: " + youTubePlaylist.getItemsList());
        LOGGER.info("spotify playlist id: " + id);
        return "homepage";
    }

    @GetMapping("/to_file/{id}")
    public String getYoutubeToFile(@PathVariable String id){
        LOGGER.info("file: " + id);
        return null;
    }
}
