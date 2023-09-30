import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PriorityFix {
    public static void main(String[] args) {
        System.out.println("---/ example.in \\---");
        System.out.println("Result: " + Arrays.toString(videoSequence("src/inputs/example.in")));

        System.out.println("\n---/ test1.in \\---");
        System.out.println("Result: " + Arrays.toString(videoSequence("src/inputs/test1.in")));
    }

    public static int[] videoSequence(String filename){
        ArrayList<Integer> result = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(filename));
            int numOfOperation = scanner.nextInt();
            PriorityQueue<Integer, DatePriority> queue = new PriorityQueue<>(true);

            for (int i = 0; i < numOfOperation; i++){
                String operation = scanner.next();
                if(operation.equals("add")){
                    Integer index = scanner.nextInt();
                    DatePriority datePriority = new DatePriority(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
                    queue.enqueue(index,datePriority);
                } else if (operation.equals("get")){
                    result.add(queue.dequeue().getData());
                }
            }
        } catch (IOException ignored){}
        return toArray(result);
    }

    public static int[] toArray(ArrayList<Integer> arrayList){
        int[] result = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++){
            result[i] = arrayList.get(i);
        }
        return result;
    }
}
