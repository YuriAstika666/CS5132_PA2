public class LiftInput extends Input {
    private int liftQueueIndex;
    private InputPriority priority;

    public LiftInput(int relatedFloor){
        super(relatedFloor);
        liftQueueIndex = -1;
        priority = null;
    }

    public int getLiftQueueIndex(){return liftQueueIndex;}
    public InputPriority getInputPriority(){return priority;}

    public void trigger(int index, InputPriority priority){
        super.trigger();
        liftQueueIndex = index;
        this.priority = priority;
    }

    public void fulfilled(){
        super.fulfilled();
        liftQueueIndex = -1;
        this.priority = null;
    }
}
