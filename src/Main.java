import PA2A.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        testPriorityQueue();
    }

    public static void testPriorityQueue() {
        PriorityQueue<Integer, Integer> pq = new PriorityQueue<>(true);
        System.out.println(pq.peek());
        pq.enqueue(1,1);
        System.out.println(pq.peek());
        pq.enqueue(2,2);
        System.out.println(pq.peek());
        pq.enqueue(0,0);
        System.out.println(pq.peek());
        System.out.println(pq.dequeue());
    }
}