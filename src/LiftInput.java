public class LiftInput extends Input {
    private final int associatedLiftIndex;
    private int liftQueueIndex;
    private InputPriority priority;

    public LiftInput(Operation operation, int relatedFloor, int associatedLiftIndex){
        super(operation, relatedFloor);
        this.associatedLiftIndex = associatedLiftIndex;
        liftQueueIndex = -1;
        priority = null;
    }

    public int getAssociatedLiftIndex(){return associatedLiftIndex;}
    public int getLiftQueueIndex(){return liftQueueIndex;}
    public InputPriority getInputPriority(){return priority;}

    public void changePriorityAndIndex(InputPriority newPriority, int newIndex){
        priority = newPriority;
        liftQueueIndex = newIndex;
    }

    public void trigger(int index, InputPriority priority){
        super.trigger();
        liftQueueIndex = index;
        this.priority = priority;
    }

    public void attempt(int liftIndex){super.attempt();}
    public void stopAttempt(){super.stopAttempt();}

    public void fulfilled(){
        super.fulfilled();
        liftQueueIndex = -1;
        this.priority = null;
    }

    @Override
    public String toString() {
        return "{" + associatedLiftIndex + "," + liftQueueIndex + "," + (priority == null ? "." : priority.getPriorityValue())  + '}';
    }
}
