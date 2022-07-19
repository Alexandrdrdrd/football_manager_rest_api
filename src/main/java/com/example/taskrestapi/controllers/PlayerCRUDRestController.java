package com.example.taskrestapi.controllers;


import com.example.taskrestapi.entity.Player;
import com.example.taskrestapi.exception_handling.NoSuchPlayerException;
import com.example.taskrestapi.exception_handling.PlayerIncorrectData;
import com.example.taskrestapi.service.PlayerService;
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
public class PlayerCRUDRestController {
    @Autowired
    PlayerService playerService;

    @GetMapping("/players")
    public List<Player> showAllPlayers(){
        List<Player> allPlayers = playerService.getAllPlayers();
        return allPlayers;
    }

    @GetMapping("/players/{id}")
    public Player getPlayer(@PathVariable int id){
        Player player = playerService.getPlayer(id);
        if(player==null){
            throw new NoSuchPlayerException("There is no player with id = "+
                    id+ " in database");
        }
        System.out.println(player);
        return player;
    }

    @PostMapping("/players")
    public Player addPlayer(@Valid @RequestBody Player player , BindingResult bindingResult, HttpServletResponse response){
        if(bindingResult.hasErrors()){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }else {
        playerService.savePlayer(player);
        return player;
    }}

    @PutMapping("/players")
    public Player updatePlayer(@Valid @RequestBody Player player, BindingResult bindingResult, HttpServletResponse response){
        if(bindingResult.hasErrors()){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }else {
        playerService.savePlayer(player);
        return player;
    }}

@DeleteMapping("/players/{id}")
    public List<Player> deletePlayer(@PathVariable int id){
        playerService.deletePlayer(id);
        return showAllPlayers();
}

    @ExceptionHandler
    public ResponseEntity<PlayerIncorrectData> handleException(NoSuchPlayerException exception){
        PlayerIncorrectData data = new PlayerIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<PlayerIncorrectData> handleException(Exception exception){
        PlayerIncorrectData data = new PlayerIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
