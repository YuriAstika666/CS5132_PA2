public class FloorInput extends Input{
    private int direction; //-1 for down, 1 for up, 0 when no direction
    private InputPriority[] priorities;
    private int[] liftQueueIndex;

    public FloorInput(int relatedFloor, int numberOfLifts){
        super(relatedFloor);
        direction = 0;
        priorities = new InputPriority[numberOfLifts];
        liftQueueIndex = new int[numberOfLifts];
    }

    public int getDirection() {return direction;}
    public InputPriority[] getPriorities() {return priorities;}
    public int[] getLiftQueueIndex() {return liftQueueIndex;}
}
