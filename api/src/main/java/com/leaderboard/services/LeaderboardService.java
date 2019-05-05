package com.leaderboard.services;

import com.leaderboard.dto.LeaderboardDTO;
import com.leaderboard.exceptions.LeaderboardException;
import com.leaderboard.models.Leaderboard;
import com.leaderboard.repositories.CamperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaderboardService {

    @Autowired
    private CamperRepository camperRepository;

    public List<LeaderboardDTO> findCamperPointsByLast30Days() {
        try {
            LocalDate date = LocalDate.now().minusDays(30);
            List<Leaderboard> campersData = camperRepository
                    .findPointsFromDateEarned(PageRequest.of(0, 100), Date.valueOf(date));

            List<LeaderboardDTO> dtoList = campersData.stream().map(c -> {
                Leaderboard allTimePoints = camperRepository.findPointsAllTimeForCamper(c.getId());
                LeaderboardDTO dto = LeaderboardDTO.builder()
                        .id(c.getId())
                        .name(c.getName())
                        .last30Days(c.getTotalPoints())
                        .totalPoints(allTimePoints.getTotalPoints())
                        .build();
                return dto;
            }).collect(Collectors.toList());

            return dtoList;
        } catch(Exception e) {
            throw new LeaderboardException();
        }
    }

    public List<LeaderboardDTO> findCamperPointsByAllTime() {
        try {
            LocalDate date = LocalDate.now().minusDays(30);
            List<Leaderboard> campersData = camperRepository.findPointsAllTime(PageRequest.of(0, 100));

            List<LeaderboardDTO> dtoList = campersData.stream().map(c -> {
                Leaderboard last30DaysPoints = camperRepository.findPointsFromDateEarnedForCamper(Date.valueOf(date), c.getId());
                LeaderboardDTO dto = LeaderboardDTO.builder()
                        .id(c.getId())
                        .name(c.getName())
                        .totalPoints(c.getTotalPoints())
                        .last30Days(last30DaysPoints == null ? 0 : last30DaysPoints.getTotalPoints())
                        .build();

                return dto;
            }).collect(Collectors.toList());

            return dtoList;
        } catch (Exception e) {
            throw new LeaderboardException();
        }
    }
}
