import PA2A.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Operation {
    private final Lift[] lifts;
    private final WaitingPriorityQueue<Input,InputPriority>[] liftInputQueue;
    private final FloorInput[][] floorInputs;
    private final LiftInput[][] liftInputs;

    private final int numLifts;
    private final int numFloors;
    private final int moving;
    private final int waiting;

    public Lift[] getLifts(){return lifts;}
    public WaitingPriorityQueue<Input, InputPriority>[] getLiftInputQueue(){return liftInputQueue;}
    public FloorInput[][] getFloorInputs(){return floorInputs;}
    public LiftInput[][] getLiftInputs(){return liftInputs;}
    public int getNumLifts(){return numLifts;}
    public int getNumFloors(){return numFloors;}
    public int getMoving(){return moving;}
    public int getWaiting(){return waiting;}

    public Operation(int numLifts, int numFloors, int moving, int waiting){
        this.numLifts = numLifts;
        this.numFloors = numFloors;
        this.moving = moving;
        this.waiting = waiting;

        lifts = new Lift[numLifts];
        liftInputQueue = new WaitingPriorityQueue[numLifts];
        for (int i = 0; i < numLifts; i++){
            lifts[i] = new Lift(this,i,moving,waiting);
            liftInputQueue[i] = new WaitingPriorityQueue<>(true);
        }

        floorInputs = new FloorInput[numFloors][2];
        for (int i = 0; i < numFloors; i++){
            floorInputs[i] = new FloorInput[]{new FloorInput(this,i,-1),new FloorInput(this,i,1)};
        }

        liftInputs = new LiftInput[numLifts][numFloors];
        for (int i = 0; i < numLifts; i++){
            for (int j = 0; j < numFloors; j++){
                liftInputs[i][j] = new LiftInput(this,j,i);
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
        WaitingPriorityQueue<Input,InputPriority> queue = liftInputQueue[liftIndex];
        int index = queue.enqueue(input,priority).getIndexInQueue();
        for (int i = 0; i < queue.getCount(); i++){
            if (i != index) {
                Node<Input, InputPriority> node = queue.getNodeByArrayIndex(i);
                if (node.getData() instanceof FloorInput) {
                    ((FloorInput) node.getData()).changeIndex(liftIndex,node.getIndexInQueue());
                } else {
                    ((LiftInput) node.getData()).changeIndex(node.getIndexInQueue());
                }
            }
        }
        return index;
    }

    public void assignNewInput(int liftIndex){
        //dequeue -> dequeue all -> return next input
        Lift lift = lifts[liftIndex];
        WaitingPriorityQueue<Input,InputPriority> queue = liftInputQueue[liftIndex];
        Input inputCompleted = queue.dequeue().getData();
        if (inputCompleted instanceof FloorInput){
            FloorInput floorInputCompleted = (FloorInput) inputCompleted;
            for (int i = 0; i < numLifts; i++){
                if(i != liftIndex){
                    liftInputQueue[i].remove(floorInputCompleted.getLiftQueueIndexes()[i]);
                }
            }
        }
        inputCompleted.fulfilled();
        if (queue.isEmpty()){
            lift.endOperation();
        } else {
            Input input = getUnattemptedInput(liftIndex);
            if(input == null) lift.endOperation();
            else {
                lift.startOperation(input);
                if (input instanceof FloorInput){((FloorInput) input).attempt(liftIndex);}
                else{input.attempt();}
            }
        }
    }

    public boolean liftCheckChange(int liftIndex){
        Lift lift = lifts[liftIndex];
        Input input = liftInputQueue[liftIndex].peek().getData();
        if (input != null && lift.getInputAttempting() != input){
            lift.getInputAttempting().stopAttempt();
            lift.startOperation(input);
            if (input instanceof FloorInput){((FloorInput) input).attempt(liftIndex);}
            else{input.attempt();}
            return true;
        }
        return false;
    }

    public int chooseLift(FloorInput floorInput, InputPriority[] priorities, int[] indexes){
        int[][] tempIndexes =  new int[indexes.length][2];
        ArrayList<InputPriority> tempPriorities = new ArrayList<>();

        for (int i = 0; i < indexes.length; i++){
            tempIndexes[i] = new int[]{(int) Math.ceil(Math.log10(indexes[i])/Math.ceil(Math.log10(2))),i};
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

    public void operate(){//this is what happens every (time unit)
        for (int i = 0; i < numLifts; i++){
            Lift lift = lifts[i];
            WaitingPriorityQueue<Input,InputPriority> queue = liftInputQueue[i];
            if(!queue.isEmpty() && !lift.isInMotion()){
                Input input = getUnattemptedInput(i);
                if(input == null) lift.endOperation();
                else {
                    lift.startOperation(input);
                    if (input instanceof FloorInput){((FloorInput) input).attempt(i);}
                    else{input.attempt();}
                }
            } else if (!queue.isEmpty() && lift.isInMotion()) {
                InputPriority[] newPriorities = new InputPriority[queue.getCount()];
                for (int j = 0; j < queue.getCount(); j++){
                    Input input = queue.getNodeByArrayIndex(j).getData();
                    newPriorities[j] = new InputPriority(lift,input,moving,waiting);
                }
                queue.priorityChange(newPriorities);
                for (int j = 0; j < queue.getCount(); j++){
                    Node<Input,InputPriority> node = queue.getNodeByArrayIndex(j);
                    Input input = node.getData();
                    if(input instanceof FloorInput){
                        ((FloorInput) input).changePriorityAndIndex(i,node.getPriority(),node.getIndexInQueue());
                    } else if (input instanceof LiftInput){
                        ((LiftInput) input).changePriorityAndIndex(node.getPriority(),node.getIndexInQueue());
                    }
                }
            }
        }
        for (int i = 0; i < numLifts; i++){
            if (lifts[i].isInMotion()){
                lifts[i].move();
            }
        }
    }

    public Input getUnattemptedInput(int liftIndex){
        WaitingPriorityQueue<Input,InputPriority> queue = new WaitingPriorityQueue<>(liftInputQueue[liftIndex]);
        while(!queue.isEmpty() && queue.peek().getData().isAttempting()){
            queue.dequeue();
        }
        if (queue.isEmpty()) return null;
        return queue.peek().getData();
    }
}
