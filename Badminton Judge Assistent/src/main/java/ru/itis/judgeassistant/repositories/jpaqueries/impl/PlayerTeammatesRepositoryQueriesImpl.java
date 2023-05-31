package ru.itis.judgeassistant.repositories.jpaqueries.impl;

import org.springframework.stereotype.Repository;
import ru.itis.judgeassistant.models.simpleentities.Player;
import ru.itis.judgeassistant.repositories.jpaqueries.PlayerTeammatesRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PlayerTeammatesRepositoryQueriesImpl implements PlayerTeammatesRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Player> getTeammates(Long playerId) {
        TypedQuery<Player> query = entityManager
                .createQuery("select distinct p from Player p join p.teams t where t.id in " +
                                "(SELECT DISTINCT t.id from Player p join p.teams t where p.id = :player_id) " +
                                                                                "and not (p.id = :player_id)",
                        Player.class);
        return query.setParameter("player_id", playerId).getResultList();
    }
}
