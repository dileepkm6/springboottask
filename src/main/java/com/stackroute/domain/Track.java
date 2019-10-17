package com.stackroute.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Track
{
    @Id
    @ApiModelProperty("ID of Track")
    private int trackId;
    @ApiModelProperty("Name of Track")
    private String trackName;
    @ApiModelProperty("Comment of Track")
    private  String comments;
}
