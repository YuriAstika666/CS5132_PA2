import PA2A.PriorityQueue;

public class Lift {
    private int currentFloor;
    private boolean moving;
    private int direction; // -1 = down, 0 = stay, 1 = up
    private int target;
    private WaitingPriorityQueue<Input, InputPriority> inputPriorityQueue;

    public Lift(){
        currentFloor = 1;
        moving = false;
        direction = 0;
        target = 1;
        inputPriorityQueue = new WaitingPriorityQueue<>(false);
    }

    public int getCurrentFloor(){return currentFloor;}
    public boolean isMoving(){return moving;}
    public int getDirection(){return direction;}
    public int getTarget(){return target;}

    public Input getNextInput(){
        return inputPriorityQueue.peek().getData();
    }


}
