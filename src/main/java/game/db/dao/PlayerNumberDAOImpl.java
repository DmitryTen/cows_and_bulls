package game.db.dao;

import game.db.entity.Game;
import game.db.entity.PlayerNumber;

import java.util.List;

/**
 * Created by Windows on 18.01.2016.
 */


public class PlayerNumberDAOImpl extends BaseDAO implements PlayerNumberDAO {

    public Long addNumber(PlayerNumber number) {
        return (Long)template.save(number);
    }

    public PlayerNumber getNumber(Long numberId) {
        return (PlayerNumber)template.load(PlayerNumber.class, numberId);
    }

    public List<PlayerNumber> getNumberList(Game game) {
        return (List<PlayerNumber>) template.find("FROM PlayerNumber p WHERE p.game = ?", game);
    }
}
