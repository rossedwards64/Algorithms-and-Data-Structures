import java.io.*;
import java.util.ArrayList;

public class Data {
    public static ArrayList<Double> readFile (String fileName){
        ArrayList<Double> res = new ArrayList<>();
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);

            int j;
            String line;

            while((line = br.readLine()) != null) {
                String[] column = line.split(",");

                for(j = 0; j < column.length; j++) {
                    res.add(Double.parseDouble(column[j]));
                }
            }
        } catch(Exception e) {
            System.err.println("Error reading");
        }
        return res;
    }

    public static void writeResult(String file, GeneticAlgorithm.Population result) {
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter (fw);

            for (int i=0; i<result.population.size(); i++) {

                for (int j=0; j<result.population.get(i).chromosome.size(); j++) {
                    bw.write(Integer.toString(result.population.get(i).chromosome.get(j)));
                    bw.write(" ");
                }
                bw.write(Double.toString(result.population.get(i).fitness));
                bw.newLine();
            }
            bw.close();
            fw.close();
        }
        catch(Exception e) {
            System.err.print("Error writing");
        }
    }

    public static void writeResult2(GeneticAlgorithm.Population result) {
        double fitnessValue = 0;

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("bestFitness.csv", true));
            writer.write(Double.toString(result.population.get(0).fitness));
            writer.newLine();
            writer.close();

            for(int i = 0; i < result.population.size(); i++) {
                fitnessValue += result.population.get(i).fitness;
            }

            fitnessValue = fitnessValue / 10;
            writer = new BufferedWriter(new FileWriter("meanFitness.csv", true));
            writer.write(Double.toString(fitnessValue));
            writer.newLine();
            writer.close();
        } catch(IOException e) {
            System.out.println("Error");
        }
    }
}
