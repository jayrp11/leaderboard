package com.leaderboard.services;

import com.leaderboard.dto.LeaderboardDTO;
import com.leaderboard.models.Leaderboard;
import com.leaderboard.repositories.CamperRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LeaderboardServiceTest {

    @MockBean
    private CamperRepository camperRepository;

    @Autowired
    private LeaderboardService leaderboardService;

    @Test
    public void testFindCamperPointsByAllTime() {
        List<Leaderboard> list = new ArrayList<>();
        list.add(new LeaderboardImpl(1L, "Test Name 1", 9));
        list.add(new LeaderboardImpl(2L, "Test Name 2", 7));

        Mockito.when(camperRepository.findPointsAllTime(PageRequest.of(0, 100)))
                .thenReturn(list);

        Mockito.when(camperRepository.findPointsFromDateEarnedForCamper(Date.valueOf(LocalDate.now().minusDays(30)), 1L))
                .thenReturn(new LeaderboardImpl(1L, "Test Name 1", 2));
        Mockito.when(camperRepository.findPointsFromDateEarnedForCamper(Date.valueOf(LocalDate.now().minusDays(30)), 1L))
                .thenReturn(new LeaderboardImpl(2L, "Test Name 2", 3));

        List<LeaderboardDTO> campers = leaderboardService.findCamperPointsByAllTime();

        assertThat(campers.size()).isEqualTo(2);
        assertThat(campers.get(1).getName()).isEqualTo("Test Name 2");
    }
}

class LeaderboardImpl implements Leaderboard {
    private Long id;
    private String name;
    private Integer totalPoints;

    LeaderboardImpl(Long id, String name, Integer totalPoints) {
        this.id = id;
        this.name = name;
        this.totalPoints = totalPoints;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getTotalPoints() {
        return totalPoints;
    }
}