import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

public class Elevator {

    static int floor = 5;
    static BufferedWriter out;

    public static void main(String[] args) {
        LinkedList<Integer> floorList = new LinkedList<>();

        int n = 5;

        for(int i = 0; i < 10; i++) {
            getData(floorList, n);
            n += 5;
        }
    }

    public static void getData(LinkedList<Integer> floorList, int n) {
        for(int i = 0; i < 10; i++) {
            dataset(floorList, n);
            try{
                elevator(floorList);
            } catch(InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void dataset(LinkedList<Integer> floorList, int n) {
        floorList.clear();
        Random rand = new Random();

        for(int i = 0; i < n; i++) {
            floorList.add(rand.nextInt(10 - 1) + 1);
        }
    }

    static void elevator(LinkedList<Integer> floorList) throws InterruptedException, IOException {
        out = new BufferedWriter(new FileWriter("newData.csv", true));

        for(Integer floor : floorList) {
            out.write(floor.toString() + ",");
        }

        floorList.sort(Comparator.naturalOrder());
        System.out.println("Level Requests: " + floorList);
        System.out.println("Starting from: " + floor);
        double time = 0;
        while(!floorList.isEmpty()) {
            int currentFloor = floorList.getFirst();

            if(currentFloor > floor) {
                int difference = currentFloor - floor;

                for(int i = 0; i < difference; i++) {
                    String string = String.format("%s", floor);
                    System.out.print(string + " ");
                    floor++;
                    time++;
                }
            } else if(currentFloor < floor) {
                int difference = floor - currentFloor;

                for(int i = 0; i < difference; i++) {
                    String string = String.format("%s", floor);
                    System.out.print(string + " ");
                    floor--;
                    time++;
                }

            } else {
                String string = String.format("%s", floor);
                System.out.println(string);
                System.out.println("Already on floor.");
            }

            floorList.removeFirst();
            System.out.println();
            time += 2;
        }

        System.out.println("\nDuration: " + time + " seconds");
        out.write(" " + time + "\n");
        out.close();
    }
}
