public class InputPriority implements Comparable<InputPriority>{
    private int priorityValue;

    public InputPriority(int priorityValue){
        this.priorityValue = priorityValue;
    }

    public int getPriorityValue() {
        return priorityValue;
    }

    @Override
    public int compareTo(InputPriority priority){
        return Integer.compare(priorityValue,priority.priorityValue);
    }
}
