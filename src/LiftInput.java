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
}
