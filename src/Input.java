public class Input {
    private final int relatedFloor;
    private boolean triggered;
    private boolean attempting;

    public Input (int relatedFloor){
        this.relatedFloor = relatedFloor;
        triggered = false;
        attempting = false;
    }

    public int getRelatedFloor() {return relatedFloor;}
    public boolean isTriggered() {return triggered;}
    public boolean isAttempting() {return  attempting;}

    public void trigger(){
        triggered = true;
    }

    public void attempt(){
        attempting = true;
    }

    public void stopAttempt(){
        attempting = false;
    }

    public void fulfilled(){
        triggered = false;
        attempting = false;
    }
}
