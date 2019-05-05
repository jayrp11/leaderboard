package com.leaderboard.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LeaderboardDTO {
    private Long id;
    private String name;
    private Integer totalPoints;
    private Integer last30Days;
}
