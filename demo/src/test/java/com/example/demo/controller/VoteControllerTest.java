package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.entity.Vote;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.VoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VoteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private VoteService voteService;

    @InjectMocks
    private VoteController voteController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(voteController).build();
    }

    @Test
    void testCastVote() throws Exception {
        Vote vote = new Vote();
        Mockito.when(voteService.castVote(1L, 1L)).thenReturn(vote);

        mockMvc.perform(post("/api/votes/cast")
                        .param("userId", "1")
                        .param("menuItemId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetVotesByMenuItemId() throws Exception {
        Vote vote1 = new Vote();
        Vote vote2 = new Vote();
        List<Vote> votes = Arrays.asList(vote1, vote2);

        Mockito.when(voteService.getVotesByMenuItemId(any())).thenReturn(votes);

        mockMvc.perform(get("/api/votes/menu/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testGetVotesByUserId() throws Exception {
        Vote vote1 = new Vote();
        Vote vote2 = new Vote();
        List<Vote> votes = Arrays.asList(vote1, vote2);

        Mockito.when(voteService.getVotesByUserId(1L)).thenReturn(votes);

        mockMvc.perform(get("/api/votes/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testGetVoteById() throws Exception {
        Vote vote = new Vote();
        Mockito.when(voteService.getVoteById(1L)).thenReturn(Optional.of(vote));

        mockMvc.perform(get("/api/votes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testDeleteVote() throws Exception {
        mockMvc.perform(delete("/api/votes/1"))
                .andExpect(status().isNoContent());

        Mockito.verify(voteService).deleteVote(1L);
    }
}

