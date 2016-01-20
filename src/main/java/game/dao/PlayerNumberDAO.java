package game.dao;

import game.entity.Game;
import game.entity.PlayerNumber;

import java.util.List;

/**
 * Created by Windows on 18.01.2016.
 */
public interface PlayerNumberDAO {

    public Long addNumber(PlayerNumber number);

    public PlayerNumber getNumber(Long numberId);

    public List<PlayerNumber> getNumberList(Game game);
}
