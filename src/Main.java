import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException {
        ArrayList<Human> males = new ArrayList<Human>();
        ArrayList<Human> females = new ArrayList<Human>();
        ArrayList<Boolean> trues = new ArrayList<Boolean>();
        ArrayList<Boolean> falses = new ArrayList<Boolean>();
        ArrayList<Boolean> truePositives = new ArrayList<Boolean>();
        ArrayList<Boolean> falseNegatives = new ArrayList<Boolean>();
        ArrayList<Boolean> falsePositives = new ArrayList<Boolean>();
        ArrayList<Boolean> trueNegatives = new ArrayList<Boolean>();

        Random random = new Random(75); // You can feed a seed here

        FileWriter fileWriter = new FileWriter("data.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for (int i = 0; i < 2000; i++) {

            double maleHeight = random.nextGaussian() * 4 + 68;                   // This sets a standard deviation of 6 inches and a mean height of 5'8 inches for the set of data for males
            double maleWeight;

            if (maleHeight > 68)                                                 // Generally speaking taller people will weigh slightly more than shorter people as a general rule
                maleWeight = random.nextGaussian() * 15 + 170;
            else
                maleWeight = random.nextGaussian() * 15 + 160;

            Human male = new Human(maleHeight, maleWeight, Human.Gender.MALE);
            male.setExpectedGender();
            males.add(male);

            if (male.calculateGenderPredictionForAccuracy()) {
                trues.add(true);
            } else {
                falses.add(false);
            }

            if (male.getExpectedGender() == Human.Gender.MALE)
                truePositives.add(true);
            else
                falseNegatives.add(true);


            printWriter.write(male.toString());
            printWriter.flush();
        }
        for (int j = 0; j < 2000; j++) {
            double femaleHeight = random.nextGaussian() * 5 + 65;                 // Standard deviation of 6 inches and average female height of 5'5
            double femaleWeight;

            if (femaleHeight > 65)                                               // Generally speaking taller people will weigh slightly more than shorter people as a general rule
                femaleWeight = random.nextGaussian() * 10 + 140;
            else
                femaleWeight = random.nextGaussian() * 10 + 130;

            Human female = new Human(femaleHeight, femaleWeight, Human.Gender.FEMALE);
            female.setExpectedGender();
            females.add(female);

            if (female.calculateGenderPredictionForAccuracy()) {
                trues.add(true);
            } else {
                falses.add(false);
            }

            if (female.getExpectedGender() == Human.Gender.MALE)
                falsePositives.add(true);
            else
                trueNegatives.add(true);

            printWriter.write(female.toString());
            printWriter.flush();

        }
        double a = (double) truePositives.size();
        double b = (double) falseNegatives.size();
        double c = (double) falsePositives.size();
        double d = (double) trueNegatives.size();

        System.out.println(a / (a + b));
        System.out.println("Accuracy: " + (trues.size() / 4000.0));   // Accuracy and Error

        System.out.println("True Positive Rate " + (a / (a + b)));
        System.out.println("False Positive Rate " + (c / (c + d)));
        System.out.println("False Negative Rate " + (b / (a + b)));
        System.out.println("True Negative Rate " + (d / (c + d)));

        ScatterPlot scatterPlot = new ScatterPlot("Example Chart", males, females);
        scatterPlot.setSize(1600, 800);
        scatterPlot.setLocationRelativeTo(null);
        scatterPlot.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        scatterPlot.setVisible(true);
    }
}
