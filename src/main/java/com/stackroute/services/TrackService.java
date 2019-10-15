package com.stackroute.services;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistException;
import com.stackroute.exceptions.TrackNotFoundException;

import java.util.List;

public interface TrackService
{
    public Track saveTrack(Track track) throws TrackAlreadyExistException;
    public List<Track> getAllTrack();
    public boolean updateComment(int trackId,String comment) throws TrackNotFoundException;
    public void deleteTrack(int trackId) throws TrackNotFoundException;
    public List<Track> getTrackByTrackName(String trackName);
}
