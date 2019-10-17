package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.exceptionhandling.TrackAlreadyExistException;
import com.stackroute.exceptionhandling.TrackNotFoundException;
import com.stackroute.services.TrackService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TrackController
{


    private TrackService trackService;
    @Autowired
    public TrackController(@Qualifier("trackService") TrackService trackService) {
        this.trackService = trackService;
    }
    @ApiOperation("adding new track in database")
    @PostMapping("/add")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) throws TrackAlreadyExistException
    {
        trackService.saveTrack(track);
        return new ResponseEntity("created successfully",HttpStatus.CREATED);
    }
    //deleting track from the database
    @ApiOperation("deleting existing track from database")
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTrack(@RequestBody int trackId) throws TrackNotFoundException
    {
        trackService.deleteTrack(trackId);
        return new ResponseEntity<String>("track deleted successfully", HttpStatus.OK);
    }
    //displaying all track
    @ApiOperation("displaying all stored track")
    @GetMapping("/getAllTrack")
    public ResponseEntity<?> getAllTrack() {
        return new ResponseEntity<List<Track>>(trackService.getAllTrack(), HttpStatus.OK);
    }
    //updating comment
    @ApiOperation("updating comment of existing track")
    @PutMapping("/updateComment/{trackId}")
    public ResponseEntity<?> updateComment(@PathVariable int trackId,@RequestBody String comment) throws TrackNotFoundException
    {
        trackService.updateComment(trackId,comment);
        return new ResponseEntity<String>("comment updated successfully", HttpStatus.OK);
    }
    @ApiOperation("searching track by trackname")
   @GetMapping("/getByTrackName/{trackName}")
    public List<Track> getByName(@PathVariable String trackName) throws TrackNotFoundException
   {
       return trackService.getTrackByTrackName(trackName);
   }

}
