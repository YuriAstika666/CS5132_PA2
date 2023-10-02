public class InputPriority implements Comparable<InputPriority>{
    private int priorityValue;

    public InputPriority(Lift lift, Input input, int moving, int waiting){
        this.priorityValue = calculatePriority(lift,input,moving,waiting);
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
        int liftTargetFloor = l.getTarget();
        if (inputFloor == liftCurrentFloor){
            return 0;
        }
        int value = 0;
        if (l.isMoving()){
            if (i instanceof FloorInput){
               FloorInput f = (FloorInput) i;
               if (f.getDirection() == l.getDirection()){
                   if ((inputFloor - liftCurrentFloor) * l.getDirection() > 0){
                       if ((inputFloor - liftTargetFloor) + l.getTarget() > 0){
                           value = Math.abs(liftCurrentFloor - liftTargetFloor) * moving + waiting + Math.abs(liftTargetFloor - inputFloor) * moving;
                       } else {
                           value = Math.abs(liftCurrentFloor - inputFloor) * moving;
                       }
                       value = value * 2 + 1;
                   } else {
                       value += (Math.abs(liftCurrentFloor - liftTargetFloor) * moving + waiting + Math.abs(liftTargetFloor - inputFloor) * moving) * 2;
                   }
               } else {
                   value = (Math.abs(liftCurrentFloor - liftTargetFloor) * moving + waiting + Math.abs(liftTargetFloor - inputFloor) * moving) * 2;
               }
            } else {
                if ((inputFloor - liftCurrentFloor) * l.getDirection() > 0){
                    if ((inputFloor - liftTargetFloor) + l.getTarget() > 0){
                        value = Math.abs(liftCurrentFloor - liftTargetFloor) * moving + waiting + Math.abs(liftTargetFloor - inputFloor) * moving;
                    } else {
                        value = Math.abs(liftCurrentFloor - inputFloor) * moving;
                    }
                } else {
                    value = Math.abs(liftCurrentFloor - liftTargetFloor) * moving + waiting + Math.abs(liftTargetFloor - inputFloor) * moving;
                }
                value = value * 2 + 1;
            }
        } else {
            value += Math.abs(liftCurrentFloor - inputFloor) * moving;
            if (i instanceof FloorInput){
                value = value*2 + 1;
            } else {
                value *= 2;
            }
        }


        return value;
    }
}
