package game.dao;

import game.entity.Game;

import java.util.List;

/**
 * Created by Windows on 17.01.2016.
 */
public class GameDAOImpl extends BaseDAO implements GameDAO {

    @Override
    public Long addGame(Game game) {
        return (Long) template.save(game);
    }

    @Override
    public Game getNumber(Long numberId) {
        return (Game)template.load(Game.class, numberId);
    }

    @Override
    public List<Game> getGameList() {
        return (List<Game>)template.find("FROM Game ORDER BY gameId");
    }
}
