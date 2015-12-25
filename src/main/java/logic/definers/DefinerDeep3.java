package logic.definers;

import logic.NumberFactory;
import logic.NumberWithCowsAmount;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Windows on 22.12.2015.
 */
public class DefinerDeep3 extends Definer {

    private Definer definer;

    public DefinerDeep3(Integer[] rndSecuence, ArrayList<NumberWithCowsAmount> numbersHistory, HashMap<Integer, Boolean> definedDigits, int stepNumber){
        super(rndSecuence, numbersHistory, definedDigits, stepNumber);
    }

    @Override
    public int getNumber() throws DefinerException{
        if(stepNumber<=numbersHistory.size()-1) return handleResults();
        else return getNumberFromCurrentDefiner();
    }

    private int handleResults() throws DefinerException{
        if (getRes(stepNumber)==4){
            definedDigits.put(x1x2_X1[1], true);
            definedDigits.put(x1x2_X1[2], false);
            definedDigits.put(y1y2_Y1[1], true);
            definedDigits.put(y1y2_Y1[2], false);
            definedDigits.put(z1z2_Z1[1], true);
            definedDigits.put(z1z2_Z1[2], false);
        }

        if (getRes(stepNumber)==0){
            definedDigits.put(x1x2_X1[1], false);
            definedDigits.put(x1x2_X1[2], true);
            definedDigits.put(y1y2_Y1[1], false);
            definedDigits.put(y1y2_Y1[2], true);
            definedDigits.put(z1z2_Z1[1], false);
            definedDigits.put(z1z2_Z1[2], true);
        }

        toLogResDefinedDigits("5");
        NumberFactory n = NumberFactory.getInstance();
        n.setStage(2);
        return n.getARandomNumber();

    }

    private int getNumberFromCurrentDefiner() throws DefinerException{
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

    private int defineResults11(int[] xx, int[] yy, int[] zz) throws DefinerException{
        if (y2x1_X2[0]==1 && x2z1_X3[0]==0) {
            definedDigits.put(x2z1_X3[1], false);
            definedDigits.put(x2z1_X3[2], false);
            definedDigits.put(z2x1_Y3[1], false);
            definedDigits.put(z2x1_Y3[2], true);
            definedDigits.put(y1y2_Z3[1], true);
            definedDigits.put(y1y2_Z3[2], false);
        }

        if (y2x1_X2[0]==1 && x2z1_X3[0]==1) {
            definedDigits.put(x2z1_X3[1], true);
            definedDigits.put(x2z1_X3[2], false);
            definedDigits.put(z2x1_Y3[1], false);
            definedDigits.put(z2x1_Y3[2], false);
            definedDigits.put(y1y2_Z3[1], false);
            definedDigits.put(y1y2_Z3[2], true);
        }

        toLogResDefinedDigits("6");
        NumberFactory n = NumberFactory.getInstance();
        n.setStage(2);
        return n.getARandomNumber();
    }

    private int defineResults111(int[] xx, int[] yy, int[] zz) throws DefinerException {
        if (y2x1_X2[0] == 0 && x2z1_X3[0] == 1) {
            definedDigits.put(x2z1_X3[1], true);
            definedDigits.put(x2z1_X3[2], false);
            definedDigits.put(z2x1_Y3[1], true);
            definedDigits.put(z2x1_Y3[2], false);
            definedDigits.put(y1y2_Z3[1], true);
            definedDigits.put(y1y2_Z3[2], false);
        }

        if (y2x1_X2[0] == 0 && x2z1_X3[0] == 2) {
            definedDigits.put(x2z1_X3[1], true);
            definedDigits.put(x2z1_X3[2], true);
            definedDigits.put(z2x1_Y3[1], false);
            definedDigits.put(z2x1_Y3[2], false);
            definedDigits.put(y1y2_Z3[1], true);
            definedDigits.put(y1y2_Z3[2], false);
        }

        if (y2x1_X2[0] == 2 && x2z1_X3[0] == 0) {
            definedDigits.put(x2z1_X3[1], false);
            definedDigits.put(x2z1_X3[2], false);
            definedDigits.put(z2x1_Y3[1], true);
            definedDigits.put(z2x1_Y3[2], true);
            definedDigits.put(y1y2_Z3[1], false);
            definedDigits.put(y1y2_Z3[2], true);
        }

        if (y2x1_X2[0] == 2 && x2z1_X3[0] == 1) {
            definedDigits.put(x2z1_X3[1], false);
            definedDigits.put(x2z1_X3[2], true);
            definedDigits.put(z2x1_Y3[1], false);
            definedDigits.put(z2x1_Y3[2], true);
            definedDigits.put(y1y2_Z3[1], false);
            definedDigits.put(y1y2_Z3[2], true);
        }

        if (y2x1_X2[0] == 1 && x2z1_X3[0] == 0) {
            definedDigits.put(x2z1_X3[1], false);
            definedDigits.put(x2z1_X3[2], false);
            definedDigits.put(z2x1_Y3[1], true);
            definedDigits.put(z2x1_Y3[2], true);
            definedDigits.put(y1y2_Z3[1], true);
            definedDigits.put(y1y2_Z3[2], false);
        }

        if (y2x1_X2[0]==1 && x2z1_X3[0]==2) {
            definedDigits.put(x2z1_X3[1], true);
            definedDigits.put(x2z1_X3[2], true);
            definedDigits.put(z2x1_Y3[1], false);
            definedDigits.put(z2x1_Y3[2], false);
            definedDigits.put(y1y2_Z3[1], false);
            definedDigits.put(y1y2_Z3[2], true);
        }

        if (y2x1_X2[0]==1 && x2z1_X3[0]==1) {
            return assembleByIndexes(z1z2_Z1[1],x1x2_X1[1],x1x2_X1[1],y1y2_Y1[1]);
        }

        toLogResDefinedDigits("7");
        NumberFactory n = NumberFactory.getInstance();
        n.setStage(2);
        return n.getARandomNumber();
    }


}
