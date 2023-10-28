
package com.spotify.pojo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)


@Getter @Setter
public class Playlist {

    @JsonProperty("href")
    private String href;
    @JsonProperty("items")
    private List<Item> items;
    @JsonProperty("limit")
    private Integer limit;
    @JsonProperty("next")
    private Object next;
    @JsonProperty("offset")
    private Integer offset;
    @JsonProperty("previous")
    private Object previous;
    @JsonProperty("total")
    private Integer total;

   

}
