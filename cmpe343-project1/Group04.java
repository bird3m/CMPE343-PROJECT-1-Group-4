import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * CMPE 343 - Project 1
 * Basic Java Programming Application
 * This class contains all project operations.
 */
public class Group04 {

    // Scanner object for user input.
    private static Scanner scanner = new Scanner(System.in);
    
    // The current date is obtained dynamically from the system.
    private static final LocalDate TODAY = LocalDate.now();

    // --- Main Method (Program Entry Point) ---

    public static void main(String[] args) {
        // 1. Display the welcome message (Team members only)
        displayWelcomeMessage();
        
        // 2. Start the main menu
        mainMenu();
        
        // Close the Scanner when the program finishes
        scanner.close();
    }

    // --- Main Menu and Navigation Methods ---

    /**
     * Displays the application's main menu and manages user selection.
     * Options: [A] Primary School, [B] Secondary School, [C] High School, [D] University, [E] Terminate.
     */
    public static void mainMenu() {
        boolean running = true; // The application should continue until the user selects option 'E'
        while (running) {
            System.out.println("\n\033[1;95m" + "---------------------------------------------------" + "\033[0m");
            System.out.println("\033[1;96m" + "      SWEET JAVA ADVENTURE MENU          " + "\033[0m");
            System.out.println("\033[1;95m" + "---------------------------------------------------" + "\033[0m");
            System.out.println("\033[1;93m" + "    [A] Primary School Fun Zone " + "\033[0m");
            System.out.println("\033[1;94m" + "    [B] Secondary School (Coming Soon!)" + "\033[0m");
            System.out.println("\033[1;92m" + "    [C] High School (Coming Soon!)" + "\033[0m");
            System.out.println("\033[1;91m" + "    [D] University (Coming Soon!)" + "\033[0m");
            System.out.println("\033[1;97m" + "    [E] Exit Sweet Application" + "\033[0m");
            System.out.println("\033[1;95m" + "---------------------------------------------------" + "\033[0m");
            
            String choice = getUserChoice("\n\033[1;36m" + "Please choose your adventure (A-E):" + "\033[0m").toUpperCase();
            
            clearScreen(); // The screen should be cleared after each selection

            switch (choice) {
                case "A":
                    primarySchoolMenu();
                    break;
                case "B":
                case "C":
                case "D":
                    System.out.println("\n\033[1;33m" + "Oopsie! This magical section is still brewing... " + "\033[0m");
                    System.out.println("\033[1;33m" + "Our coding fairies are working hard on it!" + "\033[0m");
                    break;
                case "E":
                    running = false; // Terminate the application
                    System.out.println("\n\033[1;95m" + "--------------------------------------" + "\033[0m");
                    System.out.println("\033[1;96m" + "    Thank you for visiting our sweet app! " + "\033[0m");
                    System.out.println("\033[1;93m" + "    May your code always compile! " + "\033[0m");
                    System.out.println("\033[1;95m" + "--------------------------------------" + "\033[0m");
                    break;
                default:
                    System.out.println("\n\033[1;91m" + "Sweetie, that's not a valid choice! Try again! " + "\033[0m");
            }
        }
    }
    
    // --- A - Primary School Methods ---
    
    /**
     * Displays the Primary School submenu and manages user selection.
     */
    public static void primarySchoolMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n\033[1;92m" + "------------------------------------" + "\033[0m");
            System.out.println("\033[1;96m" + "    PRIMARY SCHOOL FUN ZONE! " + "\033[0m");
            System.out.println("\033[1;92m" + "------------------------------------" + "\033[0m");
            System.out.println("\033[1;93m" + "    [1] Age & Zodiac Magic Calculator" + "\033[0m");
            System.out.println("\033[1;94m" + "    [2] Word Reversal Wonderland" + "\033[0m");
            System.out.println("\033[1;95m" + "    [3] Back to Main Menu" + "\033[0m");
            System.out.println("\033[1;92m" + "------------------------------------" + "\033[0m");
            
            String choice = getUserChoice("\n\033[1;36m" + "Choose your fun activity (1-3):" + "\033[0m");
            
            clearScreen(); // The screen should be cleared after submenu selection

            switch (choice) {
                case "1":
                    calculateAgeAndZodiac();
                    askForRepeatOrReturn(Group04::calculateAgeAndZodiac, Group04::primarySchoolMenu);
                    return; 
                case "2":
                    reverseWordsOperation();
                    askForRepeatOrReturn(Group04::reverseWordsOperation, Group04::primarySchoolMenu);
                    return;
                case "3":
                    return; // Exit the primarySchoolMenu method and return to mainMenu
                default:
                    System.out.println("\n\033[1;91m" + "Oh no! That's not a valid choice! " + "\033[0m");
            }
        }
    }
    
