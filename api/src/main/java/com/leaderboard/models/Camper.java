package com.leaderboard.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Camper {
    @Id
    @GeneratedValue
    private long id;

    private String name;
}
