package game.web.wicket.pages;

import game.logic.TheGame;
import game.web.wicket.models.NumberModel;
import game.web.wicket.models.PageModel;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Windows on 15.02.2016.
 */
@AuthorizeInstantiation("USER")
public class GamePanel extends Panel {

    private TheGame theGame;
    private PageModel pageModel;
    private ListView playerTable;
    private ListView computerTable;

    public GamePanel(String id, final TheGame theGame, final PageModel pageModel){
        super(id);
        this.theGame = theGame;
        this.pageModel = pageModel;

        InnerForm innerForm = new InnerForm("cows_data_form");
        innerForm.setOutputMarkupId(true);
        add(innerForm);

        add(new Label("winner", new PropertyModel(this.pageModel, "winner")));
        add(new Label("proposed_player_number", new PropertyModel(this.pageModel, "proposedPlayerNumber")));
        add(new Label("proposed_computer_number", new PropertyModel(this.pageModel, "proposedComputerNumber")));

        playerTable = new PlayerTable("table_row_1", new PropertyModel<List<NumberModel>>(this.pageModel, "player_list"));
        playerTable.setOutputMarkupId(true);
        computerTable = new PlayerTable("table_row_2", new PropertyModel<List<NumberModel>>(this.pageModel, "computer_list"));
        computerTable.setOutputMarkupId(true);

        add(playerTable);
        add(computerTable);
    }

    private class InnerForm extends Form {
        List<Integer> choice = Arrays.asList(0, 1, 2, 3, 4);
        List<String> choiceCows = Arrays.asList("0 коров", "1 корова", "2 коровы", "3 коровы", "4 коровы");
        List<String> choiceBulls = Arrays.asList("0 быков", "1 бык", "2 быка", "3 быка", "4 быка");

        public InnerForm(String id) {
            super(id);
            add(new Label("player_name", new PropertyModel(pageModel, "player_name")));
            add(new NumberTextField<Integer>("player_number", new PropertyModel<Integer>(pageModel, "player_number")));
            add(new Label("computer_number", new PropertyModel(pageModel, "string_computer_number")));

            RadioGroup<Integer> cowGroup = new RadioGroup<>("cow_group", new PropertyModel<Integer>(pageModel, "cow_group"));
            ListView<Integer> cowList = new ListView<Integer>("cow_list", choice) {

                @Override
                protected void populateItem(ListItem<Integer> item) {
                    item.add(new Radio<>("cow_radio", item.getModel()));
                    item.add(new Label("cow_amount", choiceCows.get(item.getModelObject())));
                }
            };

            RadioGroup<Integer> bullGroup = new RadioGroup<Integer>("bull_group", new PropertyModel<Integer>(pageModel, "bull_group"));
            ListView<Integer> bullList = new ListView<Integer>("bull_list", choice) {

                @Override
                protected void populateItem(ListItem<Integer> item) {
                    item.add(new Radio<>("bull_radio", item.getModel()));
                    item.add(new Label("bull_amount", choiceBulls.get(item.getModelObject())));
                }
            };

            add(cowGroup);
            add(bullGroup);
            cowGroup.add(cowList);
            bullGroup.add(bullList);

            add(new AjaxButton("submit_button") {
                @Override
                protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                    super.onSubmit(target, form);
                    System.out.println("hello");
                    theGame.defineNumberViewsAndPutThemInto(pageModel);
                    target.add(GamePanel.this);
                }
            });

            add(new AjaxButton("restart_game") {
                @Override
                protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                    super.onSubmit(target, form);
                //По какой-то причине новый PageModel - создавать нельзя, решил проблему другим путем. Узнать почему.
                    pageModel.makeZero();
                    theGame.startNewGame(pageModel);

                    target.add(GamePanel.this);
                }
            });
        }
    }



    private class PlayerTable extends ListView<NumberModel>{

        public PlayerTable(String id, final IModel model){
            super(id, model);
        }

        @Override
        protected void populateItem(ListItem<NumberModel> item) {
            NumberModel numberModel = item.getModelObject();
            item.add(new Label("player_name", numberModel.getPlayerName()));
            item.add(new Label("step", numberModel.getStep()));
            item.add(new Label("player_number", numberModel.getStringNumber()));
            item.add(new Label("player_bulls", numberModel.getBulls()));
            item.add(new Label("player_cows", numberModel.getCows()));
        }
    }
}
