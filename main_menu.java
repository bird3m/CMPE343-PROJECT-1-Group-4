import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*; //for scanner,lists, random



public class main_menu
{

	public static void main(String[] args) 
	{
		callMenu();
	}
	
	//method for clearing the terminal
	public static void clearConsole() 
	{
    	 	System.out.print("\033[H\033[2J");
   	 		System.out.flush();
	}
	
    static boolean animationFlag = false; // so the animation plays only once
	public static void callMenu()
	{

		while(true)
		{
            if(!animationFlag)
			{
                playNyanCat();
                animationFlag = true;
            }
            
            showWelcomeScreen();


			System.out.println("\nPlease select one of the options below: \n[A] Primary School");
			System.out.println("[B] Secondary School");
			System.out.println("[C] High School");
			System.out.println("[D] University");
			System.out.println("[E] Terminate");

			Scanner input = new Scanner(System.in);
			System.out.printf("Select one of the options above:");
			String str = input.nextLine();
			System.out.printf("%s", str);

              if (str.isEmpty())
            {
                System.err.println("\nInvalid input. Try again!!!!!!!");
                input.nextLine();
                continue;
            }

			if (str.equals("A"))
			{
				primarySchoolMenu();
				break;
			}
			else if (str.equals("B"))
			{
				//call func
				break;
			}
			else if (str.equals("C"))
			{
				//call func
				break;
			}
			else if (str.equals("D"))
			{
				clearConsole();
				connectFour();
				break;
			}
			else if (str.equals("E"))
			{
				System.exit(0);
	
			}
			else
				System.err.println("\nInvalid input. Try again!!!!!!!");

            
			
			
		}	

        
	}


    /**
     * Plays the ASCII animation.
     */
static void playNyanCat() 
{
    final String nyanCat = """
                 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@                
                @@@:...................................@@@              
              @@@-:.....::::::::::::::::::::::::::.....:-@@@            
              @@@......::::::::::-++::::=+-::::::::......@@@            
              @@@....::---:::::::-++::::=+-::::::::::....@@@            
              @@@..::::+*=::::::::::::::-----::::--::::..@@@ @@@@@      
              @@@..:::::::::::::::::::::@@@@#:::-*+::::..@@@@@@@@@@     
              @@@..::::::::::::::::::-@@*---@@%::::::::..@@@@%---#@@    
 @@@@@@@@@@   @@@..::::::::::::-*+-::-@@*-----%@@::::::..@@+-----#@@    
 @@@#+++@@@@@ @@@..:::::::::::::--:::-@@*-----=+*@@@@@@@@#+------#@@    
 @@@@@#-==@@@@@@@..::::::=*=:::::::::-@@*--------========--------#@@    
 @@@@@@#+-#@@@@@@..::::::=+-:::::--=+*@@+------------------------*@@@@@ 
    @@@@@@#-=#@@@..::=+::::::::::-==@@*-----. =@%----------  +@*---*@@@ 
      @@@@@=--#@@..::+*-::::::::::::@@*-----  =@@----------  *@@---*@@@ 
        @@@@@@@@@..:::::::::::-:::::@@*-----%@@@@-----+@@*-@@@@%---+@@@ 
            @@@@@..::::::::::=**::::@@*-:::::------------------::::=@@@ 
              @@@.....:**=::::::::::@@*-:::::-%@@---=@@=---@@%-::::=@@@ 
              @@@......::::::::::::::-@@+-----%@@@@@@@@@@@@@@%---#@@@   
            @@@@@@@...................:-@@*---=**************=-#@@@@    
          @@@######@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@      
          @@@---=*%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@        
          @@@+++*@@@@@ @@@+++%@@          @@@+++#@@@@@@*+++@@@          
          @@@@@@@@@@   @@@@@@@              @@@@@@@@  @@@@@@            
                                                                        
""";

     String[] raw = nyanCat.split("\n");

    int steps = 80; // number of steps the cat will take
    for (int x = 0; x <= steps; x++) 
	{
        clearConsole();                  
        for (int r = 0; r < raw.length; r++)
		{
			String indent = rainbowPrefix(x, r, raw.length); 
        	System.out.println(indent + raw[r]);
		}        
        try { Thread.sleep(20); } catch (InterruptedException ignored) {}

	
	}
}

/**
 * Displays the welcome message in blue color.
 */
static void showWelcomeScreen()
{
   String banner =  BLUE_FG + """
  _     _  _______  ___      _______  _______  __   __  _______    ___   ____   
| | _ | ||       ||   |    |       ||       ||  |_|  ||       |  |   | |    |  
| || || ||    ___||   |    |       ||   _   ||       ||    ___|  |___| |_    | 
|       ||   |___ |   |    |       ||  | |  ||       ||   |___    ___    |   | 
|       ||    ___||   |___ |      _||  |_|  ||       ||    ___|  |   |   |   | 
|   _   ||   |___ |       ||     |_ |       || ||_|| ||   |___   |___|  _|   | 
|__| |__||_______||_______||_______||_______||_|   |_||_______|        |____|  
""" + RESET; 

System.out.println(banner);

}

/**
 * Displays a rainbow trail behind the cat.
 * @param x
 * @param row
 * @param totalRows
 * @return
 */
static String rainbowPrefix(int x, int row, int totalRows)
{
	//ANSI codes for the rainbow trail behind the kitty 
    final String RESET   = "\033[0m";
    final String RED     = "\033[38;5;196m";
    final String ORANGE  = "\033[38;5;208m";
    final String YELLOW  = "\033[38;5;226m";
    final String GREEN   = "\033[38;5;46m";
    final String BLUE    = "\033[38;5;33m";
    final String MAGENTA = "\033[38;5;201m";
    String[] palette = { RED, ORANGE, YELLOW, GREEN, BLUE, MAGENTA };

    int stripes = 6;
    int top = Math.max(0, totalRows/2 - stripes/2); //top of the stripes, this centers the body of the cat, so the rainbow starts from the middle of it
    boolean isStripe = row >= top && row < top + stripes;

    if (!isStripe)
	{
        return " ".repeat(x); //when theres no rainbow its an empty character
    }

	else 
	{
        String color = palette[row - top];
        //the shape of the character (so it can look a bit wavy and cool)
        char ch = ((x + row) % 3 == 0 ? '=' : ((x + row) % 3 == 1 ? '~' : '-'));
        return color + ("" + ch).repeat(x) + RESET; //repeat that character x times. RESET resets the color code of the terminal.
    }
}

//-------------------------------------------------PRIMARY SCHOOL---------------------------------------------------
/**
     * Displays the Primary School submenu and manages user selection.
     */
    static Scanner scanner = new Scanner(System.in);
    static final LocalDate TODAY = LocalDate.now();

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
            
