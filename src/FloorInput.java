import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class FloorInput extends Input{
    private final int direction; //-1 for down, 1 for up
    private int attemptLiftIndex;
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
}
