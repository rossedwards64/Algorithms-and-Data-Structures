import java.util.LinkedList;

public class Elevator {

    // start here
    static int currentLevel = 5;

    public static void main(String[] args) {
        LinkedList<Integer> levelList = new LinkedList<>();
        levelList.add(4);
        levelList.add(1);
        levelList.add(8);

        System.out.println("Levels in queue: " + levelList);

        try {
            elevator(levelList);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    // NEEDS OPTIMISING
    public static void elevator(LinkedList<Integer> levelList) throws InterruptedException {
        System.out.println("Currently on: " + currentLevel);
        double start = System.currentTimeMillis();

        while(!levelList.isEmpty()) {
            int level = levelList.getFirst();

            while(level > currentLevel) {
                String levelString = String.format("%s", currentLevel);
                System.out.print(levelString);
                Thread.sleep(1000);
                currentLevel++;
            }

            while(level < currentLevel) {
                String levelString = String.format("%s", currentLevel);
                System.out.print(levelString);
                Thread.sleep(1000);
                currentLevel--;
            }
            System.out.println("\nYou have arrived at level: " + currentLevel);
            levelList.removeFirst();
            Thread.sleep(2000);
        }

        double end = System.currentTimeMillis();
        double duration = (end - start) / 1000;
        System.out.println("Duration: " + duration + " seconds");
    }
}
