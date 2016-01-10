package logic.stage2;

import java.util.Arrays;

/**
 * Created by Windows on 07.01.2016.
 */
public class NumberParameters {
    private DefinerStage2.IndexType[] indexType = new DefinerStage2.IndexType[4];
    private int[] index = new int[4];

    public NumberParameters(){
        Arrays.fill(index, -1);
    }

    public NumberParameters(NumberParameters numberParameters){
        for (int i=0; i<numberParameters.indexType.length; i++) {
            this.indexType[i] = numberParameters.indexType[i];
            this.index[i] = numberParameters.index[i];
        }
    }

    public void setPosition(byte position, int index, DefinerStage2.IndexType type){
        this.indexType[position] = type;
        this.index[position] = index;
    }

    public DefinerStage2.IndexType getType(byte position){
        return indexType[position];
    }

    public int getIndex(byte position){
        return index[position];
    }

    public int getBullsAmount(){
        int amount = 0;
        for (int i=0; i< indexType.length; i++)
            if (indexType[i] == DefinerStage2.IndexType.BULL) amount++;
        return amount;
    }

    public boolean isCowExists(){
        for (int i=0; i< indexType.length; i++)
            if (indexType[i] == DefinerStage2.IndexType.COW) return true;
        return false;
    }

    public int getCowIndex(){
        for (int i=0; i< indexType.length; i++)
            if (indexType[i] == DefinerStage2.IndexType.COW) return index[i];
        return -1;
    }

    public byte getCowPosition(){
        for (byte i=0; i< indexType.length; i++)
            if (indexType[i] == DefinerStage2.IndexType.COW) return i;
        return -1;
    }

    public boolean isNewIndexExists(){
        for (int i=0; i< indexType.length; i++)
            if (indexType[i] == DefinerStage2.IndexType.NEW_INDEX) return true;
        return false;
    }

    public int getNewIndex(){
        for (int i=0; i< indexType.length; i++)
            if (indexType[i] == DefinerStage2.IndexType.NEW_INDEX) return index[i];
        return -1;
    }

    public byte getNewIndexPosition(){
        for (byte i=0; i< indexType.length; i++)
            if (indexType[i] == DefinerStage2.IndexType.NEW_INDEX) return i;
        return -1;
    }

    public int getCowsAndBullsAmountSum(){
        int amount = 0;
        for (int i=0; i< indexType.length; i++)
            if (indexType[i] == DefinerStage2.IndexType.BULL
                    || indexType[i] == DefinerStage2.IndexType.COW) amount++;
        return amount;
    }

}
