package pl.klewek.springbootmusic.model.youtube;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Thumbnails{

    @JsonProperty("default")
    public Default defaultObj;
    public Medium medium;
    public High high;
    public Standard standard;
    public Maxres maxres;
}
