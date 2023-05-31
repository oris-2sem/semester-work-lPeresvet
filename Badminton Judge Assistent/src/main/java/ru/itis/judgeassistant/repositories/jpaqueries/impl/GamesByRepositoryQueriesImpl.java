package ru.itis.judgeassistant.repositories.jpaqueries.impl;

import org.springframework.stereotype.Repository;
import ru.itis.judgeassistant.models.Game;
import ru.itis.judgeassistant.repositories.jpaqueries.GamesByRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class GamesByRepositoryQueriesImpl implements GamesByRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Game> getGamesByUserId(Long userId) {
        TypedQuery<Game> query = entityManager
                .createQuery("SELECT g FROM Player p JOIN p.teams t JOIN t.games g WHERE p.id = :user_id",
                        Game.class);
        //TODO: отптимизировать запрос(если останется время)
        return query.setParameter("user_id", userId).getResultList();
    }
}
