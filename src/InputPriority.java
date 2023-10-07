public class InputPriority implements Comparable<InputPriority>{
    private int priorityValue;

    public InputPriority(Lift lift, Input input, int moving, int waiting){
        this.priorityValue = calculatePriority(lift,input,moving,waiting);
    }

    public InputPriority(int priorityValue){
        this.priorityValue = priorityValue;
    }

    public int getPriorityValue() {
        return priorityValue;
    }

    @Override
    public int compareTo(InputPriority priority){
        if (priority.priorityValue % 2 == priorityValue % 2){return Integer.compare(priorityValue/2,priority.priorityValue/2);}
        else if (priority.priorityValue % 2 == 1){return -1;}
        else {return 1;}
    }

    public static int calculatePriority(Lift l, Input i, int moving, int waiting){
        int inputFloor = i.getRelatedFloor();
        int liftCurrentFloor = l.getCurrentFloor();
        int liftTargetFloor = l.getInputAttempting().getRelatedFloor();
        if (inputFloor == liftCurrentFloor){return 0;}
        int value = TD(moving,liftCurrentFloor,liftTargetFloor) + waiting + TD(moving,liftTargetFloor,inputFloor);
        boolean priorityPoint = false;
        if (l.isInMotion()){
            if(i instanceof FloorInput){
                FloorInput fi = (FloorInput) i;
                if ((inputFloor - liftCurrentFloor) * l.getDirection() > 0 && ((liftTargetFloor - inputFloor) * l.getDirection() > 0)){
                    if(fi.getDirection() == l.getDirection()){
                        value = TD(moving,liftCurrentFloor,inputFloor);
                    }
                } else if (l.getInputAttempting() instanceof  FloorInput){
                    if(((FloorInput) l.getInputAttempting()).getDirection() != l.getDirection() && fi.getDirection() != l.getDirection()){
                        if ((inputFloor - liftTargetFloor) * l.getDirection() > 0){
                            value = TD(moving,liftCurrentFloor,inputFloor);
                            priorityPoint = true;
                        }
                    }
                }
            } else {
                priorityPoint = true;
                if ((inputFloor - liftCurrentFloor) * l.getDirection() > 0 && ((liftTargetFloor - inputFloor) * l.getDirection() > 0)){
                    value = TD(moving,liftCurrentFloor,inputFloor);
                }
            }
        } else {
            value = TD(moving,liftCurrentFloor,inputFloor);
        }
        value *= 2;
        if (priorityPoint){value++;}
        return value;
    }

    public static int TD(int moving, int floor1, int floor2){
        return Math.abs(floor1 - floor2) * moving;
    }
}
