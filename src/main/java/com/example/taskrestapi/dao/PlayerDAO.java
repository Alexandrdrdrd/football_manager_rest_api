package com.example.taskrestapi.dao;


import com.example.taskrestapi.entity.Player;

import java.util.List;

public interface PlayerDAO {
    public List<Player> getAllPlayers();

    public void savePlayer(Player player);

    public Player getPlayer (int playerId);

    void deletePlayer(int playerId);

}
