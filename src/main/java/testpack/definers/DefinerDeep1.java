package testpack.definers;

import testpack.NumberFactory;

/**
 * Created by Windows on 22.12.2015.
 */
public class DefinerDeep1 extends Definer {

    private int x1x2_X1[];
    private int y1y2_Y1[];
    private int z1z2_Z1[];

    private int y2x1_X2[];
    private int x2y1_Y2[];
    private int z1z2_Z2[];

    private Definer definer;

    public DefinerDeep1(int[] x1x2_X1, int[] y1y2_Y1, int[] z1z2_Z1){
        this.x1x2_X1 = x1x2_X1;
        this.y1y2_Y1 = y1y2_Y1;
        this.z1z2_Z1 = z1z2_Z1;
    }

    @Override
    public int getNumber() throws DefinerException{
        if(stepNumber==numbersHistory.size()-1) return handleResults();
        else return getNumberFromCurrentDefiner();
    }

    private int handleResults() throws DefinerException{
        y2x1_X2[0] = getRes(stepNumber)- z1z2_Z2[0];
        x2y1_Y2[0] = x1x2_X1[0] + y1y2_Y1[0] - y2x1_X2[0];
        definer = new DefinerDeep2(x1x2_X1, y1y2_Y1, z1z2_Z1, y2x1_X2, x2y1_Y2, z1z2_Z2);
        definer.setStepNumber(numbersHistory.size());
        return definer.getNumber();
    }

    private int getNumberFromCurrentDefiner() throws DefinerException{
        if (x1x2_X1[0]==1 && y1y2_Y1[0]==1){
            return get11Number(x1x2_X1, y1y2_Y1, z1z2_Z1);
        }
        if (y1y2_Y1[0]==1 && z1z2_Z1[0]==1){
            return get11Number(y1y2_Y1, z1z2_Z1, x1x2_X1);
        }
        if (z1z2_Z1[0]==1 && x1x2_X1[0]==1){
            return get11Number(z1z2_Z1, x1x2_X1, y1y2_Y1);
        }
        if (x1x2_X1[0]==1){
            return get11Number(x1x2_X1, y1y2_Y1, z1z2_Z1);
        }
        if (y1y2_Y1[0]==1){
            return get11Number(y1y2_Y1, z1z2_Z1, x1x2_X1);
        }
        if (z1z2_Z1[0]==1){
            return get11Number(z1z2_Z1, x1x2_X1, y1y2_Y1);
        }

        putIntoDefinedDigits(x1x2_X1);
        putIntoDefinedDigits(y1y2_Y1);
        putIntoDefinedDigits(z1z2_Z1);

        NumberFactory n = NumberFactory.getInstance();
        n.setStage(2);
        return n.getARandomNumber();
    }

    private int get11Number(int[] mixingX, int[] mixingY, int[] sameZ){
        y2x1_X2 = new int[3];
        x2y1_Y2 = new int[3];
        z1z2_Z2 = sameZ;
        y2x1_X2[1] = mixingY[2];
        y2x1_X2[2] = mixingX[1];
        x2y1_Y2[1] = mixingX[2];
        x2y1_Y2[2] = mixingY[1];
        return assembleByIndexes(mixingY[2], sameZ[1], sameZ[2], mixingX[1]);
    }

    private void putIntoDefinedDigits(int[] x){
        if (x[0]==0) {
            definedDigits.put(x[1], false);
            definedDigits.put(x[2], false);
        }
        if (x[0]==2) {
            definedDigits.put(x[1], true);
            definedDigits.put(x[2], true);
        }
    }

}
