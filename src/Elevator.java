import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
        LinkedList<Integer> oldFloorList;
        for(int i = 0; i < 10; i++) {
            dataset(floorList, n);
            oldFloorList = (LinkedList<Integer>) floorList.clone();
            try{
                floor = 5;
                elevator(floorList);
                floor = 5;
                FIFO(oldFloorList);
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
        System.out.println("New Algorithm");
        out = new BufferedWriter(new FileWriter("newData.csv", true));

        for(Integer floor : floorList) {
            out.write(floor.toString() + ",");
        }

        floorList.sort(Comparator.naturalOrder());
        System.out.println("Level Requests: " + floorList);
        System.out.println("Starting from: " + floor);
        double startTime = System.currentTimeMillis();
        while(!floorList.isEmpty()) {
            int currentFloor = floorList.getFirst();

            if(currentFloor > floor) {
                int difference = currentFloor - floor;

                for(int i = 0; i < difference; i++) {
                    String string = String.format("%s", floor);
                    System.out.print(string + " ");
                    floor++;
                    Thread.sleep(1000);
                }
            } else if(currentFloor < floor) {
                int difference = floor - currentFloor;

                for(int i = 0; i < difference; i++) {
                    String string = String.format("%s", floor);
                    System.out.print(string + " ");
                    floor--;
                    Thread.sleep(1000);
                }

            } else {
                String string = String.format("%s", floor);
                System.out.print(string + " ");
                System.out.println("Already on floor.");
            }

            floorList.removeFirst();
            System.out.println("You have arrived at floor " + floor);
            Thread.sleep(2000);
        }

        double endTime = System.currentTimeMillis();
        double time = (endTime - startTime) / 1000;
        System.out.println("Duration: " + time + " seconds");
        out.write(" " + time + "\n");
        out.close();
    }

    public static void FIFO(LinkedList<Integer> floors) throws InterruptedException, IOException {
        System.out.println("Old Algorithm");
        out = new BufferedWriter(new FileWriter("data.csv", true));

        for(Integer floor : floors) {
            out.write(floor.toString() + ",");
        }

        System.out.println("Level Requests: " + floors);
        System.out.println("Starting from: " + floor);

        double startTime = System.currentTimeMillis();

        while(!floors.isEmpty()) {
            int f = floors.getFirst();

            while(f > floor) {
                String string = String.format("%s", floor);
                System.out.print(string + " ");
                Thread.sleep(1000);
                floor++;
            }

            while(f < floor) {
                String string = String.format("%s", floor);
                System.out.print(string + " ");
                Thread.sleep(1000);
                floor--;
            }
            floors.removeFirst();
            System.out.println("You have arrived at level " + floor);
            Thread.sleep(2000);
        }

        double endTime = System.currentTimeMillis();
        double time = (endTime - startTime) / 1000;
        System.out.println("Duration: " + time + " seconds");
        out.write(" " + time + "\n");
        out.close();
    }
}
