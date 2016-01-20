package game.facade;

import game.dao.GameDAO;
import game.entity.Game;

import java.util.List;

/**
 * Created by Windows on 17.01.2016.
 */
public class GameFacade {

    private GameDAO gameDAO;

    public void setGameDAO(GameDAO gameDAO) {
        this.gameDAO = gameDAO;
    }

    public Long addGame(Game game) {
        return gameDAO.addGame(game);
    }

    public List<Game> getGameList(){
       return gameDAO.getGameList();
    }
}
