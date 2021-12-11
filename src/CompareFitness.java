public class CompareFitness implements java.util.Comparator {
    public int compare(Object a, Object b)
    {
        return Double.compare(((GeneticAlgorithm.Individual) a).fitness, ((GeneticAlgorithm.Individual) b).fitness);
    }
}
