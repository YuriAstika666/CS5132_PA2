package PA2A;

public class PriorityQueue<T,S extends Comparable<S>> {
    Node<T,S>[] queue;
    int count;
    int heapDirection;

    public PriorityQueue(boolean maxHeap){
        queue = new Node[4];
        count = 0;
        heapDirection = (maxHeap) ? 1 : -1;
    }

    public int getCount() {return count;}

    public void enqueue(T data, S priority){
        Node<T,S> node = new Node<>(data, priority);
        queue[count] = node;
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
    }

    public Node<T,S> peek(){
        return queue[0];
    }

    public Node<T,S> dequeue(){
        Node<T,S> temp = queue[0];
        queue[0] = queue[count-1];
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
        count--;
        return temp;
    }

    public void swap(int a, int b){
        if(a >= 0 && b >= 0 && a < count && b < count){
            if (a != b){
                Node<T,S> temp = queue[a];
                queue[a] = queue[b];
                queue[b] = temp;
            }
        } else {throw new IndexOutOfBoundsException();}
    }

    public static int getParent(int child){return Math.floorDiv(child-1,2);}
    public static int getLeftChild (int parent){return parent*2 + 1;}
    public static int getRightChild (int parent){return parent*2 + 2;}
}