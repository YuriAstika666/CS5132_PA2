import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class FloorInput extends Input{
    private final int direction; //-1 for down, 1 for up
    private int attemptLiftIndex;
    private InputPriority[] priorities;
    private int[] liftQueueIndexes;

    public FloorInput(Operation operation, int relatedFloor, int direction){
        super(operation, relatedFloor);
        this.direction = direction;
        priorities = null;
        liftQueueIndexes = null;
    }

    public int getDirection() {return direction;}
    public InputPriority[] getPriorities() {return priorities;}
    public int[] getLiftQueueIndexes() {return liftQueueIndexes;}
    public int getAttemptLiftIndex() {return attemptLiftIndex;}

    public void changeIndex(int liftIndex, int newIndex){
        liftQueueIndexes[liftIndex] = newIndex;
    }

    public void changePriorityAndIndex(int liftIndex, InputPriority newPriority, int newIndex){
        priorities[liftIndex] = newPriority;
        liftQueueIndexes[liftIndex] = newIndex;
    }

    public void trigger(int[] indexes, InputPriority[] priorities){
        super.trigger();
        liftQueueIndexes = indexes;
        this.priorities = priorities;
    }

    public void attempt(int liftIndex){
        attemptLiftIndex = liftIndex;
        super.attempt();
    }
    public void changeLift(int liftIndex){
        attemptLiftIndex = liftIndex;
    }
    public void stopAttempt(){
        attemptLiftIndex = -1;
        super.stopAttempt();
    }

    public void fulfilled(){
        super.fulfilled();
        priorities = null;
        liftQueueIndexes = null;
    }

    @Override
    public String toString() { // FOR SPECIFIC USE ONLY
        return "FloorInput{" +
                "floor=" + getRelatedFloor() +
                //"direction=" + direction +
                //", attemptLiftIndex=" + attemptLiftIndex +
                //", priorities=" + (priorities == null ? "." : priorities[0].getPriorityValue()) +
                ", priorities=" + (priorities == null ? "." : Arrays.toString(priorities)) +
                ", liftQueueIndexes=" + Arrays.toString(liftQueueIndexes) +
                '}';
    }
}