    /**
     * Calculates age (year, month, day) and zodiac sign from the birth date.
     * Uses built-in date functions as requested by the user, overriding original project constraint.
     */
    public static void calculateAgeAndZodiac() {
        System.out.println("\n\033[1;95m" + "-☻-☻-☻-☻-☻-☻-☻-☻-☻-☻-☻-☻-☻-☻-☻-☻-☻-☻-" + "\033[0m");
        System.out.println("\033[1;96m" + "    AGE & ZODIAC MAGIC CALCULATOR " + "\033[0m");
        System.out.println("\033[1;95m" + "-☻-☻-☻-☻-☻-☻-☻-☻-☻-☻-☻-☻-☻-☻-☻-☻-☻-☻-" + "\033[0m");
        System.out.println("\033[1;93m" + "Today's Date: " + TODAY.getDayOfMonth() + "." + TODAY.getMonthValue() + "." + TODAY.getYear() + " " + "\033[0m");
        
        // 1. Get the birth date from the user (with error handling)
        int day = 0, month = 0, year = 0;
        
        // Loop until a valid date is obtained
        boolean dateValid = false;
        LocalDate birthDate = null;
        while (!dateValid) {
            day = getValidatedInteger("\n\033[1;36m" + "Enter your magical birth day (DD): " + "\033[0m");
            month = getValidatedInteger("\033[1;36m" + "Enter your magical birth month (MM): " + "\033[0m");
            year = getValidatedInteger("\033[1;36m" + "Enter your magical birth year (YYYY): " + "\033[0m");

            if (isValidInputDate(day, month, year)) {
                try {
                    birthDate = LocalDate.of(year, month, day);
                    if (birthDate.isAfter(TODAY)) {
                         // REVISED ERROR MESSAGE 1
                         System.out.println("\n\033[1;41m\033[1;97m" + "!!! ERROR: TIME TRAVELER DETECTED! !!!" + "\033[0m");
                         System.out.println("\033[1;91m" + "The birth date can't be in the future (after " + TODAY + ")! " + "\033[0m");
                    } else {
                        dateValid = true;
                    }
                } catch (Exception e) {
                    // REVISED ERROR MESSAGE 2
                    System.out.println("\n\033[1;41m\033[1;97m" + "!!! ERROR: INVALID DATE COMBINATION! !!!" + "\033[0m");
                    System.out.println("\033[1;91m" + "Oopsie! That date combination (" + day + "." + month + "." + year + ") doesn't exist in our magical calendar! " + "\033[0m");
                }
            } else {
                // REVISED ERROR MESSAGE 3
                System.out.println("\n\033[1;41m\033[1;97m" + "!!! ERROR: OUT OF RANGE! !!!" + "\033[0m");
                System.out.println("\033[1;91m" + "That date seems a bit too magical! Please ensure the day (1-31), month (1-12), and a reasonable year are entered! " + "\033[0m");
            }
        }
        
        // 2. Age Calculation (Using built-in ChronoUnit)
        long years = ChronoUnit.YEARS.between(birthDate, TODAY);
        long months = ChronoUnit.MONTHS.between(birthDate.plusYears(years), TODAY);
        long days = ChronoUnit.DAYS.between(birthDate.plusYears(years).plusMonths(months), TODAY);

        // 3. Zodiac Sign Calculation (Custom Code)
        String zodiac = calculateZodiacSign(day, month);
        
        System.out.println("\n\033[1;95m" + "-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-" + "\033[0m");
        System.out.println("\033[1;96m" + "    YOUR MAGICAL RESULTS! " + "\033[0m");
        System.out.println("\033[1;95m" + "-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-" + "\033[0m");
        System.out.println("\033[1;93m" + "    Your Age: " + years + " years, " + months + " months, " + days + " days "); 
        System.out.println("\033[1;94m" + "    Your Zodiac Sign: " + zodiac + " ");  
        System.out.println("\033[1;95m" + "-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-" + "\033[0m");
    }
    
