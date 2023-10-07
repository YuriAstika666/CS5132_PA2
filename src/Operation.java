public class Operation {
    private final Lift[] lifts;
    private final WaitingPriorityQueue<Input,InputPriority>[] liftInputQueue;
    private final FloorInput[][] floorInputs;
    private final LiftInput[][] liftInputs;

    private final int numLifts;
    private final int numFloors;
    private final int moving;
    private final int waiting;

    public Operation(int numLifts, int numFloors, int moving, int waiting){
        this.numLifts = numLifts;
        this.numFloors = numFloors;
        this.moving = moving;
        this.waiting = waiting;

        lifts = new Lift[numLifts];
        liftInputQueue = new WaitingPriorityQueue[numLifts];
        for (int i = 0; i < numLifts; i++){
            lifts[i] = new Lift(i,moving,waiting);
            liftInputQueue[i] = new WaitingPriorityQueue<>(false);
        }

        floorInputs = new FloorInput[numFloors][2];
        for (int i = 0; i < numFloors; i++){
            floorInputs[i] = new FloorInput[]{new FloorInput(i+1,-1),new FloorInput(i+1,1)};
        }

        liftInputs = new LiftInput[numLifts][numFloors];
        for (int i = 0; i < numLifts; i++){
            for (int j = 0; j < numFloors; j++){
                liftInputs[i][j] = new LiftInput(j);
            }
        }
    }

    public void triggerFloorInput(int floor, int direction){
        FloorInput floorInput = floorInputs[floor][(direction+1)/2];
        InputPriority[] priorities = new InputPriority[numLifts];
        int[] indexes = new int[numLifts];

        for (int i = 0; i < numLifts; i++){
            priorities[i] = new InputPriority(lifts[i],floorInput,moving,waiting);
            indexes[i] = addInput(i,floorInput,priorities[i]);
        }

        floorInput.trigger(indexes,priorities);
    }

    public void triggerLiftInput(int liftIndex, int floor){
        LiftInput liftInput = liftInputs[liftIndex][floor];
        Lift lift = lifts[liftIndex];
        InputPriority priority = new InputPriority(lift,liftInput,moving,waiting);
        int index = addInput(liftIndex,liftInput,priority);
        liftInput.trigger(index,priority);
    }

    public int addInput(int liftIndex, Input input, InputPriority priority){
        return liftInputQueue[liftIndex].enqueue(input,priority).getIndexInQueue();
    }

    public Input assignNewInput(int liftIndex){
        //dequeue -> dequeue all -> return next input
        return null;
    }
}
