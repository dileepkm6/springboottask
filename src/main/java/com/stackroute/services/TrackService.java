package com.stackroute.services;

import com.stackroute.domain.Track;
import com.stackroute.exceptionhandling.TrackAlreadyExistException;
import com.stackroute.exceptionhandling.TrackNotFoundException;

import java.util.List;

public interface TrackService
{
    Track saveTrack(Track track) throws TrackAlreadyExistException;
    List<Track> getAllTrack();
    boolean updateComment(int trackId,String comment) throws TrackNotFoundException;
    Track deleteTrack(int trackId) throws TrackNotFoundException;
    List<Track> getTrackByTrackName(String trackName) throws TrackNotFoundException;
}
