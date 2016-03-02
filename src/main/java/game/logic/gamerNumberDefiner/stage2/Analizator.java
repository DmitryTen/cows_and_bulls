package game.logic.gamerNumberDefiner.stage2;

import game.logic.gamerNumberDefiner.NumberInfo;

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
    protected ArrayList<NumberInfo> numbersHistory;

    public Analizator(HashMap<Integer, Boolean> definedIndexes,
                      ArrayList<NumberInfo> numbersHistory,
                      HashMap<Integer, CowInfo> cowContainer,
                      HashMap<Byte, Integer> definedPositions) {
        this.definedIndexes = definedIndexes;
        this.cowContainer = cowContainer;
        this.definedPositions = definedPositions;
        this.numbersHistory = numbersHistory;
    }

    public void analizeDataOfStage1()
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
    //Простейший анализ (класс Analizator). Находим одиночных быков, одиночных коров, запоминаем позиции в CowInfo.
    private void analizeStep1(){
        for(int i=0; i<numbersHistory.size(); i++){
            NumberInfo number = numbersHistory.get(i);
            ArrayList<Integer> numbersTrueIndexes = number.getAllTrueIndexesFromCurrentNumber(definedIndexes);

            for (int j=0; j<numbersTrueIndexes.size(); j++){
                int trueIndex = numbersTrueIndexes.get(j);
                byte positionOfCurrentIndex = number.findPositionOfIndex(trueIndex);
                //одиночные быки
                if (number.getResultSum() == number.getBullsAmount()){
                    cowContainer.get(trueIndex).setBullPosition(positionOfCurrentIndex);
                }
                //одиночные коровы
                if (number.getResultSum() == number.getCowsAmount()) {
                    cowContainer.get(trueIndex).setCowPositionAndAnalize(positionOfCurrentIndex);
                }
            }
        }
    }
    //Вторичный анализ. Находим неодиночных быков и коров, анализируя уже занятые позиции пытаемся найти новые бычьи
    //позиции
    public void analizeStep2() {
        for(int i=0; i<numbersHistory.size(); i++){
            NumberInfo number = numbersHistory.get(i);

            if (number.getBullsAmount() != 0 && number.getCowsAmount() != 0){
                HashSet<Integer> bullSet = defineBullSet(number);
                for(Integer index : bullSet){
                    byte positionOfIndex = number.findPositionOfIndex(index);
                    cowContainer.get(index).setBullPosition(positionOfIndex);
                }
            }
        }
    }

    private HashSet<Integer> defineBullSet(NumberInfo number){
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
