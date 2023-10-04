import PA2A.PriorityQueue;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // testPriorityQueue();
        testWaitingPriorityQueue();
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
        System.out.println(pq.dequeue());
        System.out.println(pq.dequeue());
        for (int i = 0; i < 20000; i++) {
            pq.enqueue(i,i*(20000-i));
        }
        System.out.println(pq.dequeue());
        for (int i = 0; i < 19999; i++) {
            System.out.println(pq.dequeue());
        }
    }

    public static void testWaitingPriorityQueue() {
        //redo the priority queue stuff
        WaitingPriorityQueue<Integer, Integer> wpq = new WaitingPriorityQueue<>(true);
        System.out.println(wpq.peek());
        wpq.enqueue(1,1);
        System.out.println(wpq.peek());
        wpq.enqueue(2,2);
        System.out.println(wpq.peek());
        wpq.enqueue(0,0);
        System.out.println(wpq.peek());
        System.out.println(wpq.dequeue());
        System.out.println(wpq.dequeue());
        System.out.println(wpq.dequeue());
        for (int i = 0; i < 20000; i++) {
            wpq.enqueue(i,i*(20000-i));
        }
        System.out.println(wpq.dequeue());
        for (int i = 0; i < 19999; i++) {
            wpq.dequeue();
        }

        for (int i = 0; i < 30; i++) {
            wpq.enqueue(i,i*(30-i));
        }
        System.out.println(wpq.getCount());

        //testing getNodeByPriorityIndex
        System.out.println(wpq.getNodeByPriorityIndex(0));
        System.out.println(wpq.getNodeByPriorityIndex(1));
        System.out.println(wpq.getNodeByPriorityIndex(2));
        System.out.println(wpq.getNodeByPriorityIndex(3));
        System.out.println(wpq.getNodeByPriorityIndex(4));
        System.out.println(wpq.getNodeByPriorityIndex(28));
        System.out.println(wpq.getNodeByPriorityIndex(29));

        //testing getNodeByArrayIndex
        System.out.println(Arrays.toString(wpq.getQueue()));
        System.out.println(wpq.getNodeByArrayIndex(0));
        System.out.println(wpq.getNodeByArrayIndex(1));
        System.out.println(wpq.getNodeByArrayIndex(2));
        System.out.println(wpq.getNodeByArrayIndex(28));
        System.out.println(wpq.getNodeByArrayIndex(29));

        //testing remove
        System.out.println(Arrays.toString(wpq.getQueue()));
        System.out.println(wpq.remove(22));
        System.out.println(Arrays.toString(wpq.getQueue()));

        //testing priorityChange, also tests heapify
        System.out.println(Arrays.toString(wpq.getQueue()));
        wpq.priorityChange(new Integer[]{25,22,29,3,2,17,16,19,28,13,14,10,9,23,27,6,7,21,8,1,20,4,24,18,5,15,12,26,11});
        System.out.println(Arrays.toString(wpq.getQueue()));
    }
}