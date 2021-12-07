import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Elevator {

    static int floor = 5;

    public static void main(String[] args) {
        LinkedList<Integer> floorList = new LinkedList<>();
        floorList.add(4);
        floorList.add(1);
        floorList.add(8);
        floorList.add(6);
        floorList.add(3);

        try {
            elevator(floorList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void elevator(LinkedList<Integer> floorList) throws InterruptedException {
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
                System.out.println(string);
                System.out.println("Already on floor.");
            }

            floorList.removeFirst();
            System.out.println();
            Thread.sleep(2000);
        }

        double endTime = System.currentTimeMillis();
        double time = (endTime - startTime) / 1000;
        System.out.println("\nDuration: " + time + " seconds");
    }
}
