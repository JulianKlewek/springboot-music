package pl.klewek.springbootmusic.model.youtube;

import lombok.Data;

import java.util.Date;

@Data
public class Snippet{
    public Date publishedAt;
    public String channelId;
    public String title;
    public String description;
    public Thumbnails thumbnails;
    public String channelTitle;
    public String playlistId;
    public int position;
    public ResourceId resourceId;
    public String videoOwnerChannelTitle;
    public String videoOwnerChannelId;
}
