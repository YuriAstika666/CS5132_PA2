import PA2A.Node;
import PA2A.PriorityQueue;

public class Lift {
    private final int liftIndex;
    private int currentFloor;
    private int direction; // -1 = down, 0 = stay, 1 = up
    private int timeCounter;
    private int state; // 0 = nothing, 1 = moving, 2 = waiting
    private final int[] time; // use state to access
    private Input inputAttempting;

    public Lift(int liftIndex, int moving, int waiting){
        this.liftIndex = liftIndex;
        currentFloor = 1;
        direction = 0;
        timeCounter = 0;
        state = 0;
        time = new int[]{0,moving,waiting};
        inputAttempting = null;
    }

    public int getLiftIndex(){return liftIndex;}
    public int getCurrentFloor(){return currentFloor;}
    public boolean isInMotion() {return state == 0;}
    public int getDirection(){return direction;}
    public int getState(){return state;}
    public Input getInputAttempting(){return inputAttempting;}

    public void startOperation(Input inputAttempting){
        this.inputAttempting = inputAttempting;
        direction = Integer.compare(inputAttempting.getRelatedFloor(),currentFloor);
        if (direction == 0){
            state = 2;
            timeCounter = time[state];
        } else {
            state = 1;
            timeCounter = time[state];
        }
    }

    public void endOperation(){
        direction = 0;
        timeCounter = 0;
        state = 0;
        inputAttempting = null;
    }

    public void move(int time){
        for (int t = 0; t < time; t++){
            move();
        }
    }

    public void move(){
        //do something
        timeCounter--;
        if (timeCounter == 0){
            switch (state){
                case 0:{
                    break;
                }
                case 1:{
                    break;
                }
                case 2:{
                    break;
                }
            }
        }
    }


}
