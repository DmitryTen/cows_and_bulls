package game.db;

import game.db.dao.ComputerNumberDAO;
import game.db.dao.GameDAO;
import game.db.dao.PlayerDAO;
import game.db.dao.PlayerNumberDAO;
import game.db.entity.ComputerNumber;
import game.db.entity.Game;
import game.db.entity.Player;
import game.db.entity.PlayerNumber;

/**
 * Created by Windows on 17.02.2016.
 */
public class GamePageFacade {
    private ComputerNumberDAO computerNumberDAO;
    private PlayerNumberDAO playerNumberDAO;
    private GameDAO gameDAO;
    private PlayerDAO playerDAO;

    public Player getPlayer(String name){
        return playerDAO.getPlayer(name);
    }

    public void addPlayer(String login, String password){
        playerDAO.addPlayer(new Player(login, password));
    }

    public void addGame(Game game){
        gameDAO.addGame(game);
    }

    public void addComputerNumber(ComputerNumber computerNumber){
        computerNumberDAO.addNumber(computerNumber);
    }

    public void addPlayerNumber(PlayerNumber playerNumber){
        playerNumberDAO.addNumber(playerNumber);
    }

    public ComputerNumberDAO getComputerNumberDAO() {
        return computerNumberDAO;
    }

    public void setComputerNumberDAO(ComputerNumberDAO computerNumberDAO) {
        this.computerNumberDAO = computerNumberDAO;
    }

    public PlayerNumberDAO getPlayerNumberDAO() {
        return playerNumberDAO;
    }

    public void setPlayerNumberDAO(PlayerNumberDAO playerNumberDAO) {
        this.playerNumberDAO = playerNumberDAO;
    }

    public GameDAO getGameDAO() {
        return gameDAO;
    }

    public void setGameDAO(GameDAO gameDAO) {
        this.gameDAO = gameDAO;
    }

    public PlayerDAO getPlayerDAO() {
        return playerDAO;
    }

    public void setPlayerDAO(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }
}
