package game.logic.gamerNumberDefiner.stage2;

import java.util.Arrays;

/**
 * Created by Windows on 07.01.2016.
 */
public class NumberParametersS2 {
    private DefinerS2.IndexType[] indexType = new DefinerS2.IndexType[4];
    private int[] index = new int[4];

    public NumberParametersS2(){
        Arrays.fill(index, -1);
    }

    public NumberParametersS2(NumberParametersS2 numberParametersS2){
        for (int i=0; i< numberParametersS2.indexType.length; i++) {
            this.indexType[i] = numberParametersS2.indexType[i];
            this.index[i] = numberParametersS2.index[i];
        }
    }

    public void setPosition(byte position, int index, DefinerS2.IndexType type){
        this.indexType[position] = type;
        this.index[position] = index;
    }

    public DefinerS2.IndexType getType(byte position){
        return indexType[position];
    }

    public int getIndex(byte position){
        return index[position];
    }

    public byte getPosition(int indexInThisNumber){
        for (byte i=0; i<index.length; i++){
            if (index[i]==indexInThisNumber) return i;
        }
        return -1;
    }

    public int getBullsAmount(){
        int amount = 0;
        for (int i=0; i< indexType.length; i++)
            if (indexType[i] == DefinerS2.IndexType.BULL) amount++;
        return amount;
    }

    public boolean isCowExists(){
        for (int i=0; i< indexType.length; i++)
            if (indexType[i] == DefinerS2.IndexType.COW) return true;
        return false;
    }

    public int getCowIndex(){
        for (int i=0; i< indexType.length; i++)
            if (indexType[i] == DefinerS2.IndexType.COW) return index[i];
        return -1;
    }

    public byte getCowPosition(){
        for (byte i=0; i< indexType.length; i++)
            if (indexType[i] == DefinerS2.IndexType.COW) return i;
        return -1;
    }

    public boolean isNewIndexExists(){
        for (int i=0; i< indexType.length; i++)
            if (indexType[i] == DefinerS2.IndexType.NEW_INDEX) return true;
        return false;
    }

    public int getNewIndex(){
        for (int i=0; i< indexType.length; i++)
            if (indexType[i] == DefinerS2.IndexType.NEW_INDEX) return index[i];
        return -1;
    }

    public byte getNewIndexPosition(){
        for (byte i=0; i< indexType.length; i++)
            if (indexType[i] == DefinerS2.IndexType.NEW_INDEX) return i;
        return -1;
    }

    public int getCowsAndBullsAmountSum(){
        int amount = 0;
        for (int i=0; i< indexType.length; i++)
            if (indexType[i] == DefinerS2.IndexType.BULL
                    || indexType[i] == DefinerS2.IndexType.COW) amount++;
        return amount;
    }

}
