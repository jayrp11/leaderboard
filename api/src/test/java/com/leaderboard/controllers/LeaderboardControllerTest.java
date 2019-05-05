package com.leaderboard.controllers;

import com.leaderboard.dto.LeaderboardDTO;
import com.leaderboard.services.LeaderboardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(LeaderboardController.class)
public class LeaderboardControllerTest {
    @MockBean
    private LeaderboardService leaderboardService;

    @Autowired
    private MockMvc mvc;


    @Test
    public void testFindCampersByAllTime() throws Exception {
        List<LeaderboardDTO> list = new ArrayList<>();
        list.add(LeaderboardDTO.builder().id(1L).name("Test Name 1").totalPoints(5).last30Days(2).build());
        list.add(LeaderboardDTO.builder().id(2L).name("Test Name 2").totalPoints(3).last30Days(1).build());

        given(leaderboardService.findCamperPointsByAllTime()).willReturn(list);

        mvc.perform(get("/api/leaderboard/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Test Name 1")));
    }

    @Test
    public void testFindCampersForLast30Days() throws Exception {
        List<LeaderboardDTO> list = new ArrayList<>();
        list.add(LeaderboardDTO.builder().id(1L).name("Test Name 1").totalPoints(6).last30Days(4).build());
        list.add(LeaderboardDTO.builder().id(2L).name("Test Name 2").totalPoints(9).last30Days(2).build());

        given(leaderboardService.findCamperPointsByLast30Days()).willReturn(list);

        mvc.perform(get("/api/leaderboard/last30days"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].last30Days", is(2)));;
    }
}
