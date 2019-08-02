package com.stackroute.muzixapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.muzixapplication.MuzixapplicationApplication;
import com.stackroute.muzixapplication.controller.MusicController;
import com.stackroute.muzixapplication.domain.Music;
import com.stackroute.muzixapplication.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixapplication.exceptions.TrackNotFoundException;
import com.stackroute.muzixapplication.service.MusicService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@RunWith(SpringRunner.class)
public class ControllerTest {
//autowired mockmvc
    @Autowired
    private MockMvc mockMvc;
    //creating music object
    private Music music = new Music();
//creating mock bean for music service
    @MockBean
    private MusicService musicService;
    //inject mock for controller class
    @InjectMocks
    private MusicController musicController;

    //initializing list as null
    private List<Music> list = null;

    @Before
    public void setUp() {
//adding all the values into list
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(musicController).build();
        music.setTrackId(121);
        music.setTrackName("melodies");
        music.setTrackComments("pleasant");
        list = new ArrayList();
        list.add(music);
    }
//testcase for save track
    @Test
    public void saveMusic() throws Exception {
        when(musicService.saveTrack(any())).thenReturn(music);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/save")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(music)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());


    }
//testcase for save track failure
    @Test
    public void saveTrackFailure() throws Exception {
        when(musicService.saveTrack(any())).thenThrow(TrackAlreadyExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/save")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(music)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }
//testcase for get all tracks
    @Test
    public void getAllTracks() throws Exception {
        when(musicService.getAllTracks()).thenReturn(list);
        mockMvc.perform(get("/api/v1/get")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(music)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
//testcase for deleting track
    @Test
    public void deleteTrack() throws Exception{
        when(musicService.deleteTrack(anyInt())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/delete/121")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(music)))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());
    }
//test case for update track
    @Test
    public void updateTrackTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/update/121")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"trackid\": \"121\", \"trackName\" : \"New ToDo Text\", \"trackComment\" : \"false\" }")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
    }




    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}