package game.facade;

import game.dao.PlayerNumberDAO;
import game.entity.Game;
import game.entity.PlayerNumber;
import game.view.NumberView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Windows on 18.01.2016.
 */
public class PlayerNumberFacade {

    private PlayerNumberDAO numberDAO;

    public void setNumberDAO(PlayerNumberDAO numberDAO) {
        this.numberDAO = numberDAO;
    }

    public Long addNumber(PlayerNumber computerNumber){
        return numberDAO.addNumber(computerNumber);
    }

    public List<PlayerNumber> findNumber(Game game){
        return numberDAO.getNumberList(game);
    }


    public List<NumberView> getViewList(Game game){
        List<NumberView> numberViewList = new ArrayList<>();

        List<PlayerNumber> playerNumberList = findNumber(game);
        for (int i=0; i<playerNumberList.size(); i++){
            numberViewList.add(new NumberView(playerNumberList.get(i)));
        }
        return numberViewList;
    }

}
