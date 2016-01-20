package game.facade;

import game.dao.PlayerDAO;
import game.entity.Player;

import java.util.List;

/**
 * Created by Windows on 17.01.2016.
 */
public class PlayerFacade {

    private PlayerDAO playerDAO;

    public void setPlayerDAO(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    public Long addGamer(Player player){
        return playerDAO.addPlayer(player);
    }

    public Player getGamer(String name){
        return playerDAO.getPlayer(name);
    }

    public List<Player> findGamer(){
        return playerDAO.getPlayerList();
    }
}
