package logic.stage2;

import logic.NumberWithCowsAmount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by nyank on 05.01.2016.
 */
public class Analizator {

    protected HashMap<Integer, Boolean> definedIndexes;
    protected HashMap<Integer, CowInfo> cowContainer;
    protected HashMap<Byte, Integer> definedPositions;
    protected ArrayList<NumberWithCowsAmount> numbersHistory;

    public Analizator(HashMap<Integer, Boolean> definedIndexes, ArrayList<NumberWithCowsAmount> numbersHistory, HashMap<Integer, CowInfo> cowContainer,
                      HashMap<Byte, Integer> definedPositions) {
        this.definedIndexes = definedIndexes;
        this.cowContainer = cowContainer;
        this.definedPositions = definedPositions;
        this.numbersHistory = numbersHistory;
    }

    public void analizeDataOfPreviousSteps()
    {
        fillBullContainer();
        analizeStep1();
        analizeStep2();
    }

    private void fillBullContainer(){
        for (Integer index : definedIndexes.keySet()){
            if(definedIndexes.get(index)==true) cowContainer.put(index, new CowInfo(index, cowContainer, definedPositions));
        }
    }

    private void analizeStep1(){
        for(int i=0; i<numbersHistory.size(); i++){
            NumberWithCowsAmount number = numbersHistory.get(i);
            ArrayList<Integer> numbersTrueIndexes = number.getAllTrueIndexesFromCurrentNumber(definedIndexes);

            for (int j=0; j<numbersTrueIndexes.size(); j++){
                int trueIndex = numbersTrueIndexes.get(j);
                byte positionOfCurrentIndex = number.findPositionOfIndex(trueIndex);

                if (number.getResultSum() == number.getBullsAmount()){
                    cowContainer.get(trueIndex).setBullPosition(positionOfCurrentIndex);
                }
                if (number.getResultSum() == number.getCowsAmount()) {
                    cowContainer.get(trueIndex).setCowPositionAndAnalize(positionOfCurrentIndex);
                }
            }
        }
    }

    public void analizeStep2() {
        for(int i=0; i<numbersHistory.size(); i++){
            NumberWithCowsAmount number = numbersHistory.get(i);

            if (number.getBullsAmount() != 0 && number.getCowsAmount() != 0){
                HashSet<Integer> bullSet = defineBullSet(number);
                for(Integer index : bullSet){
                    byte positionOfIndex = number.findPositionOfIndex(index);
                    cowContainer.get(index).setBullPosition(positionOfIndex);
                }
            }
        }
    }

    private HashSet<Integer> defineBullSet(NumberWithCowsAmount number){
        ArrayList<Integer> trueIndexesOfANumber = number.getAllTrueIndexesFromCurrentNumber(definedIndexes);
        HashSet<Integer> cowSet = new HashSet<>();
        HashSet<Integer> bullSet = new HashSet<>();

        for (int j=0; j<trueIndexesOfANumber.size(); j++) {
            int trueIndex = trueIndexesOfANumber.get(j);
            byte position = number.findPositionOfIndex(trueIndex);
            if (definedPositions.get(position) != null && definedPositions.get(position) != trueIndex) {
                cowSet.add(trueIndex);
            }
        }

        if (cowSet.size() == number.getCowsAmount()) {
            for (int i=0; i<trueIndexesOfANumber.size(); i++) {
                int index = trueIndexesOfANumber.get(i);
                if (!cowSet.contains(index))
                    bullSet.add(index);
            }
        }
        return bullSet;
    }
}
