import java.util.ArrayList;
import java.util.Random;

// write every generation to csv file
// change crossover rate, write that to another csv file
// change mutation rate, write that to another csv file
// append csv files to portfolio to cut down on space
// create graphs with horizontal axis as generations and vertical axis as fitness levels
// add a function that shows that the fitness values are being calculated correctly
// in excel, work out which numbers from geneticSample are 0 (left) or 1 (right), then work out the sum of the
// 0 numbers and the sum of the 1 numbers

// generate dataset of random doubles between 0 and 5
// after that, run thousands of generations to create a graph

public class GeneticAlgorithm {
    static ArrayList<Double> dataset = new ArrayList<>();
    static double finalFitness = 0;

    public static void main(String[] args) {
        runGA();
    }

    static class Individual {

        public ArrayList<Integer> chromosome = new ArrayList<>();
        double fitness;

        public Individual(int n) {
            Random r = new Random();
            while(chromosome.size()<n) {
                this.chromosome.add(r.nextInt(2));
            }
            setFitness();
        }

        public Individual(ArrayList<Integer> genes) {
            this.chromosome.addAll(genes);
            setFitness();
        }

        public void setFitness () {
            double res;
            int size = chromosome.size();

            double left = 1;
            double right = 0;

            for(int i=0; i < size; i++) {
                if(chromosome.get(i) == 0) {
                    left *= dataset.get(i);
                } else {
                    right += dataset.get(i);
                }
            }

            res = Math.abs(left - right);
            this.fitness = res;
        }
    }

    static class Population {
        public ArrayList<Individual> population = new ArrayList<>();

        public Population (int popSize, int chromosomeSize) {
            for (int i=0; i<popSize; i++) {
                Individual ind = new Individual(chromosomeSize);
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
            ArrayList<Integer> genes = new ArrayList<>();

            int point = (int)(p1.chromosome.size()*rate);

            for (int i=0; i<point; i++) {
                genes.add(p1.chromosome.get(i));
                //res.chromosome.set(i, p1.chromosome.get(i)); //copy some genes from the parent
            }

            //res.chromosome.set(i, p2.chromosome.get(i));
            for(int i = point; i < p2.chromosome.size(); i++) {
                genes.add(p2.chromosome.get(i));
            }

            return new Individual(genes);
        }

        public Individual mutate (Individual p1, Double rate) {
            ArrayList<Integer> genes = new ArrayList<>();//we use set to get unique number within the range

            int point = (int)(p1.chromosome.size()*rate);

            for (int i=0; i<point; i++) {
                genes.add(p1.chromosome.get(i)); //copy some genes from the parent
            }

            Random r = new Random();
            while(genes.size()<p1.chromosome.size()) {
                genes.add(r.nextInt(2));
            }

            return new Individual(genes);
        }
    }

    public static void runGA() {
        //create a population object and parameters
        int numGeneration = 100000;
        int popSize = 1000;
        double crossOverRate = 0.2;
        double mutationRate = 0.7;

        //prepare dataset
        String file = "myAlgoData.csv";
        dataset = Data.readFile(file);
        int chromosomeSize = dataset.size();

        // initialise the population
        Population pop = new Population(popSize, chromosomeSize); // create 10 candidates, each candidates has 5 genes (5 nodes), pass dataset to calculate fitness

        // We sort the candidates by fitness in ascending order, the least the better in this example (TSP)
        pop.population.sort(new CompareFitness()); // sorting the population by fitness (asc)

        for (int gen=0; gen<numGeneration; gen++) {
            System.out.println("Generation : "+gen);

            // get the parents - top 2 from the list
            Individual p1 = pop.population.get(0);
            Individual p2 = pop.population.get(1);

            // try getting the bottom 2 parents as well

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

            // desktop folder path: String fileName = "C:\\Users\\redwa\\Documents\\Uni Work\\Year 2\\Algorithms and Data Structures\\Assignment\\result\\geneticResult" + gen + ".csv";
            // laptop folder path: String fileName = "C:\\Users\\redwa\\IdeaProjects\\Algorithms-and-Data-Structures\\result\\geneticResult" + gen + ".csv";
            //String fileName = "C:\\Users\\redwa\\Documents\\Uni Work\\Year 2\\Algorithms and Data Structures\\Assignment\\result\\geneticResult" + gen + ".csv";
            //Data.writeResult(fileName, pop);
            Data.writeResult2(pop);
            pop.printPop();
        }

        String fileName = "geneticResult.csv";
        Data.writeResult(fileName, pop);
        finalFitness = pop.population.get(0).fitness;
    }
}
