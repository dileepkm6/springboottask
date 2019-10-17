package com.stackroute.services;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.NullException;
import com.stackroute.exceptions.TrackAlreadyExistException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("trackDummyService")
public class TrackDummyServiceImpl implements TrackService
{
    private TrackRepository trackRepository;
    @Autowired
    public TrackDummyServiceImpl(TrackRepository trackRepository) {

        System.out.println("TrackDummyServiceImpl");
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
            throw new NullException("no track exist in database");
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
    public List<Track> getTrackByTrackName(String trackName) throws NullException
    {
        if(trackRepository.findAll().size()==0)
        {
            throw new NullException("no track exist in database");
        }
        return trackRepository.getTrackByTrackName(trackName);
    }
}
