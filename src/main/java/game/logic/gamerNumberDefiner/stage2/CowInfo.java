package game.logic.gamerNumberDefiner.stage2;

import java.util.HashMap;

/**
 * Created by Windows on 26.12.2015.
 */
public class CowInfo {

    private HashMap<Integer, CowInfo> cowContainer;
    private HashMap<Byte, Integer> definedPositions;
    private final int indexOfThisBull;
    private boolean atLeastOneBullFound;
    private HashMap<Byte, Boolean> positions = new HashMap<>();

    public CowInfo(int indexOfThisBull, HashMap<Integer, CowInfo> cowContainer, HashMap<Byte, Integer> definedPositions) {
        this.indexOfThisBull = indexOfThisBull;
        this.cowContainer = cowContainer;
        this.definedPositions = definedPositions;
    }

    public void setBullPosition(byte bullPosition) {
        positions.put(bullPosition, true);
        definedPositions.put(bullPosition, indexOfThisBull);
        atLeastOneBullFound = true;
        setCowPostionToOtherBulls(bullPosition);
    }

    public void setCowPositionAndAnalize(byte bullPosition){
        positions.put(bullPosition, false);
        analizeAndSetPositions();
    }

    private void setCowPostionToOtherBulls(byte cowPosition){
        for (Integer anotherIndex : cowContainer.keySet()){
            if (anotherIndex!=indexOfThisBull){
                cowContainer.get(anotherIndex).setCowPositionAndAnalize(cowPosition);
            }
        }
    }

    public void finishThisCow(){
            if(positions.get((byte)0) == null) positions.put((byte)0, false);
            if(positions.get((byte)1) == null) positions.put((byte)1, false);
            if(positions.get((byte)2) == null) positions.put((byte)2, false);
            if(positions.get((byte)3) == null) positions.put((byte)3, false);
    }

    public boolean isThisCowFinished(){
        if (positions.size()==4) return true;
        else return false;
    }

    private void analizeAndSetPositions(){
        if (positions.size()==3 && !atLeastOneBullFound){
            if(positions.get((byte)0) == null) {
                setBullPosition((byte)0);
            }
            if(positions.get((byte)1) == null) {
                setBullPosition((byte)1);
            }
            if(positions.get((byte)2) == null) {
                setBullPosition((byte)2);
            }
            if(positions.get((byte)3) == null) {
                setBullPosition((byte)3);
            }
        }
    }

    public boolean isCowPositionOccupied(byte position){
        if (positions.get(position)==null) return false;
        else return true;
    }



}
