import java.util.*;
import java.lang.Math;

public class PartC {

    // ANSI color codes - Eğlenceli renkler
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[38;5;196m";
    public static final String GREEN = "\u001B[38;5;46m";
    public static final String YELLOW = "\u001B[38;5;226m";
    public static final String BLUE = "\u001B[38;5;33m";
    public static final String PURPLE = "\u001B[38;5;129m";
    public static final String CYAN = "\u001B[38;5;51m";
    public static final String ORANGE = "\u001B[38;5;214m";
    public static final String PINK = "\u001B[38;5;205m";
    public static final String LIME = "\u001B[38;5;118m";
    public static final String TEAL = "\u001B[38;5;43m";
    public static final String MAGENTA = "\u001B[38;5;201m";

    /** Main part of the program.
     * I used a while loop here so the menu can repeat.
     * This makes it easier for the user to try both parts without restarting the program. */
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        boolean runProgram = true;

        while (runProgram) {
            System.out.println(PURPLE + "\n===== HIGH SCHOOL MENU =====" + RESET);
            System.out.println(CYAN + "[1] Statistical Information about an Array" + RESET);
            System.out.println(LIME + "[2] Distance between Two Arrays" + RESET);
            System.out.println(RED + "[3] Return to Main Menu" + RESET);
            System.out.print(YELLOW + "Select an option: " + RESET);

            int choice = safeIntInput(input);

            if (choice == 1) {
                statisticalInfo();
            } else if (choice == 2) {
                distanceBetweenArrays();
            } else if (choice == 3) {
                runProgram = false;
            } else {
                System.out.println(RED + "Invalid choice! Please try again." + RESET);
            }
        }

        System.out.println(GREEN + "Program finished. Returning to Main Menu..." + RESET);
    }

    /** Custom method to safely read integers.
     * I added this because nextInt() throws an error if the user writes text.
     * This way, it just asks again until a valid number is entered. */
    public static int safeIntInput(Scanner input) {
        while (true) {
            try {
                return input.nextInt();
            } catch (InputMismatchException e) {
                System.out.print(RED + "Invalid input! Please enter a number: " + RESET);
                input.nextLine();
            }
        }
    }

    /** Similar method for doubles.
     * It helps when the array needs decimal values instead of integers. */
    public static double safeDoubleInput(Scanner input) {
        while (true) {
            try {
                return input.nextDouble();
            } catch (InputMismatchException e) {
                System.out.print(RED + "Invalid input! Please enter a valid number: " + RESET);
                input.nextLine();
            }
        }
    }

    /** Calculates basic statistics for an array.
     * This includes median, arithmetic mean, geometric mean, and harmonic mean.
     * I wanted this part to handle any size input smoothly without breaking. */
    public static void statisticalInfo() {

        Scanner input = new Scanner(System.in);
        int n;

        // asking user until they type a positive number
        while (true) {
            System.out.print(ORANGE + "Enter array size (positive integer): " + RESET);
            n = safeIntInput(input);
            if (n > 0) break;
            System.out.println(RED + "Size must be greater than 0!" + RESET);
        }

        double[] arr = new double[n];
        System.out.println(GREEN + "Enter " + n + " elements:" + RESET);

        for (int i = 0; i < n; i++) {
            System.out.print(CYAN + "Element " + (i + 1) + ": " + RESET);
            arr[i] = safeDoubleInput(input);
        }

        Arrays.sort(arr); // sorting makes median easier

        double median = getMedian(arr);
        double arithmeticMean = getArithmeticMean(arr);
        double geometricMean = getGeometricMean(arr);
        double harmonicMean = getHarmonicMeanRecursive(arr, 0);

        System.out.println(PINK + "\nSorted Array: " + Arrays.toString(arr) + RESET);
        System.out.printf(BLUE + "Median: " + RESET + "%.4f\n", median);
        System.out.printf(GREEN + "Arithmetic Mean: " + RESET + "%.4f\n", arithmeticMean);
        System.out.printf(YELLOW + "Geometric Mean: " + RESET + "%.4f\n", geometricMean);
        System.out.printf(PURPLE + "Harmonic Mean (recursive): " + RESET + "%.4f\n", harmonicMean);

        pause();
    }

    /** Finds the middle value (median) of a sorted array.
     * If the count is even, I just take the average of the two middle numbers. */
    public static double getMedian(double[] arr) {
        int n = arr.length;
        if (n % 2 == 0) {
            return (arr[n / 2 - 1] + arr[n / 2]) / 2.0;
        } else {
            return arr[n / 2];
        }
    }

    /** Simple average = total sum / number of elements. */
    public static double getArithmeticMean(double[] arr) {
        double sum = 0;
        for (double num : arr) {
            sum += num;
        }
        return sum / arr.length;
    }

