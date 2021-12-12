import java.util.ArrayList;
import java.util.Random;

public class GeneticAlgorithm {
    static ArrayList<Double> dataset = new ArrayList<>();
    static double finalFitness = 0;

    public static void main(String[] args) {
        runGA();
    }

    static class Individual{

        public ArrayList<Integer> chromosome;
        double fitness;

        public Individual(int n) {
            ArrayList<Integer> chromosome = new ArrayList<>();//we use set to get unique number within the range

            Random r = new Random();

            while(chromosome.size()<n) {
                chromosome.add(r.nextInt(2));
            }

            this.chromosome = new ArrayList<>(chromosome); //convert set into arrayList
            setFitness();
        }

        public void setFitness () {
            double res;
            int size = chromosome.size();

            double left = 0;
            double right = 0;

            for(int i=0; i<size-1; i++) {
                if(chromosome.get(i) == 0) {
                    left += dataset.get(i);
                } else {
                    right += dataset.get(i);
                }
            }

            res = Math.abs(left - right);

            this.fitness = res;
        }
    }

    static class Population{
        public ArrayList<Individual> population = new ArrayList<>();

        public Population (int popSize, double chromosomeSize) {
            for (int i=0; i<popSize; i++) {
                Individual ind = new Individual((int) chromosomeSize);
                this.population.add(ind);
            }
        }

        public void printPop() {
            for (Individual individual : population) {
                System.out.print(individual.chromosome + "\t");
                System.out.println(individual.fitness);
            }
        }

        public Individual crossOver (Individual p1, Individual p2, double rate) {
            Individual res = new Individual(p1.chromosome.size());
            int point = (int)(p1.chromosome.size()*rate);

            for (int i=0; i<point; i++) {
                res.chromosome.set(i, p1.chromosome.get(i)); //copy some genes from the parent
            }

            for (int i = 0; i < p2.chromosome.size(); i++) {
                res.chromosome.set(i, p2.chromosome.get(i));
            }

            return res;
        }

        public Individual mutate (Individual p1, Double rate) {
            ArrayList<Integer> chromosome = new ArrayList<>();//we use set to get unique number within the range
            Individual res = new Individual(p1.chromosome.size());
            int point = (int)(p1.chromosome.size()*rate);

            for (int i=0; i<point; i++) {
                chromosome.add(p1.chromosome.get(i)); //copy some genes from the parent
            }

            Random r = new Random();
            while(chromosome.size()<p1.chromosome.size()) {
                chromosome.add(r.nextInt(2));
            }
            return res;
        }
    }

    public static void runGA() {
        //create a population object and parameters
        int numGeneration = 10;
        int popSize = 10;
        double crossOverRate = 0.6;
        double mutationRate = 0.3;

        //prepare dataset
        String file = "geneticSample.csv";
        dataset = Data.readFile(file);
        double chromosomeSize = dataset.get(0);

        // initialise the population
        Population pop = new Population(popSize, chromosomeSize); // create 10 candidates, each candidates has 5 genes (5 nodes), pass dataset to calculate fitness

        // We sort the candidates by fitness in ascending order, the least the better in this example (TSP)
        pop.population.sort(new CompareFitness()); // sorting the population by fitness (asc)

        for (int gen=0; gen<numGeneration; gen++) {
            System.out.println("Generation : "+gen);

            // get the parents - top 2 from the list
            Individual p1 = pop.population.get(0);
            Individual p2 = pop.population.get(1);

            // get 2 new children
            Individual ch1 = pop.crossOver(p1, p2, crossOverRate);
            Individual ch2 = pop.crossOver(p2, p2, crossOverRate);

            // get a mutate child
            Individual ch3 = pop.mutate(p1, mutationRate);

            System.out.println();
            // add these new children to the population
            pop.population.add(ch1);
            pop.population.add(ch2);
            pop.population.add(ch3);

            //sort them
            pop.population.sort(new CompareFitness()); //sorting the population by fitness (asc)

            // remove weakest links
            pop.population.remove(popSize);
            pop.population.remove(popSize);
            pop.population.remove(popSize);
            pop.printPop();
        }

        String fileName = "geneticResult.csv";
        Data.writeResult(fileName, pop);
        finalFitness = pop.population.get(0).fitness;
    }
}