            clearConsole(); // The screen should be cleared after submenu selection

            switch (choice) {
                case "1":
                    calculateAgeAndZodiac();
                    askForRepeatOrReturn(main_menu::calculateAgeAndZodiac, main_menu::primarySchoolMenu);
                    return; 
                case "2":
                    reverseWordsOperation();
                    askForRepeatOrReturn(main_menu::reverseWordsOperation, main_menu::primarySchoolMenu);
                    return;
                case "3":
                    return; // Exit the primarySchoolMenu method and return to mainMenu
                default:
                    System.out.println("\n\033[1;91m" + "Oh no! That's not a valid choice! " + "\033[0m");
            }
        }
    }

     /**
     * Gets a menu choice (A-E or 1-3) from the user.
     */
    public static String getUserChoice(String prompt) {
        System.out.print(prompt + " ");
        // Reads the entire line, which is necessary for menu choices.
        if (scanner.hasNextLine()) {
            return scanner.nextLine().trim();
        }
        return "";
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
        
        System.out.println("\033[1;96m" + "    YOUR MAGICAL RESULTS:" + "\033[0m");

        System.out.println("\033[1;93m" + "    Your Age: " + years + " years, " + months + " months, " + days + " days "); 
        System.out.println("\033[1;94m" + "    Your Zodiac Sign: " + zodiac + " ");  
        
    }
    
    /**
     * Initiates the operation to reverse the words in the text received from the user.
     */
    public static void reverseWordsOperation() {
        System.out.println("\033[1;96m" + "    WORD REVERSAL WONDERLAND! " + "\033[0m");
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
     * Asks the user to repeat an operation or return to the submenu after a process.
     * @param repeatMethod The method to be called to repeat the operation.
     */
    private static void askForRepeatOrReturn(Runnable repeatMethod, Runnable menuMethod) {
        System.out.println("\033[1;96m" + "    What would you like to do next? " + "\033[0m");
        System.out.println("\033[1;93m" + "    [R] Play Again (Repeat Operation)" + "\033[0m");
        System.out.println("\033[1;94m" + "    [M] Back to Main Menu" + "\033[0m");
        
        // We use a validated choice getter to ensure 'R' or 'M' is correctly read, 
        // preventing the program from treating a newline as an invalid option.
        String choice = getValidatedRMChoice("\n\033[1;36m" + "Enter your choice (R/M):" + "\033[0m").toUpperCase();
        
        if (choice.equals("R")) {
            clearConsole();
            repeatMethod.run(); 
            // After the repeated operation is finished, we call itself to ask for R/M again (recursive)
            askForRepeatOrReturn(repeatMethod, menuMethod);
        } else if (choice.equals("M")) {
            clearConsole();
            callMenu();
        } else {
            // This path should ideally not be reached
            System.out.println("\n\033[1;91m" + "Oopsie! Let's go back to the menu! " + "\033[0m");
            clearConsole();
            callMenu();
        }
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
        if ((month == 3 && day >= 21) || (month == 4 && day <= 20)) return "Aries";
        if ((month == 4 && day >= 21) || (month == 5 && day <= 21)) return "Taurus";
        if ((month == 5 && day >= 22) || (month == 6 && day <= 21)) return "Gemini ";
        if ((month == 6 && day >= 22) || (month == 7 && day <= 22)) return "Cancer ";
        if ((month == 7 && day >= 23) || (month == 8 && day <= 23)) return "Leo ";
        if ((month == 8 && day >= 24) || (month == 9 && day <= 23)) return "Virgo ";
        if ((month == 9 && day >= 24) || (month == 10 && day <= 23)) return "Libra ";
        if ((month == 10 && day >= 24) || (month == 11 && day <= 22)) return "Scorpio ";
        if ((month == 11 && day >= 23) || (month == 12 && day <= 21)) return "Sagittarius ";
        if ((month == 12 && day >= 22) || (month == 1 && day <= 20)) return "Capricorn ";
        if ((month == 1 && day >= 21) || (month == 2 && day <= 19)) return "Aquarius ";
        if ((month == 2 && day >= 20) || (month == 3 && day <= 20)) return "Pisces";
        return "Invalid Day/Month Combination"; 
    }


//------------------------------------------------------------------------------------------------------------------


//-------------------------------------------------- CONNECT FOUR ---------------------------------------------------
		//game constants
		static final int EMPTY = 0;
		static final int P1 = 1; // Red player 1
		static final int P2 = 2; // Yellow player 2


		static int rows, cols;
		static int[][] grid;   // [row][col]
		static int[] heights;  // amount of pieces in each column

		
		static final String RESET  = "\u001B[0m";

        // ANSI escape codes for foreground colors. 
		static final String RED_FG    = "\u001B[38;5;196m";
		static final String YELLOW_FG = "\u001B[38;5;226m";
		static final String BLUE_FG   = "\u001B[38;5;45m";
		static final String GRAY_FG   = "\u001B[38;5;240m";

/**
 * The main function that runs the Connect Four game.
 * This method manages the entire game loop; from player setup to move execution,
 * win detection, and post-game options. It supports both: Single-player mode (vs. computer using minimax AI) && Two-player local mode.
 * AI uses Minimax + Alpha-Beta pruning.
 * After each move, the screen refreshes and the updated board is rendered in ASCII with colors.
 * The game checks for a win condition or a tie.
 * At the end, the winner or draw message is displayed. Then the player can choose to go back to the main menu or replay.
 */
static void connectFour()
{
    Scanner sc = new Scanner(System.in);
    printTitle();

    int[] size = pickSize(sc); // [cols, rows]
    cols = size[0];
    rows = size[1];
    initBoard(rows, cols);

    int mode = pickMode(sc);

    int humanPlayer = P1; // player 1
    if (mode == 1)
    {
            // single player
        humanPlayer = pickHumanSide(sc);
    }

    int current = P1;
    boolean gameOver = false;
    Integer forfeitWinner = null;

    while (!gameOver) 
    {
        clearConsole();
        printTitle();
        System.out.println(render());

        if (mode == 1)
        {
            if (current == humanPlayer) 
            {
                System.out.printf("Player %s (You), choose a column 1-%d or 'q' to forfeit: ", token(current), cols);
                String in = sc.nextLine().trim();

                //*Returns true if the strings are equal */
                if (in.equalsIgnoreCase("q")) 
                {
                    forfeitWinner = opponent(current);
                    break;
                }
                Integer col = parseColumn(in, cols);
                if (col == null) continue;
                if (!isValidMove(col)) {
                    System.out.println("\nColumn is full. Pick another.");
                    pause(sc);
                    continue;
                }
                makeMove(col, current);
            } 
            else 
            {
                System.out.printf("Computer (%s) is thinking...\n", token(current));
                int searchDepth = pickSearchDepth(); // depth based on the board size
                int move = chooseAIMove(current, searchDepth);

                System.out.printf("Computer plays column %d.\n", move + 1);
                makeMove(move, current);
                sleep(300);
            }
        }
            else 
            {
            System.out.printf("Player %s, choose a column 1-%d or 'q' to forfeit: ", token(current), cols);
            String in = sc.nextLine().trim();
            if (in.equalsIgnoreCase("q")) {
                forfeitWinner = opponent(current);
                break;
            }
            Integer col = parseColumn(in, cols);
            if (col == null) continue;
            if (!isValidMove(col)) {
                System.out.println("\nColumn is full. Pick another.");
                pause(sc);
                continue;
            }
            makeMove(col, current);
        }

        
        if (isWinningMove(current)) 
        {
            clearConsole();
            printTitle();
            System.out.println(render());
            if (mode == 1 && current != humanPlayer) 
            {
                System.out.printf("Computer (%s) wins!\n", token(current));
            } 
            else if (mode == 1 && current == humanPlayer) 
            {
                System.out.println("You win!");
            }
            else
            {
                System.out.printf("Player %s wins!\n", token(current));
            }
            gameOver = true;
        } 
        else if (isFull()) 
        {
            clearConsole();
            printTitle();
            System.out.println(render());
            System.out.println("It's a draw.");
            gameOver = true;
        } 
        else 
        {
            current = opponent(current);
        }
    }

    if (forfeitWinner != null)
    {
        clearConsole();
        printTitle();
        System.out.println(render());
        if (mode == 1 && forfeitWinner != humanPlayer) 
        {
            System.out.printf("You forfeited. Computer (%s) wins.\n", token(forfeitWinner));
        } 
        else if (mode == 1 && forfeitWinner == humanPlayer)
        {
            System.out.println("Computer forfeited. You win!");
        } 
        else 
        {
            System.out.printf("Player %s wins by forfeit.\n", token(forfeitWinner));
        }
    }

    //After the game ends, we ask the user if they want to return or play again.
    while(true)
    {
        System.out.println("\nThanks for playing! \n Would you like to play again or return to the main menu?");
        System.out.println("1) Play again! ");
        System.out.println("2) Return to Main Menu.");

        Scanner input = new Scanner(System.in);
        String ans = input.nextLine().trim();

        if (ans.equals("1"))
        {
            clearConsole();
            connectFour();
            break;
        }
        else if (ans.equals("2"))
        {
            clearConsole();
            callMenu();
            break;

        }
        else
        {
            System.err.println("Invalid input. Please try again.");
        }
    }
}

/**
 * checks if any of the players are in the winning condition
 * @return if any of the players have won, or if the board is full (draw)
 */
static boolean isTerminal() 
{
    return isWinningMove(P1) || isWinningMove(P2) || isFull();
}

/**
 * When we look at moves deep in the tree, if we have a big tree (bigger matrix and number of moves) we wont go so deep so itll be efficient.
 * @return the depth level
 */
static int pickSearchDepth()
{
    int cells = rows * cols;
    if (cells <= 24) 
         return 7;  // 5x4 
    if (cells <= 30) 
        return 6;  // 6x5
    return 5;// 7x6 
}

/**
 * Minimax search algorithm with alpha-beta pruning.
 * Recursively explores possible move sequences and returns an evaluation score. 
 *
 * @param board The board to evaluate.
 * @param heightsCopy The height array for this board.
 * @param depth The current search depth.
 * @param maximizing True if maximizing for the AI, false for minimizing (human).
 * @param aiPlayer The AI player's token (P1 or P2).
 * @param alpha The current alpha bound.
 * @param beta The current beta bound.
 * @return The evaluation score for the position.
 */
static int minimax(int[][] board, int[] heightsCopy, int depth, boolean maximizing, int aiPlayer, int alpha, int beta) 
{
    int human = opponent(aiPlayer);

    if (depth == 0 || isTerminal(board, heightsCopy)) {
        if (isWinningMove(board, aiPlayer)) return 1_000_000 - depth;
        if (isWinningMove(board, human)) return -1_000_000 + depth;
        return scorePosition(board, aiPlayer);
    }

    if (maximizing) {
        int value = Integer.MIN_VALUE;
        for (int col : orderedValidMoves(board, heightsCopy)) {
            int[][] b2 = copyBoard(board);
            int[] h2 = Arrays.copyOf(heightsCopy, heightsCopy.length);
            makeMove_minmax(b2, h2, col, aiPlayer);
            int child = minimax(b2, h2, depth - 1, false, aiPlayer, alpha, beta);
            value = Math.max(value, child);
            alpha = Math.max(alpha, value);
            if (alpha >= beta) break; // prune
        }
        return value;
    } else {
        int value = Integer.MAX_VALUE;
        for (int col : orderedValidMoves(board, heightsCopy)) {
            int[][] b2 = copyBoard(board);
            int[] h2 = Arrays.copyOf(heightsCopy, heightsCopy.length);
            makeMove_minmax(b2, h2, col, human);
            int child = minimax(b2, h2, depth - 1, true, aiPlayer, alpha, beta);
            value = Math.min(value, child);
            beta = Math.min(beta, value);
            if (alpha >= beta) break; // prune
        }
        return value;
    }
}

/**
 *Evaluates a 4 cell window of the board and assigns a heuristic score.
 * This function is used by the AI to estimate how good a given position is.
 * @param r Starting row of the window.
 * @param c Starting column of the window. 
 * @param dr Row direction increment.
 * @param dc  Column direction increment.
 * @param player Current player.
 * @param opponent Opposing player.
 * @return
 */
static int scoreWindow(int r, int c, int dr, int dc, int player, int opponent)
 {
        int my = 0, opp = 0, empty = 0;
    for (int k = 0; k < 4; k++)
     {
        int v = grid[r + k*dr][c + k*dc];
        if (v == player) my++;
        else if (v == opponent) opp++;
        else empty++;
    }
    if (my > 0 && opp > 0) 
        return 0;
    if (my == 4) 
        return 100000;      // extra winning points
    if (opp == 4) 
        return -100000;    // punishment if the opponent won
    if (my == 3 && empty == 1) 
        return 500;
    if (my == 2 && empty == 2) 
        return 50;
    if (opp == 3 && empty == 1) 
        return -400; // if theres a threat, lesser points.
    if (opp == 2 && empty == 2) 
        return -40;
    return 0;
}

/**
 * Evaluates the strength of a given board position for a player.
 * This heuristic function is used by the minimax algorithm to guide search.
 *
 * @param board The board to evaluate.
 * @param player The player whose perspective is being evaluated.
 * @return A numerical score (higher means better for this player).
 */
static int scorePosition(int[][] board, int player) {
    int opponent = opponent(player);
    int rowsLocal = board.length, colsLocal = board[0].length;
    int score = 0;

    // Center column preference (strategically strongest area)
    int center = colsLocal / 2;
    int centerCount = 0;
    for (int r = 0; r < rowsLocal; r++) if (board[r][center] == player) centerCount++;
    score += centerCount * 6;

    // Evaluate all 4-cell windows
    for (int r = 0; r < rowsLocal; r++)
        for (int c = 0; c + 3 < colsLocal; c++)
            score += scoreWindow(board, r, c, 0, 1, player, opponent); // horizontal

    for (int r = 0; r + 3 < rowsLocal; r++)
        for (int c = 0; c < colsLocal; c++)
            score += scoreWindow(board, r, c, 1, 0, player, opponent); // vertical

    for (int r = 0; r + 3 < rowsLocal; r++)
        for (int c = 0; c + 3 < colsLocal; c++)
            score += scoreWindow(board, r, c, 1, 1, player, opponent); // diagonal down-right

    for (int r = 3; r < rowsLocal; r++)
        for (int c = 0; c + 3 < colsLocal; c++)
            score += scoreWindow(board, r, c, -1, 1, player, opponent); // diagonal up-right

    return score;
}


/**
 * Creates a deep copy of the given board array.
 * Used by the minimax algorithm to simulate moves without modifying the global board.
 *
 * @param src The source board to copy.
 * @return A deep copy of the board.
 */
static int[][] copyBoard(int[][] src) {
    int[][] copy = new int[src.length][src[0].length];
    for (int r = 0; r < src.length; r++) System.arraycopy(src[r], 0, copy[r], 0, src[r].length);
    return copy;
}

/**
 * Simulates placing a piece on a copied board for minimax evaluation.
 * This method does not modify the global board.
 *
 * @param board The board to modify (copy of the real grid).
 * @param heightsCopy The heights array associated with the board.
 * @param col The column where the piece will be dropped.
 * @param player The player making the move (P1 or P2).
 */
static void makeMove_minmax(int[][] board, int[] heightsCopy, int col, int player) {
    int row = board.length - 1 - heightsCopy[col];
    board[row][col] = player;
    heightsCopy[col]++;
}

/**
 * Returns a list of valid columns (available moves) for a given board state.
 *
 * @param board The current board to check.
 * @param heightsCopy The heights array tracking column fill levels.
 * @return A list of column indices that can still accept a piece.
 */
static List<Integer> getValidMoves(int[][] board, int[] heightsCopy) {
    List<Integer> moves = new ArrayList<>();
    int rowsLocal = board.length, colsLocal = board[0].length;
    for (int c = 0; c < colsLocal; c++) if (heightsCopy[c] < rowsLocal) moves.add(c);
    return moves;
}

/**
 * Sorts the valid moves so that central columns are evaluated first.
 * Improves alpha-beta pruning efficiency by exploring stronger moves earlier.
 *
 * @param board The current board.
 * @param heightsCopy The heights array for this board.
 * @return A list of valid columns sorted by proximity to the board center.
 */
static List<Integer> orderedValidMoves(int[][] board, int[] heightsCopy) {
    List<Integer> moves = getValidMoves(board, heightsCopy);
    int colsLocal = board[0].length;
    moves.sort((a, b) -> Integer.compare(Math.abs(a - colsLocal / 2), Math.abs(b - colsLocal / 2)));
    return moves;
}

/**
 * Checks if a given player has achieved a four-in-a-row on a copied board.
 *
 * @param board The board to check.
 * @param player The player to test (P1 or P2).
 * @return True if the player has a connect-four, otherwise false.
 */
static boolean isWinningMove(int[][] board, int player) {
    int rowsLocal = board.length, colsLocal = board[0].length;
    for (int r = 0; r < rowsLocal; r++) {
        for (int c = 0; c < colsLocal; c++) {
            if (board[r][c] != player) continue;
            if (c + 3 < colsLocal &&
                board[r][c + 1] == player && board[r][c + 2] == player && board[r][c + 3] == player) return true;
            if (r + 3 < rowsLocal &&
                board[r + 1][c] == player && board[r + 2][c] == player && board[r + 3][c] == player) return true;
            if (r + 3 < rowsLocal && c + 3 < colsLocal &&
                board[r + 1][c + 1] == player && board[r + 2][c + 2] == player && board[r + 3][c + 3] == player) return true;
            if (r - 3 >= 0 && c + 3 < colsLocal &&
                board[r - 1][c + 1] == player && board[r - 2][c + 2] == player && board[r - 3][c + 3] == player) return true;
        }
    }
    return false;
}

/**
 * Checks if the given board is completely filled (draw condition).
 *
 * @param heightsCopy The column heights for this board.
 * @param rowsLocal The number of rows in the board.
 * @return True if the board is full, otherwise false.
 */
static boolean isFull(int[] heightsCopy, int rowsLocal) {
    for (int h : heightsCopy) if (h < rowsLocal) return false;
    return true;
}

/**
 * Determines whether the game state is terminal (win or draw).
 *
 * @param board The board to check.
 * @param heightsCopy The column heights array.
 * @return True if the position is terminal, otherwise false.
 */
static boolean isTerminal(int[][] board, int[] heightsCopy) {
    return isWinningMove(board, P1) || isWinningMove(board, P2) || isFull(heightsCopy, board.length);
}



/**
 * Scores a 4-cell window on the board based on the players and opponents pieces.e
 *
 * @param board The board.
 * @param r Starting row of the window.
 * @param c Starting column of the window.
 * @param dr Row direction increment.
 * @param dc Column direction increment.
 * @param player The player being evaluated.
 * @param opponent The opposing player.
 * @return The score contribution from this window.
 */
static int scoreWindow(int[][] board, int r, int c, int dr, int dc, int player, int opponent) {
    int my = 0, opp = 0, empty = 0;
    for (int k = 0; k < 4; k++)
    {
        int v = board[r + k * dr][c + k * dc];
        if (v == player) my++;
        else if (v == opponent) opp++;
        else empty++;
    }
    if (my > 0 && opp > 0) return 0;
    if (my == 4) return 100000;
    if (opp == 4) return -100000;
    if (my == 3 && empty == 1) return 500;
    if (my == 2 && empty == 2) return 50;
    if (opp == 3 && empty == 1) return -400;
    if (opp == 2 && empty == 2) return -40;
    return 0;
}

/**
 * Chooses the best move for the AI using minimax with alpha-beta pruning.
 *
 * @param aiPlayer The AI player (P1 or P2).
 * @param depth The maximum search depth.
 * @return The column index that represents the best move.
 */
static int chooseAIMove(int aiPlayer, int depth) {
    int bestCol = -1;
    int bestScore = Integer.MIN_VALUE;

    for (int col : orderedValidMoves(grid, heights)) {
        int[][] b2 = copyBoard(grid);
        int[] h2 = Arrays.copyOf(heights, heights.length);
        makeMove_minmax(b2, h2, col, aiPlayer);

        int score = isWinningMove(b2, aiPlayer)
                ? 1_000_000
                : minimax(b2, h2, depth - 1, false, aiPlayer, Integer.MIN_VALUE, Integer.MAX_VALUE);

        if (score > bestScore) {
            bestScore = score;
            bestCol = col;
        }
    }

    if (bestCol == -1) {
        List<Integer> ms = getValidMoves();
        bestCol = ms.isEmpty() ? 0 : ms.get(0);
    }
    return bestCol;
}


/**
 * Prints the connect four game title.
 */
static void printTitle()
{
        clearConsole();
        System.out.println(BLUE_FG + """
                                                                                    
_____ _____ _____ _____ _____ _____ _____    _____ _____ _____ _____ 
|     |     |   | |   | |   __|     |_   _|  |   __|     |  |  | __  |
|   --|  |  | | | | | | |   __|   --| | |    |   __|  |  |  |  |    -|
|_____|_____|_|___|_|___|_____|_____| |_|    |__|  |_____|_____|__|__|
                                                                    
            """);
}

/**
 * Lets the player pick a size of the board.
 * @param sc The input that the user enters.
 * @return A matrix of the chosen size.
 */
static int[] pickSize(Scanner sc) 
{
    while (true) {
        System.out.println("Choose board size:");
        System.out.println("  1) 5 x 4");
        System.out.println("  2) 6 x 5");
        System.out.println("  3) 7 x 6");
        System.out.print("Enter 1-3: ");
        String in = sc.nextLine().trim();
        if (in.equals("1")) 
            return new int[]{5, 4};
        else if (in.equals("2")) 
            return new int[]{6, 5};
        else if (in.equals("3")) 
            return new int[]{7, 6};
        System.out.println("Invalid choice. Try again.\n");
    }
}

/**
 * Lets the user choose the mode they want to play (single or multi player).
 * @param sc Gets the input from the user.
 * @return Returns the gamemode based on the users choice.
*/
static int pickMode(Scanner sc)
{
    while (true)
        {
        System.out.println("Choose game mode:");
        System.out.println("  1) Single-player (vs Computer, Minimax AI)");
        System.out.println("  2) Two players (local)");
        System.out.print("Enter 1 or 2: ");

        String in = sc.nextLine().trim();

        try {
        int choice = Integer.parseInt(in);
        if (choice == 1 || choice == 2)
            return choice;
        else
            System.out.println("Invalid choice. Try again.\n");
        } 
        catch (NumberFormatException e) {
            System.err.println("Please enter a number (1 or 2 only).\n");
        }
    }
}

/**
 *Prompts the user to choose whether to play first (Red) or second (Yellow).
 * @param sc Scanner to get the input.
 * @return Returns P1 if user chooses re and P2 if the user chooses yellow.
 */
static int pickHumanSide(Scanner sc) 
{
    while (true) {
        System.out.println("Do you want to play first or second?");
        System.out.println("  1) First (Red)");
        System.out.println("  2) Second (Yellow)");
        System.out.print("Enter 1 or 2: ");
        String in = sc.nextLine().trim();
        if (in.equals("1")) 
            return P1;
        if (in.equals("2")) 
            return P2;
        System.out.println("Invalid choice. Try again.\n");
    }
}

/**
 * Parses a column input string and converts it to a zero-based column index.
 * @param in Input string entered by the user.
 * @param cols The total number of columns on the board.
 * @return 0 based column index if the input is in the range.
 */
static Integer parseColumn(String in, int cols) 
{
    try {
        int c = Integer.parseInt(in);
        if (c < 1 || c > cols) {
            System.out.println("\nOut of range.");
            return null;
        }
        return c - 1; // zero-based
    } catch (NumberFormatException e) {
        System.out.println("\nPlease enter a number.");
        return null;
    }
}

/**
 * Pauses the game flow until the user presses enter.
 * @param sc Input for an empty line.
 */
static void pause(Scanner sc)
{
    System.out.print("Press Enter to continue...");
    sc.nextLine();
}

/**
 * Sleeps the current thread for the given number of milliseconds.
 * @param ms Miliseconds to sleep.
 */
static void sleep(long ms)
{
    try { 
        Thread.sleep(ms);
        } 
        catch (InterruptedException ignored) {}
}

/**
 * Returns the opposing player.
 * @param p Gets the current player.
 * @return Returns the opponent.
 */
static int opponent(int p) 
{
if (p == P1)
    return P2;
else
    return P1;
} 

/**
 * P1 is red P2 is yellow.
 * @param p Player P1 or P2.
 * @return Returns the R (red) or Y(yellow) based on the player.
 */
static String token(int p) 
{
if (p == P1)
    return "R";
else
    return "Y";
}

/**
 * For declaring the board.
 * @param r number of rows.
 * @param c number of columns.
 */
static void initBoard(int r, int c) 
{
    grid = new int[r][c];
    heights = new int[c];
}

/**
 * Checking if the move is in range of the grid.
 * @param col
 * @return
 */
static boolean isValidMove(int col) //checking if the move is valid 
{
    return col >= 0 && col < cols && heights[col] < rows;
}

/**
 * Places a piece on the board for the given player.
 * The piece will fall to the lowest empty row in the specified column.
 * @param col The colum where the player wants to drop the piece.
 * @param player The current player 
 * @return Returns the row index where the piece was placed.
 */
static int makeMove(int col, int player) 
{
    int row = rows - 1 - heights[col];
    grid[row][col] = player;
    heights[col]++;
    return row;
}


/**
 * Checks whether the board is completely full.
 * @return True if no more moves can be made.
 */
static boolean isFull() 
{
    for (int c = 0; c < cols; c++) 
        if (heights[c] < rows)
                return false;
    return true;
}

/**
 * Computes and returns the list of all valid columns the next move can be played in.
 * A list of 0-indexed columns that arent full.
 */
static List<Integer> getValidMoves()
{
    List<Integer> moves = new ArrayList<>();
    for (int c = 0; c < cols; c++)
        if (isValidMove(c)) 
            moves.add(c);
    return moves;
}

/**
 * Checks if current player has connected four tokens.
 * @param player Gets the current player.
 * @return True if there exist a winning move for this player.
 */
static boolean isWinningMove(int player) 
{
    
    for (int r = 0; r < rows; r++)
        {
        for (int c = 0; c < cols; c++) 
        {
            if (grid[r][c] != player)
                continue;
            // horizontal
            if (c + 3 < cols && grid[r][c+1] == player && grid[r][c+2] == player && grid[r][c+3] == player) 
                return true;
            // vertical
            if (r + 3 < rows && grid[r+1][c] == player && grid[r+2][c] == player && grid[r+3][c] == player) 
                return true;
            // diagonal bottom
            if (r + 3 < rows && c + 3 < cols && grid[r+1][c+1] == player && grid[r+2][c+2] == player && grid[r+3][c+3] == player) 
                return true;
            // diagonal top
            if (r - 3 >= 0 && c + 3 < cols && grid[r-1][c+1] == player && grid[r-2][c+2] == player && grid[r-3][c+3] == player) 
                return true;
        }
    }
    return false;
}

/**
 * Returns a colored single character(red or yellow) string representing a cell value.
 * @param v Cell value from the board (it can be empty, P1 or P2).
 * @return "R" for P1, "Y" for P2.
 */
static String pieceChar(int v)
    {
    if (v == P1) return RED_FG + "R" + RESET;    
    if (v == P2) return YELLOW_FG + "Y" + RESET; 
    return GRAY_FG + "·" + RESET;               
}

/**
 * Renders the current board as a colored ASCII.
 * @return Returns the board.
 */
static String render() 
{
    StringBuilder sb = new StringBuilder();

    // TOP LINE
    sb.append("   ").append("┌");
    for (int c = 0; c < cols; c++) {
        sb.append("───");
        sb.append(c == cols - 1 ? "┐" : "┬");
    }
    sb.append("\n");

    // LINE SPACES
    for (int r = 0; r < rows; r++) {
        sb.append("   ").append("│");
        for (int c = 0; c < cols; c++) {
            String p = pieceChar(grid[r][c]); // ● ya da ·
            sb.append(" ").append(p).append(" ");
            sb.append("│");
        }
        sb.append("\n");

        
        if (r != rows - 1) {
            sb.append("   ").append("├");
            for (int c = 0; c < cols; c++) {
                sb.append("───");
                sb.append(c == cols - 1 ? "┤" : "┼");
            }
            sb.append("\n");
        }
    }

    // BOTTOM LINE
    sb.append("   ").append("└");

    for (int c = 0; c < cols; c++) {
        sb.append("───");
        sb.append(c == cols - 1 ? "┘" : "┴");
    }
    sb.append("\n");

    //BLUE COLUMN NUMBERS 
    sb.append("    ");
    for (int c = 1; c <= cols; c++)
    {
        sb.append(BLUE_FG).append(String.format(" %2d ", c)).append(RESET);
    }
    sb.append("\n");

    return sb.toString();            
    }


}
