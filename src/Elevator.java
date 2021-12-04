import java.util.LinkedList;
import java.util.Scanner;

public class Elevator {

    // sort requests
    // go to default floor after request
    // instead of in/decrementing currentlevel, work out the difference between floors and add/subtract that

    // have a loop that writes results to a file for testing

    // start here
    static int startingLevel = 5;
    static int currentLevel = startingLevel;
    static int levelRequest = 0;
    static LinkedList<Integer> levelList = new LinkedList<>();

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            Scanner input = new Scanner(System.in);
            getUserInput(input);

            while(levelRequest != -1) {
                try{
                    elevator();
                    getUserInput(input);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
            input.close();
            System.out.println("The elevator has shut down!");
        });
        thread.start();
    }

    public static void getUserInput(Scanner input) {
        System.out.println("Which level would you like to go to? ");
        levelRequest = input.nextInt();
        if(levelRequest != -1) {
            System.out.println("Going to level " + levelRequest);
            levelList.add(levelRequest);
        }
    }

    // NEEDS OPTIMISING
    public static void elevator() throws InterruptedException {
        System.out.println("Moving from level " + currentLevel);
        double start = System.currentTimeMillis();

        while(!levelList.isEmpty()) {
            int level = levelList.getFirst();

            while(level > currentLevel++) {
                String levelString = String.format("%s", currentLevel);
                System.out.print(levelString);
                Thread.sleep(1000);
            }

            while(level < currentLevel--) {
                String levelString = String.format("%s", currentLevel);
                System.out.print(levelString);
                Thread.sleep(1000);
            }

            System.out.println("\nYou have arrived at level " + currentLevel);
            levelList.removeFirst();
            Thread.sleep(2000);
        }

        double end = System.currentTimeMillis();
        double duration = (end - start) / 1000;
        System.out.println("Duration: " + duration + " seconds");
    }
}
