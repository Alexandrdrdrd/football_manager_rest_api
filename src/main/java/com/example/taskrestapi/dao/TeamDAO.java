package com.example.taskrestapi.dao;

import com.example.taskrestapi.entity.Player;
import com.example.taskrestapi.entity.Team;

import java.util.List;

public interface TeamDAO {
    public List<Team> getAllTeams();

    public void saveTeam(Team team);

    public Team getTeam (int teamId);

    public void deleteTeam(int teamId);

    public Team addPlayerToTeam(int teamId, int playerId);

    void deletePlayerFromTeam(int teamId, int playerId);
}