    /**
     * Initiates the operation to reverse the words in the text received from the user.
     */
    public static void reverseWordsOperation() {
        System.out.println("\n\033[1;95m" + "-❤-❤-❤-❤-❤-❤-❤-❤-❤-❤-❤-❤-❤-❤-❤-❤-❤-❤-" + "\033[0m");
        System.out.println("\033[1;96m" + "    WORD REVERSAL WONDERLAND! " + "\033[0m");
        System.out.println("\033[1;95m" + "-❤-❤-❤-❤-❤-❤-❤-❤-❤-❤-❤-❤-❤-❤-❤-❤-❤-❤-" + "\033[0m");
        System.out.println("\033[1;93m" + "Enter your magical text below: "); 
        
        String input = "";
        
        // [FIX] We read until a non-empty line is provided. This handles the initial empty line skip
        // after menu selection or after a Repeat (R) choice.
        while (scanner.hasNextLine()) {
            input = scanner.nextLine();
            if (!input.trim().isEmpty()) {
                break; // Found non-empty input, exit loop
            }
            // If it was just an empty line (the skipped newline character), loop again
        }

        // Call the main recursive method
        String reversedText = reverseWordsInText(input); 
        
        System.out.println("\n\033[1;92m" + "Original Text:" + "\033[0m");
        System.out.println("\033[1;97m" + input + "\033[0m");
        System.out.println("\n\033[1;94m" + "Reversed Text:" + "\033[0m");
        System.out.println("\033[1;97m" + reversedText + "\033[0m"); 
    }
    
    /**
     * Recursively reverses the words in the text.
     * Handles words containing mixed letters and numbers (e.g., "word123").
     * @param text The text to be reversed.
     * @return The text with reversed words.
     */
    public static String reverseWordsInText(String text) {
        // A regular expression is used to split the text into words and delimiters (spaces, punctuation).
        String[] tokens = text.split("(?<=\\s)|(?=\\s)|(?<=[^\\p{L}0-9])|(?=[^\\p{L}0-9])");
        
        // Call the recursive helper method
        return processTokensRecursively(tokens, 0);
    }

    /**
     * Recursively processes the text components (tokens).
     * @param tokens Array consisting of words and delimiters.
     * @param index The index of the token to be processed.
     * @return The processed text.
     */
    private static String processTokensRecursively(String[] tokens, int index) {
        if (index >= tokens.length) {
            return ""; // Base case: End of the array is reached.
        }

        String token = tokens[index];
        String processedToken;

        // [FIX for Requirement 3] Check if the token is NOT just whitespace or punctuation.
        // It should contain at least one letter or number and be length 2 or more.
        if (token.matches(".*[\\p{L}0-9]+.*") && token.length() >= 2) { 
            processedToken = reverseWordPreservingNonLetters(token); // Reverse the word while preserving digit positions
        } else {
            // Leave punctuation, or single character/whitespace tokens as they are
            processedToken = token;
        }

        // Recursive call: Process the rest and concatenate the result.
        return processedToken + processTokensRecursively(tokens, index + 1);
    }
    
    /**
     * Reverses a word while preserving the positions of non-letter characters (digits, punctuation).
     * @param word The word to reverse
     * @return The reversed word with non-letter characters in their original positions
     */
    private static String reverseWordPreservingNonLetters(String word) {
        // First, identify all non-letter characters and their positions
        StringBuilder letters = new StringBuilder();
        StringBuilder result = new StringBuilder();
        int[] nonLetterPositions = new int[word.length()];
        char[] nonLetterChars = new char[word.length()];
        
        // Separate letters from non-letters
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (Character.isLetter(c)) {
                letters.append(c);
                nonLetterPositions[i] = -1; // Mark as letter position
            } else {
                nonLetterPositions[i] = 1; // Mark as non-letter position
                nonLetterChars[i] = c;
            }
        }
        
        // Reverse the letters only
        String reversedLetters = reverseStringRecursively(letters.toString());
        
        // Reconstruct the word with non-letters in their original positions
        int letterIndex = 0;
        for (int i = 0; i < word.length(); i++) {
            if (nonLetterPositions[i] == -1) {
                // This was a letter position, take from reversed letters
                result.append(reversedLetters.charAt(letterIndex++));
            } else {
                // This was a non-letter position, keep the original character
                result.append(nonLetterChars[i]);
            }
        }
        
