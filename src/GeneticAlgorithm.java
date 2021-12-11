import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

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

        public Individual copyGenes(Individual ind) {

            Individual res = new Individual(ind.chromosome.size());

            for (int i=0; i<ind.chromosome.size(); i++) {
                res.chromosome.set(i, ind.chromosome.get(i));
            }

            return res;
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

        public Individual crossOver (Individual p1, double rate) {

            Set<Integer> chromosome = new LinkedHashSet<>();//we use set to get unique number within the range
            Individual res = new Individual(p1.chromosome.size());

            int point = (int)(p1.chromosome.size()*rate);

            for (int i=0; i<point; i++) {
                chromosome.add(p1.chromosome.get(i)); //copy some genes from the parent
            }

            Random r = new Random();
            while(chromosome.size()<p1.chromosome.size()) {
                chromosome.add(r.nextInt(p1.chromosome.size())); //get the remaining genes by random
            }

            res.chromosome = new ArrayList<>(chromosome); //convert set into arrayList

            return res;
        }

        public Individual mutate (Individual p1) {
            Individual res = p1.copyGenes(p1); //we copy the parent's genes first

            //now we mutate the genes, using small change (swap genes by random)
            Random r = new Random();
            int i = r.nextInt(p1.chromosome.size());
            int j = r.nextInt(p1.chromosome.size());

            //to avoid getting the same gene
            while(i == j) {
                j = r.nextInt(p1.chromosome.size());
            }
            res.chromosome.set(i, p1.chromosome.get(j));
            res.chromosome.set(j, p1.chromosome.get(i));

            return res;
        }


    }

    public static void runGA() {

        //create a population object and parameters
        //int chromosomeSize = 10;
        int numGeneration = 10;
        int popSize = 10;
        double crossOverRate = 0.6;

        //prepare dataset
        String file = "geneticSample.csv";
        dataset = Data.readFile(file);
        double chromosomeSize = dataset.get(0);
        //Data.printArray(dataset);

        // initialise the population
        Population pop = new Population(popSize, chromosomeSize); // create 10 candidates, each candidates has 5 genes (5 nodes), pass dataset to calculate fitness

        // We sort the candidates by fitness in ascending order, the least the better in this example (TSP)
        pop.population.sort(new CompareFitness()); // sorting the population by fitness (asc)
        System.out.println("====Before Search====");
        pop.printPop();

        for (int gen=0; gen<numGeneration; gen++) {

            System.out.println("Generation : "+gen);

            // get the parents - top 2 from the list
            Individual p1 = pop.population.get(0);
            Individual p2 = pop.population.get(1);

            // get 2 new children
            Individual ch1 = pop.crossOver(p1, crossOverRate);
            Individual ch2 = pop.crossOver(p2, crossOverRate);

            // get a mutate child
            Individual ch3 = pop.mutate(p1);
            System.out.println();
            // add these new children to the population
            pop.population.add(ch1);
            pop.population.add(ch2);
            pop.population.add(ch3);

            //sort them
            pop.population.sort(new CompareFitness()); //sorting the population by fitness (asc)
        }

        System.out.println("====Result====");
        pop.printPop();

        String fileName = "geneticResult.csv";
        Data.writeResult(fileName, pop);
        finalFitness = pop.population.get(0).fitness;

    }
}
