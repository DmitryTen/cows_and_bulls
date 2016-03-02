package game.db.dao;

import game.db.entity.ComputerNumber;
import game.db.entity.Game;

import java.util.List;

/**
 * Created by Windows on 14.01.2016.
 */
public class ComputerNumberDAOImpl extends BaseDAO implements ComputerNumberDAO {

    public Long addNumber(ComputerNumber number) {
        return (Long)template.save(number);
    }

    public ComputerNumber getNumber(Long numberId) {
        return (ComputerNumber)template.load(ComputerNumber.class, numberId);
    }

    public List<ComputerNumber> getNumberList(Game game) {
        return (List<ComputerNumber>) template.find("FROM ComputerNumber c WHERE c.game = ?", game);
    }
}
