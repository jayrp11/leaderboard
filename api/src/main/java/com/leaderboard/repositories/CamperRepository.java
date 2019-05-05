package com.leaderboard.repositories;

import com.leaderboard.models.Camper;
import com.leaderboard.models.Leaderboard;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CamperRepository extends JpaRepository<Camper, Long> {
    @Query("select c.id as id, c.name as name, sum(b.points) as totalPoints " +
            "from Camper c join Brownie b on c.id = b.camper " +
            "where b.dateEarned > :date " +
            "group by c.id " +
            "order by totalPoints desc, c.name asc")
    List<Leaderboard> findPointsFromDateEarned(Pageable page, @Param("date") Date date);

    @Query("select c.id as id, c.name as name, sum(b.points) as totalPoints " +
            "from Camper c join Brownie b on c.id = b.camper " +
            "group by c.id " +
            "order by totalPoints desc, c.name asc")
    List<Leaderboard> findPointsAllTime(PageRequest page);

    @Query("select c.id, c.name as name, sum(b.points) as totalPoints " +
            "from Camper c join Brownie b on c.id = b.camper " +
            "where c.id = :camper " +
            "group by c.id")
    Leaderboard findPointsAllTimeForCamper(@Param("camper") Long id);

    @Query("select c.id as id, c.name as name, sum(b.points) as totalPoints " +
            "from Camper c join Brownie b on c.id = b.camper " +
            "where b.dateEarned > :date and c.id = :camper " +
            "group by c.id ")
    Leaderboard findPointsFromDateEarnedForCamper(@Param("date") Date date, @Param("camper") Long id);
}
