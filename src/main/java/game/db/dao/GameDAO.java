package game.db.dao;

import game.db.entity.Game;

import java.util.List;

/**
 * Created by Windows on 17.01.2016.
 */
public interface GameDAO {

    public Long addGame(Game game);

    public Game getNumber(Long numberId);

    public List<Game> getGameList();
}
