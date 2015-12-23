package testpack.definers;

/**
 * Created by Windows on 22.12.2015.
 */
public class DefinerXYZ extends Definer2{

    private int x[] = new int[3];
    private int y[] = new int[3];
    private int z[] = new int[3];
    private int x85[];
    private int y67[];
    private int z90[];
    private Definer2 definerXYZ;

    public DefinerXYZ(int[] x, int[] y, int[] z){
        this.x=x;
        this.y=y;
        this.z=z;
    }

    @Override
    public int getNumber() throws DefinerException{
        if(stepNumber==numbersHistory.size()-1) return handleResults();
        else return getNumberFromCurrentDefiner();
    }

    private int handleResults() throws DefinerException{
        x85[0] = getRes(stepNumber)-z90[0];
        y67[0] = x[0] + y[0] - x85[0];

        definerXYZ = new DefinerXYZDeep2(x,y,z,x85,y67,z90);
        definerXYZ.setStepNumber(numbersHistory.size());
        return definerXYZ.getNumber();
    }

    private int getNumberFromCurrentDefiner(){
        if (x[0]==1 && y[0]==1){
            return get11Number(x, y, z);
        }
        if (y[0]==1 && z[0]==1){
            return get11Number(y, z, x);
        }
        if (z[0]==1 && x[0]==1){
            return get11Number(z, x, y);
        }
        if (x[0]==1){
            return get11Number(x, y, z);
        }
        if (y[0]==1){
            return get11Number(y, z, x);
        }
        if (z[0]==1){
            return get11Number(z, x, y);
        }
        return 0;
    }

    private int get11Number(int[] equals1X, int[] equals1Y, int[] someZ){
        x85 = new int[3];
        y67 = new int[3];
        z90 = someZ;
        x85[1] = equals1Y[2];
        x85[2] = equals1X[1];
        y67[1] = equals1X[2];
        y67[2] = equals1Y[1];
        return assembleByIndexes(equals1Y[2], someZ[1], someZ[2], equals1X[1]);
    }

}
