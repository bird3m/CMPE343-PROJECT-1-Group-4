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
     * Displays the Primary School submenu with animated ASCII art and manages user selection.
     */
    public static void primarySchoolMenu() {
        boolean running = true;
        while (running) {
            clearScreen();
            
            // Animasyonlu ve renkli ASCII Art
            try {
                printAnimatedAsciiArt();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            // --- Menü ---
            System.out.println("\n\033[1;92m" + "------------------------------------" + "\033[0m");
            System.out.println("\033[1;96m" + "    PRIMARY SCHOOL FUN ZONE! " + "\033[0m");
            System.out.println("\033[1;92m" + "------------------------------------" + "\033[0m");
            System.out.println("\033[1;93m" + "    [1] Age & Zodiac Magic Calculator" + "\033[0m");
            System.out.println("\033[1;94m" + "    [2] Word Reversal Wonderland" + "\033[0m");
            System.out.println("\033[1;95m" + "    [3] Back to Main Menu" + "\033[0m");
            System.out.println("\033[1;92m" + "------------------------------------" + "\033[0m");
            
            String choice = getUserChoice("\n\033[1;36m" + "Choose your fun activity (1-3):" + "\033[0m");
            clearScreen();

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
                    return;
                default:
                    System.out.println("\n\033[1;91m" + "Oh no! That's not a valid choice! " + "\033[0m");
            }
        }
    }

    /**
     * Prints animated and colorful ASCII art for Primary School menu
     */
    private static void printAnimatedAsciiArt() throws InterruptedException {
        String asciiArt =
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣤⣄⡀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡸⠋⠀⠘⣇⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⠇⠀⠀⠀⢸⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡜⠀⠀⠀⠀⢸⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⠇⠀⠀⠀⠀⢸⠇⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡎⠀⠀⠀⠀⠀⢸⠀⠀⠀\n" +
            "⠀⠀⢀⣀⣀⣀⠀⠀⠀⠀⠀⢀⣀⣤⡤⠤⠤⠤⠤⢤⣤⣀⡤⢖⡿⠛⠉⢳⠀⠀⠀⠀⠀⢸⠀⠀⠀\n" +
            "⠀⢼⠁⠉⠉⠛⠻⢭⡓⠒⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠰⣏⠀⠀⠀⢸⠀⠀⠀⠀⠀⡤⠀⠀⠀\n" +
            "⠀⠸⡄⠀⠀⠀⠀⢸⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠂⠀⠀⡜⠀⠀⠀⠀⢀⡇⠀⠀⠀\n" +
            "⠀⠀⢷⠀⠀⠀⠠⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢣⢠⠏⠀⠀⠀⠀⢸⠃⠀⠀⠀\n" +
            "⠀⠀⠈⢧⠀⢀⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡞⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠈⢳⡈⠁⠀⠀⠀⠀⠀⣀⡀⠀⠀⠀⠀⠀⠀⠀⣶⣶⣦⠀⠀⢹⠀⠀⠀⠀⠀⡎⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⡇⠀⠀⠀⠀⢠⣾⣟⣹⡄⠀⠀⠀⠀⡀⠀⣿⣿⣿⡇⠀⢈⣧⠤⠤⠶⠶⢷⠒⠒⠂⠀\n" +
            "⠀⠀⢀⣀⣠⡧⠄⠀⠀⠀⣾⣿⣿⣿⠇⠀⠀⠀⠙⠁⠀⠙⠻⠿⠃⠀⠨⣼⣤⣀⡀⠀⠈⢧⠀⠀⠀\n" +
            "⠘⠉⠁⠀⢸⣤⡤⠀⠀⠀⠛⢿⡿⠋⠀⠀⠀⠀⠴⠦⠀⠀⠀⠀⠀⠐⣲⣯⡀⠀⠈⠙⠓⠺⣧⣄⡀\n" +
            "⠀⣀⡤⠚⠉⢳⡴⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡼⠃⠀⠈⠓⢦⡀⠀⠀⢸⠀⠈\n" +
            "⠀⠁⠀⢀⡔⠉⠙⡶⢄⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠴⠚⠁⠀⠀⠀⠀⠀⠀⠈⠓⠆⠀⡇⠀\n" +
            "⠀⠀⠰⠋⠀⠀⢸⡇⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠁⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠈⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡎⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠹⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠙⢆⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠄⠀⢰⠇⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⠶⠺⣇⠀⣀⡜⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢱⡄⠀⠀⠀⠹⡟⠒⢢⡀⠀⠀⠀⠀⢀⡏⠀⠀⠀⠈⠉⠉⠁⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⣄⠀⠀⢀⡇⠀⠀⠻⣄⠀⠀⠀⡸⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢷⠶⠋⠀⠀⠀⠀⠈⣣⠶⠖⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀";

    String[] colors = {
        "\033[1;91m", // kırmızı
        "\033[1;93m", // sarı
        "\033[1;92m", // yeşil
        "\033[1;94m", // mavi
        "\033[1;95m", // mor
        "\033[1;96m"  // camgöbeği
    };

    for (int i = 0; i < 2; i++) { // 3 yerine 2 döngü
        for (String color : colors) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(color + asciiArt + "\033[0m");
            Thread.sleep(80); // 150 yerine 80 ms
        }
    }
}
    
    /**
     * Calculates age (year, month, day) and zodiac sign from the birth date.
     */
    public static void calculateAgeAndZodiac() {
        clearScreen();
        
        // Animasyonlu ve renkli ASCII Art
        try {
            printAnimatedZodiacArt();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

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
                    // CUSTOM isAfter IMPLEMENTATION - Check if birth date is in the future
                    if (isDateAfter(birthDate, TODAY)) {
                         System.out.println("\n\033[1;41m\033[1;97m" + "!!! ERROR: TIME TRAVELER DETECTED! !!!" + "\033[0m");
                         System.out.println("\033[1;91m" + "The birth date can't be in the future (after " + TODAY + ")! " + "\033[0m");
                    } else {
                        dateValid = true;
                    }
                } catch (Exception e) {
                    System.out.println("\n\033[1;41m\033[1;97m" + "!!! ERROR: INVALID DATE COMBINATION! !!!" + "\033[0m");
                    System.out.println("\033[1;91m" + "Oopsie! That date combination (" + day + "." + month + "." + year + ") doesn't exist in our magical calendar! " + "\033[0m");
                }
            } else {
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
     * Prints animated and colorful ASCII art for Zodiac Calculator
     */
    private static void printAnimatedZodiacArt() throws InterruptedException {
        String asciiArt = 
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⡀⠀⠀⠀⠀⢀⣀⣀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⢀⣴⣶⣶⣶⣤⣄⣀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⠟⠉⠉⠻⣦⣠⣶⣿⠿⠟⠻⣷⡄⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⣾⡟⠁⠀⠈⠉⠛⠿⣿⣾⣿⠿⠿⠿⠿⢿⣿⠃⠀⠀⠀⠀⠙⣿⠉⠀⠀⠀⠀⢻⣧⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⢠⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠃⠀⣠⣾⠟⣿⣶⠿⠿⣶⣄⣠⣤⣼⣿⣀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠈⢿⣾⡿⠁⠀⠀⠀⢻⣏⡁⠀⠈⠹⣧⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣧⣄⣀⣠⣼⣷⡀⠀⠀⣰⣾⠟⣿⡄⠀⢸⡏⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⢰⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠉⠉⠙⠻⠶⠶⣿⠛⠛⠛⠀⢠⡿⠁⠀⠀⠀\n" +
            "⠀⠀⠀⠀⢠⣿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢷⣤⣤⣴⢿⣧⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠀⢸⣿⡀⠀⠀⠀\n" +
            "⠀⣀⣠⣤⣼⣿⣤⣀⠀⠀⠀⠀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠀⠀⠀⠀⠠⠶⠾⣿⡿⠿⠿⠷\n" +
            "⠈⠉⠉⠉⢹⣿⠉⠉⠀⠀⠀⢰⣿⣿⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⣷⠀⠀⠀⠀⠀⢰⣿⠀⠀⠀⠀\n" +
            "⠀⠀⠀⣀⣬⣿⣷⡶⠄⠀⠀⠈⠛⠛⠀⠀⠀⠀⠀⢀⡴⠶⣦⡀⠀⠀⠀⠀⠘⠛⠃⠀⠀⠀⠀⠙⣿⡿⠿⠷⠶⠀\n" +
            "⠀⠀⠈⠉⠉⠉⢿⣆⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⠷⣤⠼⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣴⣿⠃⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⣠⣼⠿⣿⣏⣤⡶⢶⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣽⡿⠛⠿⣶⡄⠀⠀\n" +
            "⠀⠀⠀⠀⠿⠋⠁⠀⣈⣿⡏⠀⠀⣿⠟⠻⣷⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣠⣤⣾⠿⠋⠀⠀⠀⠀⠁⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⣼⡏⠁⠁⢠⠤⣄⠀⢀⣾⣷⣶⣶⣶⣶⣶⣶⣶⣶⡾⠿⣿⣿⣿⣍⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠹⣷⣤⠄⠘⠷⠋⠈⠙⣷⢻⣇⠀⠀⠀⠀⠀⢰⣿⠀⠀⠘⣿⡌⢻⣿⣦⣀⣀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⢀⣾⠟⣿⡀⠀⣰⣦⣀⣴⡿⠂⠻⣷⣤⣤⣤⣶⡿⠃⠀⠀⠀⠸⣿⡿⠋⠉⠉⢻⣧⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⢸⣿⠀⠈⠛⠛⢿⣿⠹⣿⡀⠀⠀⠀⠈⠉⠉⠁⠀⠀⠀⠀⠀⠀⢻⣷⠀⠀⠀⢸⣏⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠈⢿⣧⡀⠀⠀⢸⣿⣷⡘⢿⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⡇⠀⠀⣼⡏⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠙⠿⣶⣶⠿⠋⠘⠿⣾⠿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⣶⡿⠟⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣧⣀⣀⣀⣀⠀⠀⠀⠀⠀⣤⠀⠀⠀⠀⠀⠀⣀⣀⣀⣤⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡏⠉⠛⠛⠛⠛⠿⠿⠿⠿⣿⠿⠿⠿⠿⠿⠿⠛⠛⠋⠉⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⣷⣤⣀⡀⠀⠀⠀⠀⣠⣿⣄⠀⠀⠀⠀⠀⣀⣤⣾⡿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠛⠛⠻⠿⠟⠛⠛⠛⠛⠛⠻⠿⠿⠟⠛⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀";

String[] colors = {
        "\033[1;91m", // kırmızı
        "\033[1;93m", // sarı
        "\033[1;92m", // yeşil
        "\033[1;94m", // mavi
        "\033[1;95m", // mor
        "\033[1;96m"  // camgöbeği
    };

    for (int i = 0; i < 2; i++) { // 3 yerine 2 döngü
        for (String color : colors) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(color + asciiArt + "\033[0m");
            Thread.sleep(80); // 150 yerine 80 ms
        }
    }
}
    
    /**
     * Custom implementation of isAfter function
     * @param date1 First date to compare
     * @param date2 Second date to compare
     * @return true if date1 is after date2, false otherwise
     */
    private static boolean isDateAfter(LocalDate date1, LocalDate date2) {
        // First compare years
        if (date1.getYear() > date2.getYear()) {
            return true;
        } else if (date1.getYear() < date2.getYear()) {
            return false;
        }
        
        // Years are equal, compare months
        if (date1.getMonthValue() > date2.getMonthValue()) {
            return true;
        } else if (date1.getMonthValue() < date2.getMonthValue()) {
            return false;
        }
        
        // Months are equal, compare days
        return date1.getDayOfMonth() > date2.getDayOfMonth();
    }
    
    /**
     * Custom implementation of isBefore function
     * @param date1 First date to compare
     * @param date2 Second date to compare
     * @return true if date1 is before date2, false otherwise
     */
    private static boolean isDateBefore(LocalDate date1, LocalDate date2) {
        // First compare years
        if (date1.getYear() < date2.getYear()) {
            return true;
        } else if (date1.getYear() > date2.getYear()) {
            return false;
        }
        
        // Years are equal, compare months
        if (date1.getMonthValue() < date2.getMonthValue()) {
            return true;
        } else if (date1.getMonthValue() > date2.getMonthValue()) {
            return false;
        }
        
        // Months are equal, compare days
        return date1.getDayOfMonth() < date2.getDayOfMonth();
    }
    
    /**
     * Initiates the operation to reverse the words in the text received from the user.
     */
    public static void reverseWordsOperation() {
        clearScreen();
        
        // Animasyonlu ve renkli ASCII Art
        try {
            printAnimatedWordReversalArt();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        System.out.println("\n\033[1;95m" + "-❤-❤-❤-❤-❤-❤-❤-❤-❤-❤-❤-❤-❤-❤-❤-❤-❤-❤-" + "\033[0m");
        System.out.println("\033[1;96m" + "    WORD REVERSAL WONDERLAND! " + "\033[0m");
        System.out.println("\033[1;95m" + "-❤-❤-❤-❤-❤-❤-❤-❤-❤-❤-❤-❤-❤-❤-❤-❤-❤-❤-" + "\033[0m");
        System.out.println("\033[1;93m" + "Enter your magical text below: "); 
        
        String input = "";
        
        while (scanner.hasNextLine()) {
            input = scanner.nextLine();
            if (!input.trim().isEmpty()) {
                break;
            }
        }

        String reversedText = reverseWordsInText(input); 
        
        System.out.println("\n\033[1;92m" + "Original Text:" + "\033[0m");
        System.out.println("\033[1;97m" + input + "\033[0m");
        System.out.println("\n\033[1;94m" + "Reversed Text:" + "\033[0m");
        System.out.println("\033[1;97m" + reversedText + "\033[0m"); 
    }

    /**
     * Prints animated and colorful ASCII art for Word Reversal
     */
    private static void printAnimatedWordReversalArt() throws InterruptedException {
        String asciiArt = 
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣴⣶⠿⠛⠛⠛⠉⠉⠉⠉⠀⠀⠉⠉⠉⠛⠛⠿⣶⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣾⠿⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⠻⣦⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣴⠟⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠻⠷⣶⣦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠀⠀⣠⣾⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢈⣻⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⢀⣠⣴⡾⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣾⣻⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⢠⣿⠟⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣀⣀⣀⠀⠀⠀⠀⠀⠘⣿⡟⠁⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⢻⣿⣶⢶⣤⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⡀⠀⠀⢼⡿⠟⠛⠛⠛⠿⠀⠀⠀⠀⠀⠘⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠉⠻⢷⣿⠇⠀⠀⠀⠀⢀⣴⡿⠿⠿⠿⠇⠀⠀⠀⢾⣿⣿⣿⣟⣿⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢿⣧⠀⠀⠀⠀⣀⣀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⣾⡟⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⣿⠛⠁⠀⠀⠀⠀⠀⠀⠸⡇⠀⠀⠀⠀⠶⠚⠛⠛⠿⢿⣿⣿⣷⣾⠟⢻⣷⣀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⣿⠃⠀⣀⣀⠀⠀⠀⠀⠀⢸⣷⠀⠀⠀⠀⠀⠀⠀⠀⣿⣄⡀⠀⠀⠀⠀⠀⣀⣼⠇⠀⠀⠀⠀⢤⣤⣄⣀⠀⢸⣿⡿⠀⠀⠀⠈⠛⣿⡇\n" +
            "⠀⠀⠀⠀⠀⣸⣿⠟⠛⠉⠉⠁⠀⠀⠀⠀⠘⢿⣦⣄⣀⣀⣀⣠⣴⠞⠉⠙⠛⢿⣶⠶⠶⠿⠛⠁⠀⠀⠀⠀⠀⠀⠀⠉⠙⢷⣾⠟⠁⠀⠀⠀⠀⠀⣿⡅\n" +
            "⠀⠀⠀⣴⠟⢻⡇⠀⠀⣠⡴⠖⠀⠀⠀⠀⠀⠀⠉⠉⠛⠛⠛⠻⣧⡀⠀⠀⢀⣴⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡾⠋⠀⠀⠀⠀⠀⠀⢀⣿⠏\n" +
            "⠀⠀⠈⠁⠀⢸⣇⣴⠞⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⠷⠾⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣼⠟⠁⠀⠀⠀⠀⠀⠀⠀⣾⠃⠀\n" +
            "⠀⠀⠀⠀⠀⢠⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡾⠃⠀⠀⠀⠀⠀⠀⠀⠀⣼⡟⠀⠀\n" +
            "⠀⠀⠀⠀⢠⡿⠉⢿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠜⠁⠀⠀⠀⠀⠀⠀⠀⠀⣰⣿⠁⠀⠀\n" +
            "⠀⠀⠀⠀⠘⠀⠀⢸⣿⠻⢷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣿⠃⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⢠⣿⠃⠀⠀⠈⠓⠒⢦⠰⠆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣿⠃⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⢠⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⡿⠃⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⢠⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⡟⠁⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⢀⣿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⢀⣼⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⣿⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⣠⣾⠟⠀⠀⠀⠀⠀⠀⠀⠀⣠⣤⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠈⢿⣇⡀⠀⠀⠀⢀⣠⣴⣶⡿⠛⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠈⠻⢷⣶⣶⣿⠿⠛⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀\n" +
            "⠀⠀⠀⠀⠀⠀⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠿⠀⠀⠀⠀⠀⠀⠀⠀";

    String[] colors = {
        "\033[1;92m", // yeşil
        "\033[1;96m", // camgöbeği
        "\033[1;95m", // mor
        "\033[1;93m", // sarı
        "\033[1;94m", // mavi
        "\033[1;91m"  // kırmızı
    };

    for (int i = 0; i < 1; i++) { // 2 yerine 1 döngü
        for (String color : colors) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(color + asciiArt + "\033[0m");
            Thread.sleep(100); // 200 yerine 100 ms
        }
    }
}

    // ... (geri kalan metodlar aynı kalacak - reverseWordsInText, getValidatedInteger, clearScreen, vb.)
    
    /**
     * Recursively reverses the words in the text.
     * Handles words containing mixed letters and numbers (e.g., "word123").
     * @param text The text to be reversed.
     * @return The text with reversed words.
     */
    public static String reverseWordsInText(String text) {
        String[] tokens = text.split("(?<=\\s)|(?=\\s)|(?<=[^\\p{L}0-9])|(?=[^\\p{L}0-9])");
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
            return "";
        }

        String token = tokens[index];
        String processedToken;

        if (token.matches(".*[\\p{L}0-9]+.*") && token.length() >= 2) { 
            processedToken = reverseWordPreservingNonLetters(token);
        } else {
            processedToken = token;
        }

        return processedToken + processTokensRecursively(tokens, index + 1);
    }
    
    /**
     * Reverses a word while preserving the positions of non-letter characters (digits, punctuation).
     * @param word The word to reverse
     * @return The reversed word with non-letter characters in their original positions
     */
    private static String reverseWordPreservingNonLetters(String word) {
        StringBuilder letters = new StringBuilder();
        StringBuilder result = new StringBuilder();
        int[] nonLetterPositions = new int[word.length()];
        char[] nonLetterChars = new char[word.length()];
        
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (Character.isLetter(c)) {
                letters.append(c);
                nonLetterPositions[i] = -1;
            } else {
                nonLetterPositions[i] = 1;
                nonLetterChars[i] = c;
            }
        }
        
        String reversedLetters = reverseStringRecursively(letters.toString());
        
        int letterIndex = 0;
        for (int i = 0; i < word.length(); i++) {
            if (nonLetterPositions[i] == -1) {
                result.append(reversedLetters.charAt(letterIndex++));
            } else {
                result.append(nonLetterChars[i]);
            }
        }
        
        return result.toString();
    }
    
    /**
     * Recursively reverses the given string.
     * @param s The string to be reversed.
     * @return The reversed string.
     */
    private static String reverseStringRecursively(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
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
                if (scanner.hasNextInt()) {
                    number = scanner.nextInt();
                    validInput = true;
                } else {
                    System.out.println("\n\033[1;41m\033[1;97m" + "!!! ERROR: NON-NUMERIC INPUT! !!!" + "\033[0m");
                    System.out.println("\033[1;91m" + "That's not a valid number! Please enter digits only! " + "\033[0m"); 
                    scanner.next();
                }
            } catch (Exception e) {
                System.out.println("\n\033[1;41m\033[1;97m" + "!!! ERROR: UNEXPECTED ISSUE! !!!" + "\033[0m");
                System.out.println("\033[1;91m" + "Oops! Something went wrong: " + e.getMessage() + " " + "\033[0m");
                scanner.nextLine();
            }
        }
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
            System.out.print("\033[H\033[2J");
            System.out.flush();

            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
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
        
        String choice = getValidatedRMChoice("\n\033[1;36m" + "Enter your choice (R/M):" + "\033[0m").toUpperCase();
        
        if (choice.equals("R")) {
            clearScreen();
            repeatMethod.run();
            askForRepeatOrReturn(repeatMethod, menuMethod);
        } else if (choice.equals("M")) {
            clearScreen();
            menuMethod.run();
        } else {
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
        if (scanner.hasNextLine()) {
            return scanner.nextLine().trim();
        }
        return "";
    }
    
    /**
     * Gets a validated R or M choice from the user, handling empty inputs robustly.
     */
    private static String getValidatedRMChoice(String prompt) {
        String choice = "";
        
        while (true) {
             System.out.print(prompt + " ");
             if (scanner.hasNextLine()) {
                 choice = scanner.nextLine().trim().toUpperCase();
                 
                 if (choice.equals("R") || choice.equals("M")) {
                     return choice;
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
        clearScreen();
        
        String teamInfo = 
            "\n" +
            "\033[1;95m" + "----------------------------------------------------------------------------------\n" +
            "\033[1;95m" + "|                                                                                |\n" +
            "\033[1;95m" + "|             ~ OUR AMAZING CMPE 343 FIRE TEAM ~                                 |\n" +
            "\033[1;95m" + "|                                                                                |\n" +
            "\033[1;95m" + "|      NERGİS HÜSEYNOVA               |    BİRDEM ÜSTÜNDAĞ                     |\n" +
            "\033[1;95m" + "|      MAIMOONAH BAKR S. AL-MASHHADANI|    NUR SENA CANDAN                     |\n" +
            "\033[1;95m" + "|                                                                                |\n" +
            "\033[1;95m" + "|      May your code be bug-free and full of magic!                              |\n" +
            "\033[1;95m" + "|                                                                                |\n" +
            "\033[1;95m" + "----------------------------------------------------------------------------------\n" + "\033[0m" +
            "\n" +
            "\033[1;93m" + "              Let the coding magic begin! Press ENTER to continue..." + "\033[0m";
        
        System.out.println(teamInfo);
        
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        clearScreen();
    }
    
    // --- Date Calculation Utility Methods ---

    /**
     * Checks if the day, month, and year values are acceptable before attempting to create a LocalDate object.
     */
    private static boolean isValidInputDate(int day, int month, int year) {
        if (year < 1900 || year > TODAY.getYear() + 1) return false;
        if (month < 1 || month > 12) return false;
        if (day < 1 || day > 31) return false; 
        return true;
    }

    /**
     * Calculates the zodiac sign with its corresponding symbol.
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