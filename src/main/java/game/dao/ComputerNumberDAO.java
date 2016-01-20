package game.dao;

import game.entity.ComputerNumber;
import game.entity.Game;

import java.util.List;

/**
 * Created by Windows on 14.01.2016.
 */
public interface ComputerNumberDAO {

    public Long addNumber(ComputerNumber number);

    public ComputerNumber getNumber(Long numberId);

    public List<ComputerNumber> getNumberList(Game game);
}
