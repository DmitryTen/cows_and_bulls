package game.db.dao;

import game.db.entity.ComputerNumber;
import game.db.entity.Game;

import java.util.List;

/**
 * Created by Windows on 14.01.2016.
 */
public interface ComputerNumberDAO {

    public Long addNumber(ComputerNumber number);

    public ComputerNumber getNumber(Long numberId);

    public List<ComputerNumber> getNumberList(Game game);
}
