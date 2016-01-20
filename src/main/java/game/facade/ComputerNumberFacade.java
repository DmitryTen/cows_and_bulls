package game.facade;

import game.dao.ComputerNumberDAO;
import game.entity.ComputerNumber;
import game.entity.Game;
import game.view.NumberView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Windows on 15.01.2016.
 */
public class ComputerNumberFacade {

    private ComputerNumberDAO numberDAO;

    public void setNumberDAO(ComputerNumberDAO numberDAO) {
        this.numberDAO = numberDAO;
    }

    public Long addNumber(ComputerNumber computerNumber){
        return numberDAO.addNumber(computerNumber);
    }

    public List<ComputerNumber> findNumber(Game game){
        return numberDAO.getNumberList(game);
    }

    public List<NumberView> getViewList(Game game){
        List<NumberView> numberViewList = new ArrayList<>();

        List<ComputerNumber> computerNumberList = findNumber(game);
        for (int i=0; i<computerNumberList.size(); i++){
            numberViewList.add(new NumberView(computerNumberList.get(i)));
        }
        return numberViewList;
    }

}
