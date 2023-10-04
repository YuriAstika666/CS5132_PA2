import PA2A.Node;
import PA2A.PriorityQueue;

public class Lift {
    private int currentFloor;
    private int direction; // -1 = down, 0 = stay, 1 = up
    private int target;
//    private WaitingPriorityQueue<Input, InputPriority> inputPriorityQueue;
    private int timeCounter;
    private int state; // 0 = nothing, 1 = moving, 2 = waiting
    private int[] time; // use state to access

    public Lift(int moving, int waiting){
        currentFloor = 1;
        direction = 0;
        target = 1;
//        inputPriorityQueue = new WaitingPriorityQueue<>(false);
        timeCounter = 0;
        state = 0;
        time = new int[]{0,moving,waiting};
    }

    public int getCurrentFloor(){return currentFloor;}
    public boolean isInMotion() {return state == 0;}
    public int getDirection(){return direction;}
    public int getTarget(){return target;}
    public int getState(){return state;}


    public void move(int time, int moving, int waiting){
        for (int t = 0; t < time; t++){
            move(moving,waiting);
        }
    }

    public void move(int moving, int waiting){
        //do something
    }


}
