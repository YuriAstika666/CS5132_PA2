public class Node<T,S extends Comparable<S>> {
    private T data;
    private S priority;

    public Node (T data, S priority){
        this.data = data;
        this.priority = priority;
    }

    public T getData(){return data;}
    public S getPriority(){return priority;}

    public void setData(T data){this.data = data;}
    public void setPriority(S priority){this.priority = priority;}

    public int comparePriority(S priority2){
        return priority.compareTo(priority2);
    }

    public int comparePriority(Node<T,S> node){
        return comparePriority(node.getPriority());
    }
}
