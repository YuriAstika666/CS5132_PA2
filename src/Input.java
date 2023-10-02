public class Input {
    private int relatedFloor;
    private boolean triggered;

    public Input (int relatedFloor){
        this.relatedFloor = relatedFloor;
        triggered = false;
    }

    public int getRelatedFloor() {return relatedFloor;}
    public boolean isTriggered() {return triggered;}

    public void trigger(){
        triggered = true;
    }

    public void fulfilled(){
        triggered = false;
    }
}
