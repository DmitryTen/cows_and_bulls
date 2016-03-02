package game.web.wicket;

import game.web.wicket.pages.GamePanel;
import game.web.wicket.pages.MySignInPage;
import game.web.wicket.pages.RulesPanel;
import game.web.wicket.pages.StatisticsPanel;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Created by Windows on 15.02.2016.
 */
public class BasePage extends WebPage{

    public BasePage(){
        this(new PageParameters());
    }

    public BasePage(PageParameters parameters){
        super(parameters);

        AjaxLink gameLink = new GameLink("game_link");
        AjaxLink statisticsLink = new StatisticsLink("statistics_link");
        AjaxLink rulesLink = new RulesLink("rules_link");

        add(gameLink);
        add(statisticsLink);
        add(rulesLink);

        add(new RulesPanel("content_panel").setOutputMarkupId(true));
    }

    private class GameLink extends AjaxLink{

        private GameLink(String id){
            super(id);
        }

        @Override
        public void onClick(AjaxRequestTarget target) {
            GameSession gameSession = (GameSession)getSession();
            if(gameSession.isSignedIn()){
                Panel replacingPanel = new GamePanel("content_panel", gameSession.getTheGame(), gameSession.getPageModel());
                replacingPanel.setOutputMarkupId(true);
                getParent().replace(replacingPanel);
                target.add(replacingPanel);
            }
            else {
                throw new RestartResponseAtInterceptPageException(MySignInPage.class);
            }
        }
    }

    private class StatisticsLink extends AjaxLink{
        private StatisticsLink(String id){
            super(id);
        }

        @Override
        public void onClick(AjaxRequestTarget target) {
            GameSession gameSession = (GameSession)getSession();
            if(gameSession.isSignedIn()){
                Panel replacingPanel = new StatisticsPanel("content_panel");
                replacingPanel.setOutputMarkupId(true);
                getParent().replace(replacingPanel);
                target.add(replacingPanel);
            }
            else {
                throw new RestartResponseAtInterceptPageException(MySignInPage.class);
            }
        }
    }

    private class RulesLink extends AjaxLink{
        private RulesLink(String id){
            super(id);
        }
        @Override
        public void onClick(AjaxRequestTarget target) {
            Panel replacingPanel = new RulesPanel("content_panel");
            replacingPanel.setOutputMarkupId(true);
            getParent().replace(replacingPanel);
            target.add(replacingPanel);
        }
    }
}

















