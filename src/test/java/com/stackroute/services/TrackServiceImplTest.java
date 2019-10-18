package com.stackroute.services;
import com.stackroute.domain.Track;
import com.stackroute.exceptionhandling.TrackAlreadyExistException;
import com.stackroute.repository.TrackRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
public class TrackServiceImplTest {
    private Track track;
    //Create a mock for UserRepository
    @Mock
    private TrackRepository trackRepository;
    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    private TrackServiceImpl trackService;
    List<Track> list= null;
    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        track=new Track();
        track.setTrackName("John");
        track.setTrackId(101);
        track.setComments("Jenny");
        list = new ArrayList<>();
        list.add(track);
    }
    @Test
    public void saveTrackTestSuccess() throws TrackAlreadyExistException {
        when(trackRepository.save((Track) any())).thenReturn(track);
        Track savedTrack = trackService.saveTrack(track);
        Assert.assertEquals(track,savedTrack);
        //verify here verifies that userRepository save method is only called once
        verify(trackRepository,times(1)).save(track);
    }
    //
//    @Test(expected = MuzixAlreadyExists.class)
    @Test
    public void saveUserTestFailure() throws TrackAlreadyExistException {
        when(trackRepository.save((Track) any())).thenReturn(track);
        Track savedTrack = trackService.saveTrack(track);
        Assert.assertEquals(track,savedTrack);
      /*doThrow(new UserAlreadyExistException()).when(userRepository).findById(eq(101));
      userService.saveUser(user);*/
    }
//    //
    @Test
    public void getAllUser(){
        trackRepository.save(track);
        //stubbing the mock to return specific data
        when(trackRepository.findAll()).thenReturn(list);
        List<Track> trackList = trackService.getAllTrack();
        Assert.assertEquals(list,trackList);
    }
////
////
////    @Test
////    public void getAllMuzix() {
////    }
////

}