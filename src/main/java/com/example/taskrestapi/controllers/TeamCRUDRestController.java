package com.example.taskrestapi.controllers;

import com.example.taskrestapi.entity.Team;
import com.example.taskrestapi.exception_handling.NoSuchTeamException;
import com.example.taskrestapi.exception_handling.PlayerIncorrectData;
import com.example.taskrestapi.exception_handling.TeamIncorrectData;
import com.example.taskrestapi.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TeamCRUDRestController {
    @Autowired
    TeamService teamService;

    @GetMapping("/teams")
    public List<Team> showAllTeams(){
        List<Team> allTeams = teamService.getAllTeams();
        return allTeams;
    }

    @GetMapping("/teams/{id}")
    public Team getTeam(@PathVariable int id){

        Team team = teamService.getTeam(id);
        if(team == null){
            throw new NoSuchTeamException("There is no team with id = "+
                    id+ " in database");
        }
        System.out.println(team);
        return team;
    }

    @PostMapping("/teams")
    public Team addTeam(@Valid @RequestBody Team team, BindingResult bindingResult, HttpServletResponse response){
        if(bindingResult.hasErrors()){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }else {
            teamService.saveTeam(team);
            return team;
        }

    }

    @PutMapping("/teams")
    public Team updateTeam( @Valid @RequestBody Team team, BindingResult bindingResult, HttpServletResponse response){
        if(bindingResult.hasErrors()){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }else {
        teamService.saveTeam(team);
        return team;
    }}

    @DeleteMapping("/teams/{id}")
    public List<Team> deleteTeam(@PathVariable int id){


        teamService.getTeam(id).setPlayers(null);
        teamService.deleteTeam(id);
        return showAllTeams();
    }

    @ExceptionHandler
    public ResponseEntity<TeamIncorrectData> handleException(NoSuchTeamException exception){
        TeamIncorrectData data = new TeamIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler
    public ResponseEntity<TeamIncorrectData> handleException(Exception exception){
        TeamIncorrectData data = new TeamIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }


}

