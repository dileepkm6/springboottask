package com.stackroute.services;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.NullException;
import com.stackroute.exceptions.TrackAlreadyExistException;
import com.stackroute.exceptions.TrackNotFoundException;

import java.util.List;

public interface TrackService
{
    Track saveTrack(Track track) throws TrackAlreadyExistException;
    List<Track> getAllTrack() throws NullException;
    boolean updateComment(int trackId,String comment) throws TrackNotFoundException;
    Track deleteTrack(int trackId) throws TrackNotFoundException;
    List<Track> getTrackByTrackName(String trackName) throws NullException;
    boolean getTrackById(int trackId) throws NullException;
}
