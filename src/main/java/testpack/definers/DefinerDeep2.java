package testpack.definers;

import testpack.NumberFactory;

/**
 * Created by Windows on 22.12.2015.
 */
public class DefinerDeep2 extends Definer {

    private int x1x2_X1[];
    private int y1y2_Y1[];
    private int z1z2_Z1[];

    private int y2x1_X2[];
    private int x2y1_Y2[];
    private int z1z2_Z2[];

    private int x2z1_X3[];
    private int z2x1_Y3[];
    private int y1y2_Z3[];

    private Definer definer;

    public DefinerDeep2(int[] x1x2_X1, int[] y1y2_Y1, int[] z1z2_Z1, int[] y2x1_X2, int[] x2y1_Y2, int[] z1z2_Z2){
        this.x1x2_X1 = x1x2_X1;
        this.y1y2_Y1 = y1y2_Y1;
        this.z1z2_Z1 = z1z2_Z1;
        this.y2x1_X2 = y2x1_X2;
        this.x2y1_Y2 = x2y1_Y2;
        this.z1z2_Z2 = z1z2_Z2;

    }

    @Override
    public int getNumber() throws DefinerException{
        if(stepNumber==numbersHistory.size()-1) return handleResults();
        else return getNumberFromCurrentDefiner();
    }

    private int handleResults() throws DefinerException{
        x2z1_X3[0] = getRes(stepNumber)- y1y2_Z3[0];
        z2x1_Y3[0] = x1x2_X1[0] + z1z2_Z1[0] - x2z1_X3[0];
        definer = new DefinerDeep3(x1x2_X1, y1y2_Y1, z1z2_Z1, y2x1_X2, x2y1_Y2, z1z2_Z2, x2z1_X3, z2x1_Y3, y1y2_Z3);
        definer.setStepNumber(numbersHistory.size());
        return definer.getNumber();
    }

    private int getNumberFromCurrentDefiner() throws DefinerException{
        if (x1x2_X1[0]==1 && y1y2_Y1[0]==1){
//            if (x1x2_X1[0]==1 && y1y2_Y1[0]==1 && z1z2_Z1[0]==1) return get111Number(z1z2_Z1,x1x2_X1,y1y2_Y1);
            return defineResults11(x1x2_X1, y1y2_Y1, z1z2_Z1);
        }

        if (y1y2_Y1[0]==1 && z1z2_Z1[0]==1){
            return defineResults11(y1y2_Y1, z1z2_Z1, x1x2_X1);
        }

        if (z1z2_Z1[0]==1 && x1x2_X1[0]==1){
            return defineResults11(z1z2_Z1, x1x2_X1, y1y2_Y1);
        }

        if (x1x2_X1[0]==1){
            putIntoDefinedDigits(y1y2_Y1);
            putIntoDefinedDigits(z1z2_Z1);
            return defineResults1(x1x2_X1, y1y2_Y1, z1z2_Z1);
        }

        if (y1y2_Y1[0]==1) {
            putIntoDefinedDigits(x1x2_X1);
            putIntoDefinedDigits(z1z2_Z1);
            return defineResults1(y1y2_Y1, z1z2_Z1, x1x2_X1);
        }

        if (z1z2_Z1[0]==1){
            putIntoDefinedDigits(x1x2_X1);
            putIntoDefinedDigits(y1y2_Y1);
            return defineResults1(z1z2_Z1, x1x2_X1, y1y2_Y1);
        }
        return -10;
    }

    private int defineResults11(int[] xx, int[] yy, int[] zz) throws DefinerException{
 //       if (z1z2_Z2[0]==1) throw new DefinerException("������");
        if (y2x1_X2[0]==0) {
            putIntoDefinedDigits(y2x1_X2);
            putIntoDefinedDigits(x2y1_Y2);
            putIntoDefinedDigits(z1z2_Z2);
        }
        if (y2x1_X2[0]==2) {
            putIntoDefinedDigits(y2x1_X2);
            putIntoDefinedDigits(x2y1_Y2);
            putIntoDefinedDigits(z1z2_Z2);
        }
        if (y2x1_X2[0]==1) {
            return get111Number(zz, xx, yy);
        }
        NumberFactory n = NumberFactory.getInstance();
        n.setStage(2);
        return n.getARandomNumber();
    }

    private int get111Number(int[] equals1X, int[] equals1Y, int[] someZ){
        x2z1_X3 = new int[3];
        z2x1_Y3 = new int[3];
        y1y2_Z3 = someZ;
        x2z1_X3[1] = equals1Y[2];
        x2z1_X3[2] = equals1X[1];
        z2x1_Y3[1] = equals1X[2];
        z2x1_Y3[2] = equals1Y[1];
        return assembleByIndexes(equals1Y[2], someZ[1], someZ[2], equals1X[1]);
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

    private int defineResults1(int[] xx, int[] yy, int[] zz) throws DefinerException{
        if (yy[0]==2){
            if (y2x1_X2[0] - 1 - z1z2_Z2[0]==1) {
                definedDigits.put(xx[1], true);
                definedDigits.put(xx[2], false);
            }
            if (y2x1_X2[0] - 1 - z1z2_Z2[0]==0) {
                definedDigits.put(xx[1], false);
                definedDigits.put(xx[2], true);
            }
        }
        if (yy[0]==0){
            if (y2x1_X2[0] - 0 - z1z2_Z2[0]==1) {
                definedDigits.put(xx[1], true);
                definedDigits.put(xx[2], false);
            }
            if (y2x1_X2[0] - 0 - z1z2_Z2[0]==0) {
                definedDigits.put(xx[1], false);
                definedDigits.put(xx[2], true);
            }
        }
        NumberFactory n = NumberFactory.getInstance();
        n.setStage(2);
        return n.getARandomNumber();
    }


}
