package com.stackroute.services;

import com.stackroute.domain.Track;
import com.stackroute.exceptionhandling.TrackAlreadyExistException;
import com.stackroute.exceptionhandling.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
//@Primary
@Qualifier("trackService")
//@Profile({"trackService","default"})
public class TrackServiceImpl implements TrackService
{
    private TrackRepository trackRepository;
    @Autowired
    public TrackServiceImpl( TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistException {

        if(trackRepository.existsById(track.getTrackId()))
        {
            throw new TrackAlreadyExistException("track already exist");
        }
        Track savedTrack=trackRepository.save(track);
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
            throw new TrackNotFoundException();
        }
        Track trackToChangeCom=trackRepository.getOne(trackId);
        trackToChangeCom.setComments(comment);
        trackRepository.save(trackToChangeCom);
        return true;
    }

    @Override
    public Track deleteTrack(int trackId) throws TrackNotFoundException
    {
        Track track=trackRepository.getOne(trackId);

        if(!trackRepository.existsById(trackId))
        {
            throw new TrackNotFoundException();
        }
        trackRepository.deleteById(trackId);
        return track;

    }

    @Override
    public List<Track> getTrackByTrackName(String trackName) throws TrackNotFoundException
    {
        if(trackRepository.findAll().size()==0)
        {
            throw new TrackNotFoundException();
        }
        return trackRepository.getTrackByTrackName(trackName);
    }

    @Override
    public boolean getTrackById(int trackId) throws TrackNotFoundException
    {
        if(!trackRepository.existsById(trackId))
        {
            throw new TrackNotFoundException();
        }
        return trackRepository.existsById(trackId);
    }
}
