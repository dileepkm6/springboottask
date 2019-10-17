package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.NullException;
import com.stackroute.exceptions.TrackAlreadyExistException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.services.TrackService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TrackController
{
    @Autowired
    @Qualifier("trackService")
    private TrackService trackService;
    @ApiOperation("adding new track in database")
    @PostMapping("/add")
    public ResponseEntity<?> save(@RequestBody Track track) throws TrackAlreadyExistException
    {
        ResponseEntity responseEntity;
        if(!trackService.getTrackById(track.getTrackId()))
        {
            trackService.saveTrack(track);
            responseEntity=new ResponseEntity("track created successfully",HttpStatus.CREATED);
        }
        else
        {
            throw new TrackAlreadyExistException("track already exist in database");
        }
        return responseEntity;
    }
    //deleting track from the database
    @ApiOperation("deleting existing track from database")
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody int trackId) throws TrackNotFoundException
    {
        ResponseEntity responseEntity;
        if(!trackService.getTrackById(trackId))
        {
            throw new TrackNotFoundException("given trackId not exist");
        }
        trackService.deleteTrack(trackId);
        responseEntity=new ResponseEntity<String>("Track successfully removed from the database", HttpStatus.OK);
        return responseEntity;
    }
    //displaying all track
    @ApiOperation("displaying all stored track")
    @GetMapping("/getAllTrack")
    public ResponseEntity<?> getAllTrack() throws NullException{
        if(trackService.getAllTrack().size()==0)
        {
            throw new NullException();
        }
        ResponseEntity responseEntity;
        responseEntity = new ResponseEntity<List<Track>>(trackService.getAllTrack(), HttpStatus.OK);
        return responseEntity;
    }
    //updating comment
    @ApiOperation("updating comment of existing track")
    @PutMapping("/updateComment/{trackId}")
    public ResponseEntity<?> updateComment(@PathVariable int trackId,@RequestBody String comment) throws TrackNotFoundException
    {
        ResponseEntity responseEntity;
        if(!trackService.getTrackById(trackId))
        {
            throw new TrackNotFoundException("given trackId not exist");
        }
        Boolean isUpdated=trackService.updateComment(trackId,comment);
        responseEntity = new ResponseEntity<String>("comment updated", HttpStatus.OK);
        return responseEntity;
    }
    @ApiOperation("searching track by trackname")
   @GetMapping("/getByTrackName/{trackName}")
    public List<Track> getByName(@PathVariable String trackName) throws NullException
   {
       if(trackService.getTrackByTrackName(trackName).size()==0)
       {
           throw new NullException();
       }
       return trackService.getTrackByTrackName(trackName);
   }
   
}
