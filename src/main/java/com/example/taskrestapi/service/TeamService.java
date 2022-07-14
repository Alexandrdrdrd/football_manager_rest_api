package com.example.taskrestapi.service;

import com.example.taskrestapi.entity.Team;

import java.util.List;

public interface TeamService {
    public List<Team> getAllTeams();

    public void saveTeam(Team team);

    public Team getTeam(int teamId);

    public void deleteTeam(int teamId);

    public Team addPlayerToTeam(int teamId, int playerId);

    public void deletePlayerFromTeam(int teamId, int playerId);
}
