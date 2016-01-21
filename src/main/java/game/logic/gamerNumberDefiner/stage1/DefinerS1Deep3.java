package game.logic.gamerNumberDefiner.stage1;

import game.logic.gamerNumberDefiner.NumberDefiner;
import game.logic.gamerNumberDefiner.NumberInfo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Windows on 22.12.2015.
 */
public class DefinerS1Deep3 extends DefinerS1 {

    private int stepNumberDeep3;

    public DefinerS1Deep3(Integer[] rndSecuence,
                          ArrayList<NumberInfo> numbersHistory,
                          HashMap<Integer, Boolean> definedDigits,
                          int stepNumberDeep3,
                          NumberDefiner numberDefiner)  {
        super(rndSecuence, numbersHistory, definedDigits, numberDefiner);
        this.stepNumberDeep3 = stepNumberDeep3;
    }

    @Override
    public int getNumber() {
        if(stepNumberDeep3 <=
                numbersHistory.size()-1) return handleResults();
        else return getNumberFromCurrentDefiner();
    }

    private int handleResults() {
        if (getRes(stepNumberDeep3)==3){
            putDefinedDigit(x1x2_X1[1], true);
            putDefinedDigit(x1x2_X1[2], false);
            putDefinedDigit(y1y2_Y1[1], true);
            putDefinedDigit(y1y2_Y1[2], false);
            putDefinedDigit(z1z2_Z1[1], true);
            putDefinedDigit(z1z2_Z1[2], false);
        }

        if (getRes(stepNumberDeep3)==1){
            putDefinedDigit(x1x2_X1[1], false);
            putDefinedDigit(x1x2_X1[2], true);
            putDefinedDigit(y1y2_Y1[1], false);
            putDefinedDigit(y1y2_Y1[2], true);
            putDefinedDigit(z1z2_Z1[1], false);
            putDefinedDigit(z1z2_Z1[2], true);
        }

        toLogResDefinedDigits("5");
        numberDefiner.setStage(2);
        return numberDefiner.getNumber();

    }

    private int getNumberFromCurrentDefiner() {
        if (x1x2_X1[0]==1 && y1y2_Y1[0]==1){
            if (z1z2_Z1[0]==1) return defineResults111(z1z2_Z1, x1x2_X1, y1y2_Y1);
            return defineResults11(x1x2_X1, y1y2_Y1, z1z2_Z1);
        }

        if (y1y2_Y1[0]==1 && z1z2_Z1[0]==1){
            return defineResults11(y1y2_Y1, z1z2_Z1, x1x2_X1);
        }

        if (z1z2_Z1[0]==1 && x1x2_X1[0]==1){
            return defineResults11(z1z2_Z1, x1x2_X1, y1y2_Y1);
        }

        return -99;
    }

    private int defineResults11(int[] xx, int[] yy, int[] zz) {
        if (y2x1_X2[0]==1 && x2z1_X3[0]==0) {
            putDefinedDigit(x2z1_X3[1], false);
            putDefinedDigit(x2z1_X3[2], false);
            putDefinedDigit(z2x1_Y3[1], false);
            putDefinedDigit(z2x1_Y3[2], true);
            putDefinedDigit(y1y2_Z3[1], true);
            putDefinedDigit(y1y2_Z3[2], false);
        }

        if (y2x1_X2[0]==1 && x2z1_X3[0]==1) {
            putDefinedDigit(x2z1_X3[1], true);
            putDefinedDigit(x2z1_X3[2], false);
            putDefinedDigit(z2x1_Y3[1], false);
            putDefinedDigit(z2x1_Y3[2], false);
            putDefinedDigit(y1y2_Z3[1], false);
            putDefinedDigit(y1y2_Z3[2], true);
        }

        toLogResDefinedDigits("6");
        numberDefiner.setStage(2);
        return numberDefiner.getNumber();
    }

    private int defineResults111(int[] xx, int[] yy, int[] zz) {
        if (y2x1_X2[0] == 0 && x2z1_X3[0] == 1) {
            putDefinedDigit(x2z1_X3[1], true);
            putDefinedDigit(x2z1_X3[2], false);
            putDefinedDigit(z2x1_Y3[1], true);
            putDefinedDigit(z2x1_Y3[2], false);
            putDefinedDigit(y1y2_Z3[1], true);
            putDefinedDigit(y1y2_Z3[2], false);
        }

        if (y2x1_X2[0] == 0 && x2z1_X3[0] == 2) {
            putDefinedDigit(x2z1_X3[1], true);
            putDefinedDigit(x2z1_X3[2], true);
            putDefinedDigit(z2x1_Y3[1], false);
            putDefinedDigit(z2x1_Y3[2], false);
            putDefinedDigit(y1y2_Z3[1], true);
            putDefinedDigit(y1y2_Z3[2], false);
        }

        if (y2x1_X2[0] == 2 && x2z1_X3[0] == 0) {
            putDefinedDigit(x2z1_X3[1], false);
            putDefinedDigit(x2z1_X3[2], false);
            putDefinedDigit(z2x1_Y3[1], true);
            putDefinedDigit(z2x1_Y3[2], true);
            putDefinedDigit(y1y2_Z3[1], false);
            putDefinedDigit(y1y2_Z3[2], true);
        }

        if (y2x1_X2[0] == 2 && x2z1_X3[0] == 1) {
            putDefinedDigit(x2z1_X3[1], false);
            putDefinedDigit(x2z1_X3[2], true);
            putDefinedDigit(z2x1_Y3[1], false);
            putDefinedDigit(z2x1_Y3[2], true);
            putDefinedDigit(y1y2_Z3[1], false);
            putDefinedDigit(y1y2_Z3[2], true);
        }

        if (y2x1_X2[0] == 1 && x2z1_X3[0] == 0) {
            putDefinedDigit(x2z1_X3[1], false);
            putDefinedDigit(x2z1_X3[2], false);
            putDefinedDigit(z2x1_Y3[1], true);
            putDefinedDigit(z2x1_Y3[2], true);
            putDefinedDigit(y1y2_Z3[1], true);
            putDefinedDigit(y1y2_Z3[2], false);
        }

        if (y2x1_X2[0]==1 && x2z1_X3[0]==2) {
            putDefinedDigit(x2z1_X3[1], true);
            putDefinedDigit(x2z1_X3[2], true);
            putDefinedDigit(z2x1_Y3[1], false);
            putDefinedDigit(z2x1_Y3[2], false);
            putDefinedDigit(y1y2_Z3[1], false);
            putDefinedDigit(y1y2_Z3[2], true);
        }

        if (y2x1_X2[0]==1 && x2z1_X3[0]==1) {
            return assembleByIndexes(z1z2_Z1[1],x1x2_X1[1],x1x2_X1[2],y1y2_Y1[1]);
        }

        toLogResDefinedDigits("7");
        numberDefiner.setStage(2);
        return numberDefiner.getNumber();
    }


}
