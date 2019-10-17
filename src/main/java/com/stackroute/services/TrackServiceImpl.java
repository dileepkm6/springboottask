package com.stackroute.services;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.NullException;
import com.stackroute.exceptions.TrackAlreadyExistException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
//@Primary
@Qualifier("trackService")
//@Profile({"trackService","default"})
public class TrackServiceImpl implements TrackService
{
    @Autowired
    private TrackRepository trackRepository;
    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track)
    {
        return trackRepository.save(track);
    }

    @Override
    public List<Track> getAllTrack()
    {
        return trackRepository.findAll();
    }

    @Override
    public boolean updateComment(int trackId,String comment)
    {
        Track trackToChangeCom=trackRepository.getOne(trackId);
        trackToChangeCom.setComments(comment);
        trackRepository.save(trackToChangeCom);
        return true;
    }

    @Override
    public void deleteTrack(int trackId)
    {
        trackRepository.deleteById(trackId);
    }

    @Override
    public List<Track> getTrackByTrackName(String trackName)
    {
        return trackRepository.getTrackByTrackName(trackName);
    }
    @Override
    public boolean getTrackById(int trackId)
    {

        return trackRepository.existsById(trackId);
    }
}
