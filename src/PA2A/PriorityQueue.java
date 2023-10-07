package PA2A;

public class PriorityQueue<T,S extends Comparable<S>> {
    protected Node<T,S>[] queue;
    protected int count;
    protected int heapDirection;

    public PriorityQueue(boolean maxHeap){
        queue = new Node[4];
        count = 0;
        heapDirection = (maxHeap) ? 1 : -1;
    }

    public PriorityQueue(PriorityQueue<T,S> originalQueue){
        Node<T,S>[] queueOriginal = originalQueue.queue;
        queue = new Node[queueOriginal.length];
        for (int i = 0; i < originalQueue.count; i++){
            queue[i] = queueOriginal[i].clone();
        }
        count = originalQueue.count;
        heapDirection = originalQueue.heapDirection;
    }

    public int getCount() {return count;}
    public Node<T,S>[] getQueue(){return queue;}
    public int getHeapDirection(){return heapDirection;}

    public Node<T,S> enqueue(T data, S priority){
        Node<T,S> node = new Node<>(data, priority);
        if (queue.length == count){queue = enlargeQueue(queue);}
        queue[count] = node;
        node.setIndexInQueue(count);
        int curr = count;
        count++;
        while(true){
            int parent = getParent(curr);
            if (parent >= 0 && node.comparePriority(queue[parent]) * heapDirection > 0){
//                System.out.println("Debug - parent: " + parent + "; curr: " + curr);
                swap(parent, curr);
                curr = parent;
            } else {break;}
        }
        node.setIndexInQueue(curr);
        return node;
    }

    public Node<T,S> peek(){
        return queue[0];
    }

    public Node<T,S> dequeue(){
        if (count==0) {
            System.out.println("Priority Queue is empty");
            return null;
        }
        Node<T,S> temp = queue[0];
        queue[0] = queue[count-1];
        queue[0].setIndexInQueue(0);
        queue[count-1] = null;
        count--;
        int curr = 0;
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
                    continue;
                } else {
                    swap(curr, leftChild);
                    curr = leftChild;
                    continue;
                }
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
        if (queue.length > 4 && count == queue.length/4) {
            queue = shrinkQueue(queue);
        }
        return temp;
    }

    protected void swap(int a, int b){
        if(a >= 0 && b >= 0 && a < count && b < count){
            if (a != b){
                Node<T,S> temp = queue[a];
                queue[a] = queue[b];
                queue[b] = temp;
                queue[a].setIndexInQueue(a);
                queue[b].setIndexInQueue(b);
            }
        } else {throw new IndexOutOfBoundsException();}
    }

    private Node[] enlargeQueue(Node[] q){
        Node<T,S>[] newQueue = new Node[count*2];
        if (count >= 0) System.arraycopy(q, 0, newQueue, 0, count);
        return newQueue;
    }

    protected Node[] shrinkQueue(Node[] q){
        Node<T,S>[] newQueue = new Node[count*2];
        if (count >= 0) System.arraycopy(queue, 0, newQueue, 0, count*2);
        return newQueue;
    }

    public static int getParent(int child){return Math.floorDiv(child-1,2);}
    public static int getLeftChild (int parent){return parent*2 + 1;}
    public static int getRightChild (int parent){return parent*2 + 2;}
}
