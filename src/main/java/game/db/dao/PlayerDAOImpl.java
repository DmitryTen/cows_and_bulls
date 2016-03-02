package game.db.dao;

import game.db.entity.Player;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;

import java.util.List;

/**
 * Created by Windows on 17.01.2016.
 */
public class PlayerDAOImpl extends BaseDAO implements PlayerDAO {
    @Override
    public Long addPlayer(Player player) {
        return (Long)template.save(player);
    }

    @Override
    public Player getPlayer(String name) {
        List<Player> playerList = (List<Player>) template.findByCriteria(DetachedCriteria.forClass(Player.class)
                .add(Restrictions.eq("playerName", name)));

        return (Player) DataAccessUtils.uniqueResult(playerList);
    }

    @Override
    public List<Player> getPlayerList() {
        return (List<Player>) template.find("FROM Player ORDER BY playerId");
    }
}
