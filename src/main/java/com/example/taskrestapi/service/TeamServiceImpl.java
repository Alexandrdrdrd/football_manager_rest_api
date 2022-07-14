package com.example.taskrestapi.service;


import com.example.taskrestapi.dao.TeamDAO;
import com.example.taskrestapi.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    TeamDAO teamDAO;

    @Transactional
    @Override
    public List<Team> getAllTeams() {
        return teamDAO.getAllTeams();
    }

    @Override
    @Transactional
    public void saveTeam(Team team) {
        teamDAO.saveTeam(team);
    }

    @Transactional
    @Override
    public Team getTeam(int teamId) {
        return teamDAO.getTeam(teamId);
    }

    @Transactional
    @Override
    public void deleteTeam(int teamId) {
        teamDAO.deleteTeam(teamId);
    }

    @Override
    public void deletePlayerFromTeam(int teamId, int playerId) {
        teamDAO.deletePlayerFromTeam(teamId,playerId);
    }

    @Transactional
    @Override
    public Team addPlayerToTeam(int teamId, int playerId) {
        return teamDAO.addPlayerToTeam(teamId,playerId);
    }
}
