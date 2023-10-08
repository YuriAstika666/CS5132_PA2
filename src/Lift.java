import PA2A.Node;
import PA2A.PriorityQueue;

import java.util.Arrays;

public class Lift {
    private final Operation operation;
    private final int liftIndex;
    private int currentFloor;
    private int direction; // -1 = down, 0 = stay, 1 = up
    private int timeCounter;
    private int state; // 0 = nothing, 1 = moving, 2 = waiting
    private final int[] time; // use state to access
    private Input inputAttempting;

    public Lift(Operation operation, int liftIndex, int moving, int waiting){
        this.operation = operation;
        this.liftIndex = liftIndex;
        currentFloor = 0;
        direction = 0;
        timeCounter = 0;
        state = 0;
        time = new int[]{0,moving,waiting};
        inputAttempting = null;
    }

    public int getLiftIndex(){return liftIndex;}
    public int getCurrentFloor(){return currentFloor;}
    public boolean isInMotion() {return state != 0;}
    public int getDirection(){return direction;}
    public int getState(){return state;}
    public Input getInputAttempting(){return inputAttempting;}

    public void startOperation(Input inputAttempting){
        this.inputAttempting = inputAttempting;
        direction = Integer.compare(inputAttempting.getRelatedFloor(),currentFloor);
        if (direction == 0){
            state = 2;
        } else {
            state = 1;
        }
        timeCounter = time[state];
    }

    public void endOperation(){
        direction = 0;
        state = 0;
        timeCounter = 0;
        inputAttempting = null;
    }

    public void move(){
        timeCounter--;
        if (timeCounter == 0){
            switch (state) {
                case 1 -> {
                    currentFloor += direction;
                    if (!operation.liftCheckChange(liftIndex)) {
                        if (currentFloor == inputAttempting.getRelatedFloor()) {
                            state = 2;
                        }
                        timeCounter = time[state];
                    }
                }
                case 2 -> operation.assignNewInput(liftIndex);
            }
        }
    }

    @Override
    public String toString() {
        return String.format("Lift %d, Floor %d, %s, State %s, Time Left: %d, Target Floor: %d ", liftIndex, currentFloor, (direction == -1 ? "Moving Down" : (direction == 0 ? "Not Moving" : "Moving Up")), (state == 0 ? "Idle" : (state == 1 ? "Moving" : "Waiting")), timeCounter, (inputAttempting == null? 0 : inputAttempting.getRelatedFloor()));
    }
}
