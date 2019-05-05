package com.leaderboard.controllers;

import com.leaderboard.dto.LeaderboardDTO;
import com.leaderboard.services.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LeaderboardController {
    @Autowired
    private LeaderboardService leaderboardService;

    @GetMapping("/api/leaderboard/all")
    public List<LeaderboardDTO> findCampersByAllTime() {
        return leaderboardService.findCamperPointsByAllTime();
    }

    @GetMapping("/api/leaderboard/last30days")
    public List<LeaderboardDTO> findCampersForLast30Days() {
        return leaderboardService.findCamperPointsByLast30Days();
    }
}
