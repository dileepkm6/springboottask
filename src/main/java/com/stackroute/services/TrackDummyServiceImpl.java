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
@Qualifier("trackDummyService")
//@Profile("trackDummyService")
public class TrackDummyServiceImpl implements TrackService
{
    private TrackRepository trackRepository;
    @Autowired
    public TrackDummyServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistException
    {
        System.out.println("save method in TrackDummyServiceImpl");
        if(trackRepository.existsById(track.getTrackId()))
        {
            throw new TrackAlreadyExistException("Track already exist in database ");
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
        Track trackToChangeCom=trackRepository.findById(trackId).get();
        trackToChangeCom.setComments(comment);
        trackRepository.save(trackToChangeCom);
        return true;
    }

    @Override
    public Track deleteTrack(int trackId) throws TrackNotFoundException
    {
        if(!trackRepository.existsById(trackId))
        {
            throw new TrackNotFoundException("Given trackId is not exist in database");
        }
        trackRepository.deleteById(trackId);
        return trackRepository.findById(trackId).get();
    }

    @Override
    public List<Track> getTrackByTrackName(String trackName) throws TrackNotFoundException
    {
        if(trackRepository.findAll().size()==0)
        {
            throw new TrackNotFoundException("no track found here");
        }
        return trackRepository.getTrackByTrackName(trackName);
    }

}
