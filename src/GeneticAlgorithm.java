import java.util.ArrayList;
import java.util.Random;

public class GeneticAlgorithm {
    static ArrayList<Integer> dataset = new ArrayList<>();

    public static void main(String[] args) {

        ArrayList<Integer> solution = candidate();
        printCandidate(solution);

    }

    public static ArrayList<Integer> candidate() {
        ArrayList<Integer> candidate = new ArrayList<>();

        for(int i = 0; i < dataset.size(); i++) {
            Random rand = new Random();
            int gene = rand.nextInt(2);
            candidate.add(gene);
        }
        return candidate;
    }

    public static void printCandidate(ArrayList<Integer> solution) {
        for (Integer i : solution) {
            System.out.print(i);
        }
    }

    public static int getFitness(ArrayList<Integer> candidate) {
        int result = 0;
        int left = 0;
        int right = 0;

        for (Integer integer : candidate) {
            if (integer == 0) {
                left = integer;
            } else {
                right = integer;
            }
        }

        return result;
    }
}
