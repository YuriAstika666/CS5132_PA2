import PA2A.PriorityQueue;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //testPriorityQueue();
        //testWaitingPriorityQueue();
        testOperation();
        //testInputPriority();
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
        WaitingPriorityQueue<Integer, Integer> wpq = new WaitingPriorityQueue<>(false);
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

    public static void testOperation() {
        Operation operation = new Operation(1,30,2,1);
        operation.triggerFloorInput(4,1);
        /*displayStatus(operation);
        for(int i = 0; i < 10; i++) {
            operation.operate();
            displayStatus(operation);
        }*/
        System.out.println("Adding Inputs:");
        /*operation.triggerLiftInput(0,6);
        displayStatus(operation);
        operation.triggerLiftInput(0,5);
        displayStatus(operation);
        operation.triggerLiftInput(0,3);
        displayStatus(operation);*/
        operation.triggerFloorInput(29,-1);
        displayStatus(operation);
        operation.triggerFloorInput(8,-1);
        displayStatus(operation);
        operation.triggerFloorInput(13,-1);
        displayStatus(operation);
        //operation.triggerLiftInput(0,29);
        System.out.println("Post inputs");
        for(int i = 0; i < 69; i++) {
            operation.operate();
            displayStatus(operation);
        }
    }

    public static void displayStatus(Operation operation) {
        //System.out.println("--Current Status--");
        for (Lift lift : operation.getLifts()) System.out.printf("%-80s",lift);
        System.out.printf("%-116s", Arrays.toString(operation.getLiftInputs()[0]));
        //System.out.println();
        System.out.print(operation.getFloorInputs()[8][0]);
        System.out.print(operation.getFloorInputs()[13][0]);
        System.out.print(operation.getFloorInputs()[29][0]);
        System.out.println();
        //System.out.println("------------------\n");
    }

    public static void testInputPriority() {
        InputPriority[] priorities = new InputPriority[10];
        for (int i = 0; i < 10; i++) priorities[i] = new InputPriority(i);
        System.out.println("       0  1  2  3  4  5  6  7  8  9");
        System.out.println("-----------------------------------");
        //int[][] comparisons = new int[10][10];
        for (int i = 0; i < 10; i++) {
            System.out.print(i+":   ");
            for (int j = 0; j < 10; j++) {
                System.out.printf("%3d",priorities[i].compareTo(priorities[j]));
            }
            System.out.println();
        }
    }
}