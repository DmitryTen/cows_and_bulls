package game.db.dao;

import game.db.entity.Player;

import java.util.List;

/**
 * Created by Windows on 17.01.2016.
 */
public interface PlayerDAO {

    public Long addPlayer(Player player);

    public Player getPlayer(String name);

    public List<Player> getPlayerList();
}
