package com.example.taskrestapi.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Size(min = 2, max = 15)
    @NotBlank()
    @Column(name="name")
    private String TeamName;


    @Column(name="budget")
    private double budget;

    @Min(0)
    @Max(10)
    @Column(name="sales_percentage")
    private int sales_percentage;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "team_id")
    private List<Player> players;

    public List<Team> withdrawFunds(Team sellerTeam, Player player) {
        double playerPrice = player.getExperience() * (100000 / player.getAge());
        double teamPercent = playerPrice / sellerTeam.getSales_percentage();
        double fullPrice = playerPrice+teamPercent;
        sellerTeam.setBudget(sellerTeam.getBudget() + fullPrice);
        this.setBudget(this.getBudget() - fullPrice);
        addPlayerToTeam(player);
        List<Team> teams = new ArrayList<>();
        teams.add(this);
        teams.add(sellerTeam);
        return teams;
    }

    public Team() {
    }

    public Team(String TeamName, double budget, int sales_percentage) {
        this.TeamName = TeamName;
        this.budget = budget;
        this.sales_percentage = sales_percentage;
    }

    public void addPlayerToTeam(Player player) {
        if (players == null) {
            players = new ArrayList<>();
        }
        players.add(player);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String teamName) {
        this.TeamName = teamName;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public int getSales_percentage() {
        return sales_percentage;
    }

    public void setSales_percentage(int sales_percentage) {
        this.sales_percentage = sales_percentage;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", teamName='" + TeamName + '\'' +
                ", budget=" + budget +
                ", sales_percentage=" + sales_percentage +
                '}';
    }
}
