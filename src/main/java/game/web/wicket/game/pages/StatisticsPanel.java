package game.web.wicket.game.pages;

import game.logic.TheGame;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * Created by Windows on 15.02.2016.
 */
@AuthorizeInstantiation("USER")
public class StatisticsPanel extends Panel {
    private TheGame theGame;

    public StatisticsPanel(String id){
        super(id);
    }

}
