package com.example.taskrestapi.dao;


import com.example.taskrestapi.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class PlayerDAOImpl implements PlayerDAO {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Player> getAllPlayers() {
        // hibernate query
//        Session session = entityManager.unwrap(Session.class);
//        Query<Employee> query = session.createQuery("from Player ", Player.class);
//        List<Employee> allPlayers = query.getResultList();

//JPA query
        Query query = entityManager.createQuery("from Player");
        List<Player> allPlayers = query.getResultList();

        return allPlayers;
    }

    @Override
    public void savePlayer(Player player) {
        // hibernate query
//        Session session = entityManager.unwrap(Session.class);
//        session.saveOrUpdate(player);

        //JPA query

        Player newplayer = entityManager.merge(player);
        player.setId(newplayer.getId());
    }

    @Override
    public Player getPlayer(int playerId) {
        // hibernate query
//        Session session = entityManager.unwrap(Session.class);
//         Player player = session.get(Player.class, empId);

        //JPA query
        Player player = entityManager.find(Player.class, playerId);
        return player;
    }

    @Override
    public void deletePlayer(int playerId) {
//        Session session = sessionFactory.getCurrentSession();
//        Employee employee = session.get(Player.class, empId);
//        session.delete(player);

        // hibernate query
//        Session session = entityManager.unwrap(Session.class);
//        Query<Player> query = session.createQuery("delete from Player where id =: playerId");
//        query.setParameter("playerId", playerId);
//        query.executeUpdate();

        //JPA query

        Query query = entityManager.createQuery("delete from Player where id =: playerId");
        query.setParameter("playerId", playerId);
        query.executeUpdate();
    }
}
