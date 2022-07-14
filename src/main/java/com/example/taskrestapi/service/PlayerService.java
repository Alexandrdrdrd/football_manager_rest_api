package com.example.taskrestapi.service;




import com.example.taskrestapi.entity.Player;

import java.util.List;

public interface PlayerService {
    public List<Player> getAllPlayers();

    public void savePlayer(Player player);

    public Player getPlayer(int playerId);

    public void deletePlayer(int playerId);
}