    /** Geometric mean multiplies everything and takes the nth root.
     * This is useful when numbers grow exponentially. */
    public static double getGeometricMean(double[] arr) {
        double product = 1;
        for (double num : arr) {
            product *= num;
        }
        return Math.pow(product, 1.0 / arr.length);
    }

    /** Harmonic mean calculated recursively.
     * It sums reciprocals and divides the number of items by that total.
     * I added a check to skip zeros so the program keeps running properly. */
    public static double getHarmonicMeanRecursive(double[] arr, int index) {

        if (index == arr.length) {
            return 0;
        }

        double current = arr[index];
        if (current == 0) {
            System.out.println(ORANGE + "Note: skipped a zero value here." + RESET);
            return getHarmonicMeanRecursive(arr, index + 1);
        }

        double sum = (1 / current) + getHarmonicMeanRecursive(arr, index + 1);

        if (index == 0) {
            int count = 0;
            for (double num : arr) if (num != 0) count++;
            return count / sum;
        } else {
            return sum;
        }
    }

    /** Second section: calculates distances between two arrays.
     * It finds Manhattan, Euclidean, and Cosine Similarity.
     * This part is basically about comparing patterns between numbers. */
    public static void distanceBetweenArrays() {

        Scanner input = new Scanner(System.in);
        int dim;

        while (true) {
            System.out.print(TEAL + "Enter array dimension (positive integer): " + RESET);
            dim = safeIntInput(input);
            if (dim > 0) break;
            System.out.println(RED + "Dimension must be greater than 0!" + RESET);
        }

        int[] arr1 = new int[dim];
        int[] arr2 = new int[dim];

        System.out.println(LIME + "Enter elements for first array (0–9):" + RESET);
        readValidatedArray(arr1, input);

        System.out.println(LIME + "Enter elements for second array (0–9):" + RESET);
        readValidatedArray(arr2, input);

        double manhattan = manhattanDistance(arr1, arr2);
        double euclidean = euclideanDistance(arr1, arr2);
        double cosine = cosineSimilarity(arr1, arr2);

        System.out.printf(MAGENTA + "\nManhattan Distance: " + RESET + "%.4f\n", manhattan);
        System.out.printf(CYAN + "Euclidean Distance: " + RESET + "%.4f\n", euclidean);
        System.out.printf(ORANGE + "Cosine Similarity: " + RESET + "%.4f\n", cosine);

        pause();
    }

    /** Reads each element carefully and only accepts values between 0 and 9.
     * If something else is written, it asks again. */
    public static void readValidatedArray(int[] arr, Scanner input) {
        for (int i = 0; i < arr.length; i++) {
            while (true) {
                System.out.print(PINK + "Element " + (i + 1) + ": " + RESET);
                try {
                    int val = input.nextInt();
                    if (val >= 0 && val <= 9) {
                        arr[i] = val;
                        break;
                    } else {
                        System.out.println(RED + "Please enter a number between 0 and 9." + RESET);
                    }
                } catch (InputMismatchException e) {
                    System.out.println(RED + "That doesn't look like a number. Try again." + RESET);
                    input.nextLine();
                }
            }
        }
    }

    /** Manhattan distance = sum of |x - y|.
     * I chose this one because it's simple and easy to understand visually. */
    public static double manhattanDistance(int[] a, int[] b) {
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += Math.abs(a[i] - b[i]);
        }
        return sum;
    }

    /** Euclidean distance = square root of sum of squared differences.
     * Basically how far two points are "straight-line" apart. */
    public static double euclideanDistance(int[] a, int[] b) {
        double sumSq = 0;
        for (int i = 0; i < a.length; i++) {
            double diff = a[i] - b[i];
            sumSq += Math.pow(diff, 2);
        }
        return Math.sqrt(sumSq);
    }

    /** Cosine similarity checks the angle between two vectors.
     * The closer the result is to 1, the more similar they are.
     * I added a small check to prevent division by zero here. */
    public static double cosineSimilarity(int[] a, int[] b) {
        double dot = 0;
        double normA = 0;
        double normB = 0;

        for (int i = 0; i < a.length; i++) {
            dot += a[i] * b[i];
            normA += a[i] * a[i];
            normB += b[i] * b[i];
        }

        if (normA == 0 || normB == 0) {
            System.out.println(YELLOW + "One of the vectors is all zeros, so I just return 0." + RESET);
            return 0;
        }

        return dot / (Math.sqrt(normA) * Math.sqrt(normB));
    }

    /** Just a short pause so the user can read the results before menu appears again. */
    public static void pause() {
        Scanner input = new Scanner(System.in);
        System.out.print(BLUE + "\nPress Enter to return..." + RESET);
        input.nextLine();
    }
}