        return result.toString();
    }
    
    /**
     * Recursively reverses the given string. (Meets Requirement 1)
     * @param s The string to be reversed.
     * @return The reversed string.
     */
    private static String reverseStringRecursively(String s) {
        if (s == null || s.length() <= 1) {
            return s; // Base case
        }
        // Recursive step: Put the first character at the very end, reverse the rest.
        return reverseStringRecursively(s.substring(1)) + s.charAt(0);
    }

    // --- Utility Methods ---

    /**
     * Prompts the user to enter a valid integer. 
     */
    private static int getValidatedInteger(String prompt) {
        int number = 0;
        boolean validInput = false;
        while (!validInput) {
            System.out.print(prompt);
            try {
                // User attempts to enter a valid integer.
                if (scanner.hasNextInt()) {
                    number = scanner.nextInt();
                    validInput = true;
                } else {
                    // Invalid data type error 
                    System.out.println("\n\033[1;41m\033[1;97m" + "!!! ERROR: NON-NUMERIC INPUT! !!!" + "\033[0m");
                    System.out.println("\033[1;91m" + "That's not a valid number! Please enter digits only! " + "\033[0m"); 
                    scanner.next(); // Consume the invalid input
                }
            } catch (Exception e) {
                // General catch for unexpected errors
                System.out.println("\n\033[1;41m\033[1;97m" + "!!! ERROR: UNEXPECTED ISSUE! !!!" + "\033[0m");
                System.out.println("\033[1;91m" + "Oops! Something went wrong: " + e.getMessage() + " " + "\033[0m");
                scanner.nextLine(); // Clear the input buffer
            }
        }
        // [SAFE CLEANUP] Clears the leftover newline character after scanner.nextInt().
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        return number;
    }

    /**
     * Attempts to clear the screen.
     */
    private static void clearScreen() {
        try {
            // Clearing with ANSI codes (Works in most modern consoles and terminals)
            System.out.print("\033[H\033[2J");
            System.out.flush();

            // Alternative for Windows (Works in cmd, may not work in IDEs)
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            // If clearing is not possible, just print a few empty lines
            for (int i = 0; i < 50; ++i) System.out.println();
        }
    }

    /**
     * Asks the user to repeat an operation or return to the submenu after a process.
     * @param repeatMethod The method to be called to repeat the operation.
     * @param menuMethod The method to be called to return to the submenu.
     */
    private static void askForRepeatOrReturn(Runnable repeatMethod, Runnable menuMethod) {
        System.out.println("\n\033[1;95m" + "-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-" + "\033[0m");
        System.out.println("\033[1;96m" + "    What would you like to do next? " + "\033[0m");
        System.out.println("\033[1;95m" + "-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-✼-" + "\033[0m");
        System.out.println("\033[1;93m" + "    [R] Play Again (Repeat Operation)" + "\033[0m");
        System.out.println("\033[1;94m" + "    [M] Back to Fun Menu" + "\033[0m");
        
        // We use a validated choice getter to ensure 'R' or 'M' is correctly read, 
        // preventing the program from treating a newline as an invalid option.
        String choice = getValidatedRMChoice("\n\033[1;36m" + "Enter your choice (R/M):" + "\033[0m").toUpperCase();
        
        if (choice.equals("R")) {
            clearScreen();
            repeatMethod.run(); 
            // After the repeated operation is finished, we call itself to ask for R/M again (recursive)
            askForRepeatOrReturn(repeatMethod, menuMethod);
        } else if (choice.equals("M")) {
            clearScreen();
            menuMethod.run();
        } else {
            // This path should ideally not be reached
            System.out.println("\n\033[1;91m" + "Oopsie! Let's go back to the menu! " + "\033[0m");
            clearScreen();
            menuMethod.run();
        }
    }
    
    /**
     * Gets a menu choice (A-E or 1-3) from the user.
     */
    private static String getUserChoice(String prompt) {
        System.out.print(prompt + " ");
        // Reads the entire line, which is necessary for menu choices.
        if (scanner.hasNextLine()) {
            return scanner.nextLine().trim();
        }
        return "";
    }
    
    /**
     * Gets a validated R or M choice from the user, handling empty inputs robustly.
     * This is crucial for preventing the newline skip bug when returning from an operation.
     */
    private static String getValidatedRMChoice(String prompt) {
        String choice = "";
        
        // Keep reading until a valid 'R' or 'M' is provided.
        while (true) {
             System.out.print(prompt + " ");
             if (scanner.hasNextLine()) {
                 choice = scanner.nextLine().trim().toUpperCase();
                 
                 if (choice.equals("R") || choice.equals("M")) {
                     return choice; // Valid choice received
                 } else {
                     System.out.println("\n\033[1;91m" + "Sweetie, please enter 'R' to Play Again or 'M' for Menu " + "\033[0m");
                 }
             }
        }
    }


    /**
     * Displays the welcome message (Team members only)
     */
    private static void displayWelcomeMessage() {
        // Clear screen before showing welcome message
        clearScreen();
        
        // Team Info structure, without ASCII art
        String teamInfo = 
            "\n" +
            "\033[1;95m" + "----------------------------------------------------------------------------------\n" +
            "\033[1;95m" + "|                                                                                |\n" +
            "\033[1;95m" + "|             ~ OUR AMAZING CMPE 343 FIRE TEAM ~                               |\n" +
            "\033[1;95m" + "|                                                                                |\n" +
            "\033[1;95m" + "|      NERGİS HÜSEYNOVA               |    BİRDEM ÜSTÜNDAĞ                     |\n" +
            "\033[1;95m" + "|      MAIMOONAH BAKR S. AL-MASHHADANI|    NUR SENA CANDAN                       |\n" +
            "\033[1;95m" + "|                                                                                |\n" +
            "\033[1;95m" + "|      May your code be bug-free and full of magic!                              |\n" +
            "\033[1;95m" + "|                                                                                |\n" +
            "\033[1;95m" + "----------------------------------------------------------------------------------\n" + "\033[0m" +
            "\n" +
            "\033[1;93m" + "              Let the coding magic begin! Press ENTER to continue..." + "\033[0m";
        
        System.out.println(teamInfo);
        
        // Wait for user input to continue to the main menu
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        clearScreen();
    }
    

    // --- Date Calculation Utility Methods (USING BUILT-IN FUNCTIONS) ---

    /**
     * Checks if the day, month, and year values are acceptable before attempting to create a LocalDate object.
     */
    private static boolean isValidInputDate(int day, int month, int year) {
        // We allow for a future year (TODAY.getYear() + 1) to distinguish between 'invalid range'
        // and 'future date' for a better error message in calculateAgeAndZodiac().
        if (year < 1900 || year > TODAY.getYear() + 1) return false;
        if (month < 1 || month > 12) return false;
        // The day must be between 1 and 31. LocalDate.of will handle month-specific day counts (e.g., Feb 30).
        if (day < 1 || day > 31) return false; 
        return true;
    }

    /**
     * Calculates the zodiac sign with its corresponding symbol. (UPDATED)
     */
    private static String calculateZodiacSign(int day, int month) {
        if ((month == 3 && day >= 21) || (month == 4 && day <= 20)) return "Aries ♈";
        if ((month == 4 && day >= 21) || (month == 5 && day <= 21)) return "Taurus ♉";
        if ((month == 5 && day >= 22) || (month == 6 && day <= 21)) return "Gemini ♊";
        if ((month == 6 && day >= 22) || (month == 7 && day <= 22)) return "Cancer ♋";
        if ((month == 7 && day >= 23) || (month == 8 && day <= 23)) return "Leo ♌";
        if ((month == 8 && day >= 24) || (month == 9 && day <= 23)) return "Virgo ♍";
        if ((month == 9 && day >= 24) || (month == 10 && day <= 23)) return "Libra ♎";
        if ((month == 10 && day >= 24) || (month == 11 && day <= 22)) return "Scorpio ♏";
        if ((month == 11 && day >= 23) || (month == 12 && day <= 21)) return "Sagittarius ♐";
        if ((month == 12 && day >= 22) || (month == 1 && day <= 20)) return "Capricorn ♑";
        if ((month == 1 && day >= 21) || (month == 2 && day <= 19)) return "Aquarius ♒";
        if ((month == 2 && day >= 20) || (month == 3 && day <= 20)) return "Pisces ♓";
        return "Invalid Day/Month Combination"; 
    }
}