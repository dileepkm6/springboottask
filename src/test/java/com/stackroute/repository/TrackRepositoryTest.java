package com.stackroute.repository;


import com.stackroute.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TrackRepositoryTest {

    @Autowired
    private TrackRepository trackRepository;
    private Track track;

    @Before
    public void setUp()
    {
        track = new Track();
        track.setTrackId(101);
        track.setTrackName("application");
        track.setComments("spring");

    }

    @After
    public void tearDown(){

        trackRepository.deleteAll();
    }


    @Test
    public void testSaveUser(){
        trackRepository.save(track);
        Track fetchUser = trackRepository.findById(track.getTrackId()).get();
        Assert.assertEquals(101,fetchUser.getTrackId());

    }

    @Test
    public void testSaveUserFailure(){
        Track testUser = new Track(101,"Harry","john");
        trackRepository.save(track);
        Track fetchUser = trackRepository.findById(track.getTrackId()).get();
        Assert.assertNotSame(testUser,track);
    }

    @Test
    public void testGetAllUser(){
        Track u = new Track(102,"Harry","john");
        Track u1 = new Track(103,"Harrry","jenny");
        trackRepository.save(u);
        trackRepository.save(u1);

        List<Track> list = trackRepository.findAll();
        Assert.assertEquals("Harry",list.get(0).getTrackName());


    }


}
