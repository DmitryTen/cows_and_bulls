package game.db.entity;

import javax.persistence.*;

/**
 * Created by Windows on 18.01.2016.
 */
@Entity
@Table(name = "player_numbers")
public class PlayerNumber {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "general_id")
    private long generalId;

    @Column(name = "local_id")
    private int stepNumber;

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

    @Column(name = "num")
    private int num;
    @Column(name = "cows_amount")
    private int cowsAmount;
    @Column(name = "bulls_amount")
    private int bullsAmount;

    public PlayerNumber(){}

    public PlayerNumber(int num, Game game, int stepNumber){
        this.num = num;
        this.game = game;
        this.stepNumber = stepNumber;
    }

    public long getGeneralId() {
        return generalId;
    }

    public void setGeneralId(long generalId) {
        this.generalId = generalId;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getCowsAmount() {
        return cowsAmount;
    }

    public void setCowsAmount(int cowsAmount) {
        this.cowsAmount = cowsAmount;
    }

    public int getBullsAmount() {
        return bullsAmount;
    }

    public void setBullsAmount(int bullsAmount) {
        this.bullsAmount = bullsAmount;
    }
}
