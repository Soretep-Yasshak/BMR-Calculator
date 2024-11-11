package Calculator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class BMRCalculator {


    public static double computeBMI(double weight, int height) {

        return 703 * (weight) / (Math.pow(height, 2));

    }


    public static double computeBMR(int age, char bodyType, double weight, int height) {

        double getBmr          = 4.54545 * weight + 15.875 * height - 5 * age;

        if (bodyType == 'F') {

            return (getBmr - 161);

        }

        else {

            return (getBmr + 5);

        }

    }


    public static double computeHighEndHealthyWeight(int height) {

        return (24.9 / 703.0) * (height * height);

    }


    public static double computeLowEndHealthyWeight(int height) {

        return (18.5 / 703.0) * (height * height);

    }


    public static double getPhysicalActivityLevel() {

        Scanner scanner          = new Scanner(System.in);
        double activityLevel;

        do {

            try {

                System.out.println("Enter your physical activity level (1.2 for sedentary, 1.4 for lightly active, " +
                        "1.6 for moderately active, 1.8 for very active, 2.0 for extremely active):");
                activityLevel     = scanner.nextDouble();

            }

            catch (Exception exception) {

                activityLevel     = -1;
                scanner.next();

            }

        }

        while (activityLevel < 1.2 || activityLevel > 2.0);

        return activityLevel;

    }


    public static double computeTDEE(double bmr, double physicalActivityLevel) {

        return bmr * physicalActivityLevel;

    }


    public static void displayResults(Scanner scanner, PrintStream stream) {

        String you_re                = "you're";
        String your                  = "your height is";
        String i                     = "inches";
        String a                     = "and";
        String y                     = "your weight is";
        String p                     = "pounds";

        String name                  = getName(scanner);
        int age                      = getAge(scanner);
        char BodyType                = getBodyType(scanner);
        double weight                = getWeight(scanner);
        int height                   = getHeight(scanner);

        double bmr                   = computeBMR(age, BodyType, weight, height);
        double bmi                   = computeBMI(weight, height);
        double lose                  = computeHighEndHealthyWeight(height);
        String GainOrLose            = WeightGainOrLose(bmi, weight, lose);

        stream.printf("Summary for %s: %s %d, ", name, you_re, age);
        stream.printf("%s %d %s %s %s %f %s ", your, height, i, a, y, weight, p + ".");
        stream.println();
        displayWeightRange(height);
        word(stream, bmr, bmi, GainOrLose);

        double physicalActivityLevel = getPhysicalActivityLevel();
        double tdee                  = computeTDEE(bmr, physicalActivityLevel);

        stream.println("Your TDEE is: " + tdee + " calories per day.");

    }


    static void word(PrintStream stream, double bmr, double bmi, String gainOrLose) {

        String Cals           = getBurnRate(bmr);
        String BmiCategory    = getWeightCategory(bmi);

        stream.println();
        stream.println("Your BMR is " + bmr + " you have a " + Cals + " calorie intake.");
        stream.println("Your BMI also is " + bmi + "which is " + BmiCategory + ".");
        stream.println(gainOrLose);
        stream.println();
        stream.println(" \t \t BMI TABLE");
        stream.println("---------------------------------------");
        stream.printf("%-15s    |  %-10s\n", "BMI", "WEIGHT STATUS");
        stream.println("---------------------------------------");
        stream.printf("%-15s    |  %-10s\n", "below 18.5", "UNDERWEIGHT");
        stream.printf("%-15s    |  %-10s\n", "18.5 - 24.9", "HEALTHY");
        stream.printf("%-15s    |  %-10s\n", "25.0 - 29.9", "OVERWEIGHT");
        stream.printf("%-15s    |  %-10s\n", "30.0 and above", "OBESE");

    }


    public static int getAge(Scanner scanner) {

        System.out.println("Enter your Age as a number example 42.");

        String value         = scanner.next();

        while (!isValidInteger(value)) {

            System.out.println("Invalid Entry " + value);
            System.out.println("Enter your Age as a number.");

            value            = scanner.next();

        }

        return Integer.parseInt(value);

    }


    public static boolean isValidInteger(String value) {

        try {

            Integer.parseInt(value);
            return true;

        }

        catch (Exception exception) {

            System.out.println("Try a number like 14.");

            return false;

        }

    }


    public static String getBurnRate(double calories) {

        if (calories < 1200.0) {

            return "LOW";

        }

        else if (calories >= 1200.0 && calories <= 2000.0) {

            return "MODERATE";

        }

        else {

            return "HIGH";

        }

    }


    public static char getBodyType(Scanner scanner) {

        System.out.println("Enter biological sex, not gender. Example, Male or female.");
        String value         = scanner.next();

        while (!isValidBodyType(value)) {

            System.out.println("Invalid Entry " + value);
            System.out.println("Enter your biological Sex. Example, Male or Female.");
            value            = scanner.next();

        }

        return value.toUpperCase().charAt(0);

    }


    public static boolean isValidBodyType(String value) {

        try {

            char first      = value.toUpperCase().charAt(0);

            return first == 'M' || first == 'F';

        }

        catch (Exception exception) {

            return false;

        }

    }


    public static int getFeet(String value) {

        int feetEnd         = value.indexOf("'");
        String feetEntry    = value.substring(0, feetEnd);

        return Integer.parseInt(feetEntry);

    }


    public static int getHeight(Scanner scanner) {

        System.out.print("Enter height in height and inches, example 6'");
        System.out.print("2");
        System.out.println('"');

        String value        = scanner.next();

        while (!isValidHeight(value)) {

            System.out.println("Invalid Entry " + value);
            System.out.print("Enter height and inches, example 6'");
            System.out.print("2");
            System.out.println('"');

            value           = scanner.next();

        }

        int feet           = getFeet(value);
        int inches         = getInches(value);

        return (getTotalHeightInInches(feet, inches));

    }


    public static int getInches(String value) {

        int inchStart      = value.indexOf("'") + 1;
        int inchEnd        = value.indexOf("\"");
        String inchEntry   = value.substring(inchStart, inchEnd);

        return Integer.parseInt(inchEntry);

    }


    public static boolean isValidHeight(String value) {

        try {

            getFeet(value);
            getInches(value);

            return true;

        }

        catch (Exception exception) {

            return false;

        }

    }


    public static String getName(Scanner scanner) {

        System.out.println("Enter your first and last name. For example, Peteros Kahssay.");

        String firstName   = scanner.next();
        String lastName    = scanner.next();

        return firstName + " " + lastName;

    }


    public static int getTotalHeightInInches(int feet, int inches) {

        return 12 * feet + inches;

    }


    public static double getWeight(Scanner scanner) {

        System.out.println("Enter weight in pounds. Example 178.2");

        String value      = scanner.next();

        while (!isValidWeight(value)) {

            System.out.println("Invalid Entry " + value);
            System.out.println("Enter your weight as a number");

            value         = scanner.next();

        }

        return Double.parseDouble(value);

    }


    public static boolean isValidWeight(String value) {

        try {

            Double.parseDouble(value);

            return true;

        }

        catch (Exception exception) {

            System.out.println("Try a number like 189.");

            return false;

        }

    }


    public static String getWeightCategory(double BMI) {

        String weightCategory = "";

        if (BMI >= 30) {

            weightCategory   += "OBESE";

        }

        else if (BMI >= 25) {

            weightCategory   += "OVERWEIGHT";

        }

        else if (BMI >= 18.5) {

            weightCategory   += "HEALTHY";

        }

        else if (BMI <= 18.4) {

            weightCategory   += "UNDERWEIGHT";

        }

        return weightCategory;

    }


    public static String WeightGainOrLose(double BMI, double weight, double SmallNumber) {

        String weightCategory = "";

        if (BMI >= 25) {

            double HowMuchWeightToLose  = weight - SmallNumber;
            weightCategory             += "You have to lose " + HowMuchWeightToLose + " to be at a healthy weight. You got this!";

        }

        else if (BMI <= 18.4) {

            double HowMuchWeightToGain  = SmallNumber - weight;
            weightCategory             += "You are under weight with a " + BMI + " BMI!";
            weightCategory             += "You have to gain " + HowMuchWeightToGain + " to be at a healthy weight. You got this!";

        }

        else if (BMI >= 18.5) {

            weightCategory             += "You don't have to lose or gain any weight!";

        }

        return weightCategory;

    }


    public static void displayWeightRange(int height) {

        double lowEnd        = computeLowEndHealthyWeight(height);
        double highEnd       = computeHighEndHealthyWeight(height);

        System.out.println("The recommended weight range for someone with a height of " + height + " inches is "
                + lowEnd + " to " + highEnd + " pounds.");

    }


    public static void intro() {

        System.out.println();
        System.out.println();
        System.out.println("=============================================================");
        System.out.println(" B  M  R     C  A  L  C  U  L  A  T  O  R");
        System.out.println();
        System.out.println("This calculator uses English Units (ft, in, lbs).");
        System.out.println(" E  X  A  M  P  L  E      E  N  T  R  I  E  S");
        System.out.println();
        System.out.println(" Name      : Jack Knight");
        System.out.println(" Age       : 21");
        System.out.println(" Body Type : Male");
        System.out.println(" Weight    : 149.2 lbs");
        System.out.println(" Height    : 5' 6\"");
        System.out.println("=============================================================");
        System.out.println("Once you're done, you will be tasked with giving the activity level for the other data!" +
                "You will choose 5 times. \n");

    }


    public static char start(char key) {

        Scanner scanner     = new Scanner(System.in);

        while (key != 'q' && key != 'Q') {

            displayResults(scanner, System.out);

            System.out.println("Press Q to end, anything else to continue.");

            key             = scanner.next().charAt(0);

        }

        return key;

    }


    public static void toFile() throws FileNotFoundException {

        String inputLocation    = "." + File.separator + "data" + File.separator + "bmr-01.txt";
        String outputLocation   = "." + File.separator + "data" + File.separator + "bmr_results.txt";
        File file               = new File(inputLocation);
        Scanner scanner         = new Scanner(file);
        PrintStream fileStream  = new PrintStream(outputLocation);

        while (scanner.hasNextLine()) {

            displayResults(scanner, fileStream);

        }

    }


    public static class ThankYouFrame extends JFrame {

        public ThankYouFrame() {

            super("Thank you :) (Double click on me to open me up)");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel() {

                @Override
                public void paintComponent(Graphics g) {

                    g.drawString("Thank you for using this calculator!", 10, 20);

                }

            };

            add(panel);
            setSize(400, 50);
            setVisible(true);

        }

    }


    public static void showHappyFaceFrame() {

        JFrame frame         = new JFrame("Happy Face");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel         = new JPanel() {

            @Override
            public void paintComponent(Graphics g) {

                drawHappyFace(g);

            }

        };

        frame.add(panel);
        frame.setSize(200, 200);
        frame.setVisible(true);

    }


    public static void drawHappyFace(Graphics g) {

        g.setColor(Color.YELLOW);
        g.fillOval(25, 10, 150, 150);

        g.setColor(Color.BLACK);
        g.fillOval(50, 40, 20, 20);
        g.fillOval(130, 40, 20, 20);

        g.drawArc(50, 70, 100, 50, 0, -180);

    }


    public static void main(String[] args) throws FileNotFoundException {

        char key = 'Y';
        intro();
        start(key);
        toFile();
        new ThankYouFrame();
        showHappyFaceFrame();

    }

}
