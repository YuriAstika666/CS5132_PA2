import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class FloorInput extends Input{
    private final int direction; //-1 for down, 1 for up, 0 when no direction
    private InputPriority[] priorities;
    private int[] liftQueueIndexes;

    public FloorInput(int relatedFloor, int direction){
        super(relatedFloor);
        this.direction = direction;
        priorities = null;
        liftQueueIndexes = null;
    }

    public int getDirection() {return direction;}
    public InputPriority[] getPriorities() {return priorities;}
    public int[] getLiftQueueIndexes() {return liftQueueIndexes;}

    public int trigger(int[] indexes, InputPriority[] priorities){
        super.trigger();
        liftQueueIndexes = indexes;
        this.priorities = priorities;

        return chooseLift();
    }

    public void fulfilled(){
        super.fulfilled();
        priorities = null;
        liftQueueIndexes = null;
    }

    public int chooseLift(){
        int[][] tempIndexes =  new int[liftQueueIndexes.length][2];
        ArrayList<InputPriority> tempPriorities = new ArrayList<>();

        for (int i = 0; i < liftQueueIndexes.length; i++){
            tempIndexes[i] = new int[]{liftQueueIndexes[i],i};
        }
        Arrays.sort(tempIndexes, Comparator.comparing(a -> a[0]));
        int minIndex = tempIndexes[0][0];
        ArrayList<Integer> tempArrayIndex = new ArrayList<>();

        int counter = 0;
        while (tempIndexes[counter][0] == minIndex) {
            tempArrayIndex.add(tempIndexes[counter][1]);
            tempPriorities.add(priorities[tempArrayIndex.get(counter)]);
            counter++;
        }

        int minPriority = tempPriorities.get(tempArrayIndex.get(0)).getPriorityValue();
        int minPriorityIndex = tempArrayIndex.get(0);
        for (int i = 1; i < tempPriorities.size(); i++){
            if(tempPriorities.get(tempArrayIndex.get(i)).getPriorityValue() < minPriority){
                minPriority = tempPriorities.get(tempArrayIndex.get(i)).getPriorityValue();
                minPriorityIndex = tempArrayIndex.get(i);
            }
        }

        return minPriorityIndex;
    }
}
