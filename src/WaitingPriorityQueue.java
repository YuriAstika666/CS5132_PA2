import PA2A.Node;
import PA2A.PriorityQueue;

import java.util.Arrays;

public class WaitingPriorityQueue<T,S extends Comparable<S>> extends PriorityQueue<T,S> {
    Node<T,S>[] queue;
    int count;
    int heapDirection;

    public WaitingPriorityQueue(boolean maxHeap){
        super(maxHeap);
        queue = super.getQueue();
        count = super.getCount();
        heapDirection = (maxHeap) ? 1 : -1;
    }

    public Node<T,S> remove(int index){
        if (index >= count || index < 0){
            throw new IndexOutOfBoundsException();
        }
        Node<T,S> temp = queue[index];
        queue[index] = queue[count-1];
        int curr = index;
        while(true){
            int leftChild = getLeftChild(curr);
            int rightChild = getRightChild(curr);
            if (leftChild >= count){break;}
            if (rightChild >= count){
                if (queue[curr].comparePriority(queue[leftChild]) * heapDirection < 0){
                    swap(curr, leftChild);
                }
                break;
            }
            boolean leftChildLarger = queue[curr].comparePriority(queue[leftChild]) * heapDirection < 0;
            boolean rightChildLarger = queue[curr].comparePriority(queue[rightChild]) * heapDirection < 0;
            if (leftChildLarger && rightChildLarger){
                if (queue[leftChild].comparePriority(queue[rightChild]) * heapDirection < 0){
                    swap(curr, rightChild);
                    curr = rightChild;
                } else {
                    swap(curr, leftChild);
                    curr = leftChild;
                }
                continue;
            } else if (leftChildLarger){
                swap (curr,leftChild);
                curr = leftChild;
                continue;
            } else if (rightChildLarger){
                swap (curr, rightChild);
                curr = rightChild;
                continue;
            }
            break;
        }
        count--;
        if (count == queue.length/4){shirnkQueue(queue);}
        return temp;
    }

    public void priorityChange(S[] priorityList){
        if (priorityList.length != count){throw new IndexOutOfBoundsException();}
        for (int i = 0; i < count; i++){
            queue[i].setPriority(priorityList[i]);
        }
        heapify();
    }

    public void heapify(){
        for (int i = getParent(count-1); i >= 0; i--) {
            int ref = i;
            while (ref < count) {
                int compare = ref;
                if (getLeftChild(ref) < count && queue[getLeftChild(ref)].getPriority().compareTo(queue[compare].getPriority()) < 0) {
                    compare = getLeftChild(ref);
                }

                if (getRightChild(ref) < count && queue[getRightChild(ref)].getPriority().compareTo(queue[compare].getPriority()) < 0) {
                    compare = getRightChild(ref);
                }

                if (compare != ref) {
                    Node<T, S> temp = queue[ref];
                    queue[ref] = queue[compare];
                    queue[compare] = temp;

                    ref = compare;
                } else {
                    break;
                }
            }
        }
    }

}
