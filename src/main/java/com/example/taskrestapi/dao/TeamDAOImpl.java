package com.example.taskrestapi.dao;

import com.example.taskrestapi.entity.Player;
import com.example.taskrestapi.entity.Team;
import com.example.taskrestapi.service.PlayerService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TeamDAOImpl implements TeamDAO {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PlayerService playerService;

    @Override
    public List<Team> getAllTeams() {
        Query query = entityManager.createQuery("from Team");
        List<Team> allTeams = query.getResultList();
        return allTeams;
    }

    @Override
    public void saveTeam(Team team) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(team);
    }

    @Override
    public Team getTeam(int teamId) {

        Team team = entityManager.find(Team.class, teamId);
        return team;
    }

    @Override
    public void deleteTeam(int teamId) {
        Query query = entityManager.createQuery("delete from Team where id =: teamId");
        query.setParameter("teamId", teamId);
        query.executeUpdate();
    }

    @Override
    public void deletePlayerFromTeam(int teamId, int playerId) {
        Team team = getTeam(teamId);
        List<Player> players = team.getPlayers();
        for (Player player : players) {
            if (player.getId() == playerId) {
                players.remove(player);
                break;
            }
        }
        saveTeam(team);
    }

    @Override
    public Team addPlayerToTeam(int teamId, int playerId) {
        Team team = getTeam(teamId);
        Player player = playerService.getPlayer(playerId);
        team.addPlayerToTeam(player);
        return team;
    }
}
