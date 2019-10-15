package com.stackroute.services;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TrackServiceImpl implements TrackService
{
    private TrackRepository trackRepository;
    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistException
    {
        if(trackRepository.existsById(track.getTrackId()))
        {
            throw new TrackAlreadyExistException("Track already exist in database");
        }
        Track savedTrack=trackRepository.save(track);
        if(savedTrack==null)
        {
            throw new TrackAlreadyExistException("Track already exist in database");
        }
        return savedTrack;
    }

    @Override
    public List<Track> getAllTrack()
    {
        return trackRepository.findAll();
    }

    @Override
    public boolean updateComment(int trackId,String comment) throws TrackNotFoundException
    {

        if(!trackRepository.existsById(trackId))
        {
            throw new TrackNotFoundException("Given trackId is not exist in database");
        }
        Track trackToChangeCom=trackRepository.getOne(trackId);
        trackToChangeCom.setComments(comment);
        trackRepository.save(trackToChangeCom);
        return true;
    }

    @Override
    public void deleteTrack(int trackId) throws TrackNotFoundException
    {
        if(trackRepository.existsById(trackId))
        {
            trackRepository.deleteById(trackId);
        }
        else
        {
            throw new TrackNotFoundException("Given trackId is not exist in database");
        }
    }

    @Override
    public List<Track> getTrackByTrackName(String trackName) {
        return trackRepository.getTrackByTrackName(trackName);
    }
}
