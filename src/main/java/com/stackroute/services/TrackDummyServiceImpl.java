package com.stackroute.services;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.NullException;
import com.stackroute.exceptions.TrackAlreadyExistException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
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
    public List<Track> getAllTrack() throws NullException
    {
        List<Track> allTrack=trackRepository.findAll();
        if(allTrack.size()==0)
        {
            throw new NullException();
        }
        return allTrack;
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
    public Track deleteTrack(int trackId) throws TrackNotFoundException
    {
        if(!trackRepository.existsById(trackId))
        {
            throw new TrackNotFoundException("Given trackId is not exist in database");
        }
        trackRepository.deleteById(trackId);
        return trackRepository.getOne(trackId);
    }

    @Override
    public List<Track> getTrackByTrackName(String trackName) throws NullException
    {
        if(trackRepository.findAll().size()==0)
        {
            throw new NullException();
        }
        return trackRepository.getTrackByTrackName(trackName);
    }
    @Override
    public boolean getTrackById(int trackId) throws NullException
    {
        if(!trackRepository.existsById(trackId))
        {
            throw new NullException();
        }
        return trackRepository.existsById(trackId);
    }
}
