package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.services.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TrackController
{
    @Autowired
    private TrackService trackService;
    @PostMapping("/add")
    public ResponseEntity<?> save(@RequestBody Track track)
    {
        ResponseEntity responseEntity;
        try
        {
            trackService.saveTrack(track);
            responseEntity=new ResponseEntity<String>("Track successfully saved", HttpStatus.CREATED);
        }
        catch (TrackAlreadyExistException e)
        {
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    //deleting track from the database
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody int trackId)
    {
        ResponseEntity responseEntity;
        try
        {
            trackService.deleteTrack(trackId);
            responseEntity=new ResponseEntity<String>("Track successfully removed from the database", HttpStatus.OK);
        }
        catch (TrackNotFoundException e)
        {
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    //displaying all track
    @GetMapping("/getAllTrack")
    public ResponseEntity<?> getAllTrack() {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<List<Track>>(trackService.getAllTrack(), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    //updating comment
    @PostMapping("/updateComment/{trackId}")
    public ResponseEntity<?> updateComment(@PathVariable int trackId,@RequestBody String comment) {
        ResponseEntity responseEntity;

        try
        {
            Boolean isUpdated=trackService.updateComment(trackId,comment);
            responseEntity = new ResponseEntity<String>("comment updated", HttpStatus.OK);
        }
       catch (TrackNotFoundException e)
        {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }

        return responseEntity;
    }
   @GetMapping("/getByTrackName/{trackName}")
    public List<Track> getByName(@PathVariable String trackName)
   {
       return trackService.getTrackByTrackName(trackName);
   }
}
