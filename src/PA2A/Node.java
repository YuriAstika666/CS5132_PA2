package PA2A;

public class Node<T,S extends Comparable<S>> {
    private T data;
    private S priority;
    private int indexInQueue;

    public Node (T data, S priority){
        this.data = data;
        this.priority = priority;
        indexInQueue = -1;
    }

    public Node (T data, S priority, int indexInQueue){
        this(data,priority);
        this.indexInQueue = indexInQueue;
    }

    public T getData(){return data;}
    public S getPriority(){return priority;}
    public int getIndexInQueue(){return indexInQueue;}

    public void setData(T data){this.data = data;}
    public void setPriority(S priority){this.priority = priority;}
    public void setIndexInQueue(int index){this.indexInQueue = index;}

    public int comparePriority(S priority2){
        return priority.compareTo(priority2);
    }

    public int comparePriority(Node<T,S> node){
        return comparePriority(node.getPriority());
    }

    public Node<T,S> clone(){
        return new Node<>(data,priority,indexInQueue);
    }

    public String toString() {
        return String.format("data : %s, priority : %s", data.toString(), priority.toString());
    }
}
