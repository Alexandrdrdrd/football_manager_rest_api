package com.example.taskrestapi.service;


import com.example.taskrestapi.dao.PlayerDAO;

import com.example.taskrestapi.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    PlayerDAO playerDAO;

    @Transactional
    @Override
    public List<Player> getAllPlayers() {
        return playerDAO.getAllPlayers();
    }

    @Override
    @Transactional
    public void savePlayer(Player player ) {
        playerDAO.savePlayer(player);
    }

    @Transactional
    @Override
    public Player getPlayer(int playerId) {
        return playerDAO.getPlayer(playerId);
    }

    @Transactional
    @Override
    public void deletePlayer(int playerId) {
        playerDAO.deletePlayer(playerId);
    }
}
