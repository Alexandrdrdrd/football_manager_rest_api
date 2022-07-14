package com.example.taskrestapi.controllers;


import com.example.taskrestapi.entity.Player;
import com.example.taskrestapi.entity.Team;
import com.example.taskrestapi.service.PlayerService;
import com.example.taskrestapi.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FootballManagerController {
    @Autowired
    PlayerService playerService;

    @Autowired
    TeamService teamService;

    @GetMapping("/add_player_to_team/{teamId}/{playerId}")
    public Team addPlayerToTeam(@PathVariable int teamId, @PathVariable int playerId) {
        teamService.addPlayerToTeam(teamId, playerId);
        return teamService.getTeam(teamId);
    }

    @DeleteMapping("/delete_player_from_team/{teamId}/{playerId}")
    public Team deletePlayerFromTeam(@PathVariable int teamId, @PathVariable int playerId) {
        teamService.deletePlayerFromTeam(teamId, playerId);
        return teamService.getTeam(teamId);
    }

    @GetMapping("transfer_player/{playerId}/from/{sellerId}/to/{customerId}")
    public Team transferPlayer(@PathVariable int customerId, @PathVariable int playerId, @PathVariable int sellerId){
        Team seller = teamService.getTeam(sellerId);
        Team customer = teamService.getTeam(customerId);
        Player player = playerService.getPlayer(playerId);
        List<Team> teams = customer.withdrawFunds(seller, player);
        teamService.saveTeam(teams.get(0));
        teamService.saveTeam(teams.get(1));
        return teamService.getTeam(customerId);
    }
}
