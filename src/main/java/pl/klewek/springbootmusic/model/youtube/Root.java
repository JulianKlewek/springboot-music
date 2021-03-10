package pl.klewek.springbootmusic.model.youtube;

import java.util.List;

public class Root{
    public String kind;
    public String etag;
    public String nextPageToken;
    public String prevPageToken;
    public List<Item> items;
    public PageInfo pageInfo;
}
