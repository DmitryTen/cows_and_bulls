package game.db.entity;

import game.logic.gamerNumberDefiner.NumberInfo;

import javax.persistence.*;

/**
 * Created by Windows on 14.01.2016.
 */
@Entity
@Table(name = "computer_numbers")
public class ComputerNumber {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
    @Column(name = "index_1")
    private int index1;
    @Column(name = "index_2")
    private int index2;
    @Column(name = "index_3")
    private int index3;
    @Column(name = "index_4")
    private int index4;

    public ComputerNumber(){}

    public ComputerNumber(NumberInfo numberInfo, Game game) {
        this.stepNumber = numberInfo.getStepNumber();
        this.num = numberInfo.getNum();
        this.cowsAmount = numberInfo.getCowsAmount();
        this.bullsAmount = numberInfo.getBullsAmount();
        this.index1 = numberInfo.getIndexOfPosition1();
        this.index2 = numberInfo.getIndexOfPosition2();
        this.index3 = numberInfo.getIndexOfPosition3();
        this.index4 = numberInfo.getIndexOfPosition4();
        this.game = game;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
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

    public int getIndex1() {
        return index1;
    }

    public void setIndex1(int index1) {
        this.index1 = index1;
    }

    public int getIndex2() {
        return index2;
    }

    public void setIndex2(int index2) {
        this.index2 = index2;
    }

    public int getIndex3() {
        return index3;
    }

    public void setIndex3(int index3) {
        this.index3 = index3;
    }

    public int getIndex4() {
        return index4;
    }

    public void setIndex4(int index4) {
        this.index4 = index4;
    }

    public long getGeneralId() {
        return generalId;
    }

    public void setGeneralId(long generalId) {
        this.generalId = generalId;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
