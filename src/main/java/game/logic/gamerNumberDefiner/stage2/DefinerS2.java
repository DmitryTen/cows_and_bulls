package game.logic.gamerNumberDefiner.stage2;

import game.logic.gamerNumberDefiner.NumberInfo;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * Created by Windows on 26.12.2015.
 */
public abstract class DefinerS2 {

    protected static final Logger log = Logger.getLogger(DefinerS2.class);
    protected HashMap<Integer, Boolean> definedIndexes;
    protected Integer[] rndSecuence;
    protected ArrayList<NumberInfo> numbersHistory;
    protected HashMap<Integer, CowInfo> cowContainer;
    protected HashMap<Byte, Integer> definedPositions;
    /*
    Каждый индекс в числе, помимо цифрового значения имеет одно из четырех СМЫСЛОВЫХ значений:
    BULL - данный индекс бык, МЫ ЗНАЕМ, что он стоит на правильной позиции. Когда такой индекс есть в числе он добавляет
    одного быка, НЕ корову, НЕ 0!
    COW - данный индекс корова, МЫ НЕ ЗНАЕМ, стоит ли он на правильной позиции. Когда такой индекс есть в числе он
    добавляет либо быка либо корову, НЕ 0!
    NEW_INDEX - данный индекс - темная лошадка, мы его еще не использовали и ничего о нем не знаем, он может добавить:
    0, или быка или корову
    FALSE_INDEX - индекс, о котором известно, что он не значимый, он добавляет только 0, служит для дозаполнения числа

    ИТОГО:
    1. Помещая BULL, COW и FALSE_INDEX в число - заранее известно общее количество значимых чисел.
    NEW_INDEX - может изменить общее количество значимых чисел (это и служит сигналом, что NEW_INDEX - верен).
    2. Как COW так и NEW_INDEX - могут изменить общее количество БЫКОВ в числе, НО если количество значимых чисел не
    увеличилось - NEW_INDEX можно вычеркнуть.

    */
    public enum IndexType {
        BULL, COW, NEW_INDEX, FALSE_INDEX
    }
    protected boolean thirdStageStarted;


    public DefinerS2(Integer[] rndSecuence,
                     ArrayList<NumberInfo> numbersHistory,
                     HashMap<Integer, Boolean> definedIndexes,
                     HashMap<Integer, CowInfo> cowContainer,
                     HashMap<Byte, Integer> definedPositions) {
        this.rndSecuence = rndSecuence;
        this.numbersHistory = numbersHistory;
        this.definedIndexes = definedIndexes;
        this.cowContainer = cowContainer;
        this.definedPositions = definedPositions;
    }

    protected int getSumOfCowsAndBulls(int stepNumber){
        return numbersHistory.get(stepNumber).getResultSum();
    }

    protected int getCows(int stepNumber){
        return numbersHistory.get(stepNumber).getCowsAmount();
    }

    protected int getBulls(int stepNumber){
        return numbersHistory.get(stepNumber).getBullsAmount();
    }

    private int getAnyFalseIndex(){
        for (Integer index : definedIndexes.keySet() ){
            if (definedIndexes.get(index)==false) {
                return index;
            }
        }
        return -1;
    }

    protected void fillAllEmptyPositionsByFalseIndexes(NumberParametersS2 numberParametersS2){
        int falseIndex = getAnyFalseIndex();

        if (numberParametersS2.getType((byte)0) == null)
            numberParametersS2.setPosition((byte)0, falseIndex, IndexType.FALSE_INDEX);
        if (numberParametersS2.getType((byte)1) == null)
            numberParametersS2.setPosition((byte)1, falseIndex, IndexType.FALSE_INDEX);
        if (numberParametersS2.getType((byte)2) == null)
            numberParametersS2.setPosition((byte)2, falseIndex, IndexType.FALSE_INDEX);
        if (numberParametersS2.getType((byte)3) == null)
            numberParametersS2.setPosition((byte)3, falseIndex, IndexType.FALSE_INDEX);
    }

    protected void fillWithOneFalseIndexIfThereWasNoNewIndex(NumberParametersS2 numberParametersS2){
        int falseIndex = getAnyFalseIndex();
        fillByIndexWithSpecifiedType(numberParametersS2, falseIndex, IndexType.NEW_INDEX);
    }

    protected void fillWithOneNewIndex(NumberParametersS2 numberParametersS2){
        if (!thirdStageStarted) {
            int newIndex = defineNewOneIndex();
            if (newIndex == -10) return;
            fillByIndexWithSpecifiedType(numberParametersS2, newIndex, IndexType.NEW_INDEX);
        }
        else {
            HashSet<Integer> potentialCows = getDefinedBulls();
            putCowPosition(numberParametersS2, potentialCows, IndexType.NEW_INDEX);
        }

    }

    private HashSet<Integer> getDefinedBulls(){
        HashSet<Integer> definedBulls = new HashSet<>();
        for (Byte position : definedPositions.keySet()) definedBulls.add(definedPositions.get(position));
        return definedBulls;
    }


    protected void fillByIndexWithSpecifiedType (NumberParametersS2 numberParametersS2, int index, IndexType type){
        if (numberParametersS2.getType((byte)0) == null) {
            numberParametersS2.setPosition((byte)0, index, type);
            return;
        }
        if (numberParametersS2.getType((byte)1) == null) {
            numberParametersS2.setPosition((byte)1, index, type);
            return;
        }
        if (numberParametersS2.getType((byte)2) == null) {
            numberParametersS2.setPosition((byte)2, index, type);
            return;
        }
        if (numberParametersS2.getType((byte)3) == null) {
            numberParametersS2.setPosition((byte)3, index, type);
            return;
        }
    }

