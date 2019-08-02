package com.stackroute.muzixapplication.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;


//created entity annotation
//@Entity
//createdd document annotation table creation
@Document(collection = "Music")
@Data //annotated as data for getters and setters
@NoArgsConstructor //annotated as noargsconstructor for default constructor
@AllArgsConstructor //annotated as allargsconstructor for parameterized constructor with all fields
//@ApiModel(description = "All details about the Music tracks. ")
public class Music {
    @Id
   // @ApiModelProperty(notes = "The database generated track ID")
    int trackId; //annotated as id to create id as primary key
   // @ApiModelProperty(notes = "The database generated track Name")
    String trackName; //created trackname and track comments
  //  @ApiModelProperty(notes = "The database generated track Comments")
    String trackComments;
}