    protected void fillWithBulls(NumberParametersS2 numberParametersS2){
        for (Byte position : definedPositions.keySet()){
            int index = definedPositions.get(position);
            numberParametersS2.setPosition(position, index, IndexType.BULL);
        }
    }

    protected void fillWithOneCow(NumberParametersS2 numberParametersS2){
        Set<Integer> cows = getCowsExceptBulls();
        /*Оптимизация поиска!!
        Если подходящих коров нет совсем (cows.size()==0), то убираем одного быка и переставляем его с привычного места
        на другое, превращая быка в корову, это позволит определить новые "бычьи" позиции, если они есть.
        НО: если уже найдено 3 "бычьих" позиции, то данное правило - не работает (definedPositions.size()<3), т.к.
        полученная псевдокорова займет единственное свободное место, а оно нужно для NEW_INDEX, который более информативен
        чем COW.
         */
        if (cows.size()==0 && definedPositions.size()<3) removeOneBullFromNumberParametersAndPlaceItLikeACow(numberParametersS2);

        else putCowPosition(numberParametersS2, cows, IndexType.COW);
    }

    private void removeOneBullFromNumberParametersAndPlaceItLikeACow(NumberParametersS2 numberParametersS2){
        int bullIndexThatCouldHaveMoreThanOneBullPosition = -2;
        for (Byte position : definedPositions.keySet()){
            if (!cowContainer.get(definedPositions.get(position)).isThisCowFinished()) {
                bullIndexThatCouldHaveMoreThanOneBullPosition = definedPositions.get(position);
                break;
            }
        }
        byte positionToRemove = numberParametersS2.getPosition(bullIndexThatCouldHaveMoreThanOneBullPosition);

        if (positionToRemove!=-1){
            numberParametersS2.setPosition(positionToRemove, getAnyFalseIndex(), IndexType.FALSE_INDEX);
            Set<Integer> cow = new HashSet<>();
            cow.add(bullIndexThatCouldHaveMoreThanOneBullPosition);
            putCowPosition(numberParametersS2, cow, IndexType.COW);
        }
    }

    private Set<Integer> getCowsExceptBulls(){
        Set<Integer> cowSet = new HashSet<>(cowContainer.keySet());
        for (Byte position : definedPositions.keySet()){
            cowSet.remove(definedPositions.get(position));
        }
        return cowSet;
    }

    protected void fillWithAnotherCowOrBull(NumberParametersS2 numberParametersS2, int exceptIndex){
        Set<Integer> cowsAndBulls = new HashSet<>(cowContainer.keySet());
        cowsAndBulls.remove(exceptIndex);
        putCowPosition(numberParametersS2, cowsAndBulls, IndexType.NEW_INDEX);
    }

     private void putCowPosition(NumberParametersS2 numberParametersS2, Set<Integer> cows, IndexType type){
        if (numberParametersS2.getType((byte)0) == null) {
            int cowIndex = getCowIndexWithSpecifiedPosition((byte)0, cows);
            if (cowIndex != -10){
                numberParametersS2.setPosition((byte)0, cowIndex, type);
                return;
            }
        }
        if (numberParametersS2.getType((byte)1) == null) {
            int cowIndex = getCowIndexWithSpecifiedPosition((byte)1, cows);
            if (cowIndex != -10){
                numberParametersS2.setPosition((byte)1, cowIndex, type);
                return;
            }
        }
        if (numberParametersS2.getType((byte)2) == null) {
            int cowIndex = getCowIndexWithSpecifiedPosition((byte)2, cows);
            if (cowIndex != -10){
                numberParametersS2.setPosition((byte)2, cowIndex, type);
                return;
            }
        }
        if (numberParametersS2.getType((byte)3) == null) {
            int cowIndex = getCowIndexWithSpecifiedPosition((byte)3, cows);
            if (cowIndex != -10){
                numberParametersS2.setPosition((byte)3, cowIndex, type);
                return;
            }
        }
     }

    protected int getCowIndexWithSpecifiedPosition(byte cowPosition, Set<Integer> cows){
        for (Integer cowIndex : cows){
            if (!cowContainer.get(cowIndex).isCowPositionOccupied(cowPosition))
                return cowIndex;
        }
        return -10;
    }

    private int defineNewOneIndex(){
        HashSet<Integer> secuenceOfDigits = new HashSet<>();
        for (int i=0; i<10; i++) secuenceOfDigits.add(i);
        secuenceOfDigits.removeAll(definedIndexes.keySet());

        Iterator<Integer> i = secuenceOfDigits.iterator();
        if (i.hasNext()) return i.next();
        else return -10;
    }


    protected final int assembleByCharacteristics(NumberParametersS2 numberParametersS2){
        int num = rndSecuence[numberParametersS2.getIndex((byte)0)]*1000
                + rndSecuence[numberParametersS2.getIndex((byte)1)]*100
                + rndSecuence[numberParametersS2.getIndex((byte)2)]*10
                + rndSecuence[numberParametersS2.getIndex((byte)3)];

        numbersHistory.add(new NumberInfo(num,
                numberParametersS2.getIndex((byte)0),
                numberParametersS2.getIndex((byte)1),
                numberParametersS2.getIndex((byte)2),
                numberParametersS2.getIndex((byte)3),
                numbersHistory.size() + 1));

        log.debug("Received indexes: " + numberParametersS2.getIndex((byte) 0) + ", " + numberParametersS2.getIndex((byte) 1) + ", "
                + numberParametersS2.getIndex((byte) 2) + ", " + numberParametersS2.getIndex((byte) 3) + ", Created number:" + num);

        return num;
    }

    protected Set<Byte> getNotDefinedPositions(){
        Set<Byte> positions = new HashSet<>();
        for (byte b=0; b<4; b++) positions.add(b);

        positions.removeAll(definedPositions.keySet());
        return positions;
    }


}
