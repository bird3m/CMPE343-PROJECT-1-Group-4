import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*; //for scanner,lists, random
import java.util.regex.Pattern;


/**
 * CMPE343 interactive, ANSI-colored, animated CLI program
 * that bundles several mini-modules and a Connect Four game under one main menu.
 *
 * <h2>Overview</h2>
 * <ul>
 *   <li><b>Main Menu:</b> Animated welcome (typewriter + glow), optional NyanCat intro,
 *       and a cinematic <i>Credits</i> roll. Options:
 *       <ul>
 *         <li>A: Primary School</li>
 *         <li>B: Secondary School</li>
 *         <li>C: High School</li>
 *         <li>D: University (Connect Four)</li>
 *         <li>E: Terminate</li>
 *         <li>F: Credits</li>
 *       </ul>
 *   </li>
 *   <li><b>Primary School:</b>
 *     <ul>
 *       <li><b>Age &amp; Zodiac</b> — Validates date input; computes age (years, months, days)
 *           via {@code java.time} and returns Zodiac sign.</li>
 *       <li><b>Word Reversal Wonderland</b> — Recursively reverses words while preserving the
 *           positions of non-letter characters; robust tokenization and input handling.</li>
 *     </ul>
 *   </li>
 *   <li><b>Secondary School:</b>
 *     <ul>
 *       <li><b>Prime Numbers</b> — Generates primes up to {@code n} using
 *           Sieve of Eratosthenes, Sundaram, and Atkin (with basic OOM safety).</li>
 *       <li><b>Expression Evaluation</b> — Step-by-step integer evaluator with precedence;
 *           maps user ops {@code x} and {@code :} to {@code *} and {@code /} only when safe.</li>
 *     </ul>
 *   </li>
 *   <li><b>High School:</b>
 *     <ul>
 *       <li><b>Array Statistics</b> — Median, arithmetic mean, geometric mean, and recursive
 *           harmonic mean (with zero handling).</li>
 *       <li><b>Vector Distances</b> — Manhattan, Euclidean, and cosine similarity.</li>
 *     </ul>
 *   </li>
 *   <li><b>University — Connect Four:</b> Colored ASCII board, variable sizes
 *       (5×4, 6×5, 7×6); single-player vs Minimax AI (alpha-beta pruning) or local 2P;
 *       heuristic scoring and center-column preference.</li>
 * </ul>
 *
 * <h2>User Experience &amp; Terminal Notes</h2>
 * <ul>
 *   <li>ANSI escape codes are used for color, effects, and cursor visibility
 *       (see {@code BLUE_FG}, {@code RESET}, etc.). On Windows, ensure the terminal
 *       supports ANSI (e.g., Windows Terminal, recent PowerShell/CMD with VT enabled).</li>
 *   <li>{@link #clearConsole()} performs a simple ANSI-based clear; behavior can vary by terminal.</li>
 *   <li>Animations (Nyan Cat, banners, credits) temporarily hide the cursor and repaint frames.</li>
 * </ul>
 *
 * <h2>Input Robustness</h2>
 * <ul>
 *   <li>Menu input is line-based; numeric reads are guarded (e.g., {@code safeIntInput}).</li>
 *   <li>Large allocations catch {@link OutOfMemoryError} and fail gracefully with messages.</li>
 *   <li>Expression validator rejects unsafe characters and enforces simple grammar.</li>
 * </ul>
 *
 * <h2>Build &amp; Run</h2>
 * <pre>
 * javac main_menu.java
 * java main_menu
 * </pre>
 *
 * <h2>Generate Javadoc</h2>
 * <pre>
 * javadoc -private -d doc main_menu.java
 * </pre>
 * Open {@code doc/index.html}.
 *
 * <h2>Implementation Notes</h2>
 * <ul>
 *   <li>Allman brace style is used for readability.</li>
 *   <li>Time calculations rely on {@link java.time.LocalDate} and {@link java.time.temporal.ChronoUnit}.</li>
 *   <li>Connect Four AI depth adapts to board size for performance.</li>
 *   <li>Centering helpers ignore ANSI codes to keep visual alignment correct.</li>
 * </ul>
 *
 * <h2>Limitations</h2>
 * <ul>
 *   <li>ANSI rendering depends on terminal support; fallback is monochrome text.</li>
 *   <li>Expression evaluator works on integers; division is truncating.</li>
 * </ul>
 * @since   2025-10
 */
public class main_menu
{
    /**
     * Initializes the program and delegates to {@link #callMenu()} to render the animated welcome and display the main menu loop.
     * @param args command line arguments.
     */
	public static void main(String[] args) 
	{
		callMenu();
	}
	
	/**
     * A method for clearing the terminal.
     */
	public static void clearConsole() 
	{
    	 	System.out.print("\033[H\033[2J");
   	 		System.out.flush();
	}
	
    static boolean animationFlag = false; // so the animation plays only once
    /**
     * Prints the menu screen for the main program.
     * The user can select one of the options below.
     */
	public static void callMenu()
	{

		while(true)
		{
            if(!animationFlag)
			{
                playNyanCat();
                animationFlag = true;
                System.out.print("\033[?25h"); //show the cursor again
            }
            
            showWelcomeScreen();
    

			System.out.println("\nPlease select one of the options below: \n[A] Primary School");
			System.out.println("[B] Secondary School");
			System.out.println("[C] High School");
			System.out.println("[D] University");
			System.out.println("[E] Terminate");
            System.out.println("\n[F] !Credits!\n");

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
				clearConsole();
				secschoolMenu();
				break;
			}
			else if (str.equals("C"))
			{
				clearConsole();
                highschool();
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
            else if (str.equals("F"))
            {
                playCredits();
            }
			else
				System.err.println("\nInvalid input. Try again!!!!!!!");

            
			
			
		}	

        
	}

/**
 * The function creates a framed window where credit lines move upward,
 * simulating a cinematic credit roll effect. Each line can include ANSI
 * color codes for styling, and the animation speed can be adjusted with
 * the sleep duration between frames.
 
 * Clears the console before each frame to create motion.
 * Prints a fixed-size box where text scrolls line by line.
 * Uses padCenterAnsi() to center lines while ignoring color codes.
 */
static void playCredits() 
{
    // Hides the cursor.
    System.out.print("\033[?25l");


    clearConsole();

    String title = BLUE_FG +
            "   ╔══════════════════════════════════════════════╗\n" +
            "   ║                C  R  E  D  I  T  S           ║\n" +
            "   ╚══════════════════════════════════════════════╝\n" +
            RESET;


    final int W = 72;       // box width
    final int H = 22;       // box height
;

    String[] lines = new String[] {
            " ",
            "A CMPE343 Production",
            " ",
            "Directed by",
            BLUE_FG + "  BİRDEM ÜSTÜNDAĞ",
            " ",
            PINK_FG + "  NARGIZ HUSEYNOVA",
            " ",

            GREEN_FG + "  NUR SENA CANDAN",
            " ",
         
            YELLOW_FG + "  MAIMOONAH BAKR SHIHAB AL-MASHHADANI",
            " ",
            "Special Thanks",
           RED_FG + "  İLKTAN AR",
            " ",

            "The End"
    };

    // The top header stays still while the credits scroll upward below.
    // We are basically sliding the "lines" array through a visible window  that has H rows (terminal height). The offset moves from bottom to top.
    for (int offset = H; offset >= -lines.length; offset--)
     {
        clearConsole();
        System.out.print(title);

        // Draw the top line
        System.out.println("   " + "┌" + "─".repeat(W) + "┐");

        // For each visible row on screen
        for (int row = 0; row < H; row++) 
        {
            int idx = row - (H - offset); //Figuring out which line from our list should appear at this spot
            String text = (idx >= 0 && idx < lines.length) ? lines[idx] : "";


            //Center the text
            String centered = padCenterAnsi(text, W);
            System.out.println("   │" + centered + RESET + "│");
        }

        // Draw the bottom line
        System.out.println("   " + "└" + "─".repeat(W) + "┘");

    
        System.out.println(BLUE_FG + "\n   Press Enter to skip…" + RESET);

        try { Thread.sleep(150); } catch (InterruptedException ignored) {}

        //If the user presses enter, quit.
        try {
            if (System.in.available() > 0)
            {
                new Scanner(System.in).nextLine();
                break;
            }
        } catch (Exception ignored) {}
    }

    System.out.println("\n" + BLUE_FG + "   Credits finished. Press Enter to return to the main menu." + RESET);
    new Scanner(System.in).nextLine();

    //show the cursor again and return back to main menu
    System.out.print("\033[?25h");
    clearConsole();
    callMenu();
}

// Matches ANSI color/style escape sequences like "\u001B[31m" or "\u001B[1;33m".
// Basically, these are the weird invisible color codes the terminal uses.
// We detect them so we can remove or ignore them when measuring text width.
static final Pattern ANSI = Pattern.compile("\\u001B\\[[\\d;]*m");

/**  
 * Removes ANSI color codes from a string and returns the visible length.
 */
static int visibleLength(String s)
{
    if (s == null) return 0;
    return ANSI.matcher(s).replaceAll("").length();
}

/** *
 *Centers text in a fixed-width field, but ignores ANSI color codes when calculating padding so the alignment stays visually correct.
 * @param s The text we want to center.
 * @param width The total width (number) of characters of the area which we want to center the text.
 * @return The centered version of the input string.
*/
static String padCenterAnsi(String s, int width)
{
    if (s == null) s = "";
    int len = visibleLength(s);
    if (len >= width) //if its already too long, just return it.
        return s; 
    int total = width - len;
    int left  = total / 2;
    int right = total - left;
    return " ".repeat(left) + s + " ".repeat(right);
}



    /**
     * Plays the ASCII animation.
     */
static void playNyanCat() 
{
    System.out.print("\033[?25l");

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
 * Simpler animated welcome: types lines character-by-character with a soft glow.
 */
static void showWelcomeScreen()
{
    String[] lines = new String[]
    {
        "  _     _  _______  ___      _______  _______  __   __  _______    ___   ____   ",
        "| | _ | ||       ||   |    |       ||       ||  |_|  ||       |  |   | |    |  ",
        "| || || ||    ___||   |    |       ||   _   ||       ||    ___|  |___| |_    | ",
        "|       ||   |___ |   |    |       ||  | |  ||       ||   |___    ___    |   | ",
        "|       ||    ___||   |___ |      _||  |_|  ||       ||    ___|  |   |   |   | ",
        "|   _   ||   |___ |       ||     |_ |       || ||_|| ||   |___   |___|  _|   | ",
        "|__| |__||_______||_______||_______||_______||_|   |_||_______|        |____|  "
    };

    System.out.print("\033[?25l");
    clearConsole();
    System.out.println();

    for (String line : lines)
    {
        typeLine(BLUE_FG + line + RESET, 1); 
        System.out.println();
        try { Thread.sleep(8); } catch (InterruptedException ignored) {}
    }

    //
    for (int i = 0; i < 4; i++)
    {
        System.out.print("\033[2m");  // dim
        reprint(lines, BLUE_FG);
        try { Thread.sleep(35); } catch (InterruptedException ignored) {}
        System.out.print("\033[22m"); // normal
        reprint(lines, BLUE_FG);
        try { Thread.sleep(35); } catch (InterruptedException ignored) {}
    }

    System.out.println();
    System.out.println(padCenterAnsi(BLUE_FG + "Press Enter to continue..." + RESET, 84));
    new Scanner(System.in).nextLine();
    System.out.print("\033[?25h");
}

/**
 * Prints a single line character-by-character with a short delay between each character,
 * creating a typewriter-like animation effect.
 * <p>
 * Used inside {@link #showWelcomeScreenTypewriter()} to simulate the feeling of 
 * typing text in real time.
 *
 * @param s The string to print.
 * @param perCharDelayMs Delay (in milliseconds) between each character output.
 */
static void typeLine(String s, int perCharDelayMs)
{
    for (int i = 0; i < s.length(); i++)
    {
        System.out.print(s.charAt(i));
        try { Thread.sleep(perCharDelayMs); 
        } 
        catch (InterruptedException ignored) {}
    }
}

/**
 * Clears the console and reprints all banner lines with a specific color.
 * <p>
 * Used by {@link #showWelcomeScreenTypewriter()} during the glowing (fade in/out) phase
 * to refresh the entire ASCII banner with brightness changes.
 *
 * @param lines  The array of banner lines to print.
 * @param color  The ANSI color code to apply (e.g., {@code BLUE_FG}).
 */
static void reprint(String[] lines, String color)
{
    clearConsole();
    System.out.println();
    for (String line : lines)
    {
        System.out.println(color + line + RESET);
    }
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

    
    static Scanner scanner = new Scanner(System.in);
    static final LocalDate TODAY = LocalDate.now();
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
            
            clearConsole(); // The screen should be cleared after submenu selection

            try {
                printAnimatedAsciiArt();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }


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
                    clearConsole();
                    callMenu(); // Exit the primarySchoolMenu method and return to mainMenu
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
     * Prints animated and colorful ASCII art for Primary School menu
     */
    private static void printAnimatedAsciiArt() throws InterruptedException
    {
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
        "\033[1;91m", // red
        "\033[1;93m", // yellow
        "\033[1;92m", // green
        "\033[1;94m", // blue
        "\033[1;95m", // purple
        "\033[1;96m"  // light blue
    };

    for (int i = 0; i < 2; i++) 
    { 
        for (String color : colors) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(color + asciiArt + "\033[0m");
            Thread.sleep(80); 
        }
    }
}
    
    /**
     * Calculates age (year, month, day) and zodiac sign from the birth date.
     * Uses built-in date functions as requested by the user, overriding original project constraint.
     */
    public static void calculateAgeAndZodiac() {

         clearConsole();
        
        // Animasyonlu ve renkli ASCII Art
        try {
            printAnimatedZodiacArt();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        System.out.println("\n\033[1;95m" + "-------------------------------------" + "\033[0m");
        System.out.println("\033[1;96m" + "    AGE & ZODIAC MAGIC CALCULATOR " + "\033[0m");
        System.out.println("\033[1;95m" + "-------------------------------------" + "\033[0m");
        System.out.println("\033[1;93m" + "Today's Date: " + TODAY.getDayOfMonth() + "." + TODAY.getMonthValue() + "." + TODAY.getYear() + " " + "\033[0m");
        
        // 1. Get the birth date from the user (with error handling)
        int day = 0, month = 0, year = 0;
        
        // Loop until a valid date is obtained
        boolean dateValid = false;
        LocalDate birthDate = null;
        while (!dateValid) {
            day = getValidatedInteger("\n\033[1;36m" + "Enter your birth day (DD): " + "\033[0m");
            month = getValidatedInteger("\033[1;36m" + "Enter your birth month (MM): " + "\033[0m");
            year = getValidatedInteger("\033[1;36m" + "Enter your birth year (YYYY): " + "\033[0m");

            if (isValidInputDate(day, month, year)) {
                try {
                     birthDate = LocalDate.of(year, month, day);
                    if (isDateAfter(birthDate, TODAY)) {
                         //ERROR MESSAGE 1
                         System.out.println("\n\033[1;41m\033[1;97m" + "!!! ERROR: TIME TRAVELER DETECTED! !!!" + "\033[0m");
                         System.out.println("\033[1;91m" + "The birth date can't be in the future (after " + TODAY + ")! " + "\033[0m");
                    } else {
                        dateValid = true;
                    }
                } catch (Exception e) {
                    //ERROR MESSAGE 2
                    System.out.println("\n\033[1;41m\033[1;97m" + "!!! ERROR: INVALID DATE COMBINATION! !!!" + "\033[0m");
                    System.out.println("\033[1;91m" + "Oopsie! That date combination (" + day + "." + month + "." + year + ") doesn't exist in our magical calendar! " + "\033[0m");
                }
            } else {
                //ERROR MESSAGE 3
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
        
        System.out.println("\033[1;96m" + "    YOUR RESULTS:" + "\033[0m");

        System.out.println("\033[1;93m" + "    Your Age: " + years + " years, " + months + " months, " + days + " days "); 
        System.out.println("\033[1;94m" + "    Your Zodiac Sign: " + zodiac + " ");  
        
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
     * Compares if a date comes after than the other.
     * @param date1 First date to compare
     * @param date2 Second date to compare
     * @return true if date1 is after date2, false otherwise
     */
    public static boolean isDateAfter(LocalDate date1, LocalDate date2)
    {
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

//---------------------------------------------------SECOND SCHOOL MENU---------------------------------------------
/**
 * secondary school menu
 */
static void secschoolMenu() {
    Scanner sc = new Scanner(System.in);
    while (true) {
        clearConsole();
        System.out.println(BLUE_FG + "\nOption B Secondary School");
        System.out.println(BLUE_FG + "[A] Prime Numbers");
        System.out.println(BLUE_FG + "[B] Evaluation of Expression");
        System.out.println(BLUE_FG + "[C] Return to Main Menu");
        System.out.print(BLUE_FG + "Select a task: ");
        String opt = sc.nextLine().trim().toUpperCase();

        switch (opt) {
            case "A" -> primeNums();
            case "B" -> expressionEv();
            case "C" -> {
                clearConsole();
                callMenu();
                return;
            }
            default -> System.err.println("Wrong, try again");
        }
    }
}

// prime number task

/**
 * runs the Sieve of Eratosthenes, Sieve of Sundaram, and Sieve of
Atkin algorithms
 */
static void primeNums() 
{
    Scanner sc = new Scanner(System.in);
    int n = 0;

    while (true) {
        System.out.print("Enter an integer greater than or equal to 12: ");
        String input = sc.nextLine().trim();

        try {
            n = Integer.parseInt(input);
            if (n >= 12) break;
            System.err.println("Number must be >= 12. Try again.");
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format, please re-enter.");
        }
    }

    try {
        // Eratosthenes 
        long t1 = System.currentTimeMillis();
        List<Integer> e = sieveEratosthenes(n);
        long t2 = System.currentTimeMillis();

        // Sundaram 
        long t3 = System.currentTimeMillis();
        List<Integer> s = sieveSundaram(n);
        long t4 = System.currentTimeMillis();

        // Atkin 
        long t5 = System.currentTimeMillis();
        List<Integer> a = sieveAtkin(n);
        long t6 = System.currentTimeMillis();

        System.out.println("\n  Eratosthenes   (" + (t2 - t1) + " ms)");
        printPrimeSample(e);
        System.out.println("\n  Sundaram   (" + (t4 - t3) + " ms)");
        printPrimeSample(s);
        System.out.println("\n  Atkin   (" + (t6 - t5) + " ms)");
        printPrimeSample(a);

    } catch (OutOfMemoryError memErr) {
        System.err.println(RED_FG + "Memory overflow, the input too large for available memory." + RESET);
    } catch (Exception ex) {
        System.err.println(RED_FG + "Unexpected error: " + ex.getMessage());
    }

    System.out.print("\nPress enter to go back");
    sc.nextLine();
}

/**
 * Prints the first 3 and last 2 primes from a list.
 * @param primes this is a list of generated prime numbers
 */
static void printPrimeSample(List<Integer> primes) 
{
    if (primes.size() < 5) System.out.println(primes);
    else {
        System.out.println("First 3: " + primes.subList(0, 3));
        System.out.println("Last 2: " + primes.subList(primes.size() - 2, primes.size()));
    }
}

/**
 * Implements the Sieve of Eratosthenes algorithm.
 */
static List<Integer> sieveEratosthenes(int n) 
{
    try {
        boolean[] prime = new boolean[n + 1];
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;
        for (int i = 2; i * i <= n; i++)
            if (prime[i])
                for (int j = i * i; j <= n; j += i)
                    prime[j] = false;
        List<Integer> list = new ArrayList<>();
        for (int i = 2; i <= n; i++) if (prime[i]) list.add(i);
        return list;
    } catch (OutOfMemoryError e) {
        System.err.println(RED_FG + "Memory overflow inside Eratosthenes sieve." + RESET);
        return new ArrayList<>();
    }
}

/**
 * Implements the Sieve of Sundaram algorithm with overflow and memory safety.
 */
static List<Integer> sieveSundaram(int n)
 {
    if (n < 2) return new ArrayList<>();

    int limit = (n - 2) / 2;
    boolean[] marked;

    try {
        marked = new boolean[limit + 1];
    } catch (OutOfMemoryError e) {
        System.err.println( RED_FG + "Memory overflow inside Sundaram sieve. Try a smaller number." + RESET);
        return new ArrayList<>();
    }

    for (int i = 1; i <= limit; i++) {
        for (int j = i; ; j++) {
            long idx = (long) i + j + 2L * i * j;
            if (idx > limit) break;
            marked[(int) idx] = true;
        }
    }

    List<Integer> primes = new ArrayList<>();
    if (n > 2) primes.add(2);
    for (int i = 1; i <= limit; i++) {
        if (!marked[i]) primes.add(2 * i + 1);
    }
    return primes;
}

/**
 * Implements the Sieve of Atkin algorithm with safe memory handling.
 */
static List<Integer> sieveAtkin(int n) 
{
    try {
        boolean[] sieve = new boolean[n + 1];
        int sqrt = (int) Math.sqrt(n);
        for (int x = 1; x <= sqrt; x++)
         {
            for (int y = 1; y <= sqrt; y++)
            {
                int num = 4 * x * x + y * y;
                if (num <= n && (num % 12 == 1 || num % 12 == 5)) 
                    sieve[num] ^= true;
                num = 3 * x * x + y * y;
                if (num <= n && num % 12 == 7) 
                    sieve[num] ^= true;
                num = 3 * x * x - y * y;
                if (x > y && num <= n && num % 12 == 11) 
                    sieve[num] ^= true;
            }
        }
        for (int i = 5; i <= sqrt; i++)
            if (sieve[i])
                for (int j = i * i; j <= n; j += i * i)
                    sieve[j] = false;

        List<Integer> list = new ArrayList<>();
        if (n > 2) list.add(2);
        if (n > 3) list.add(3);
        for (int i = 5; i <= n; i++) if (sieve[i]) list.add(i);
        return list;
    } catch (OutOfMemoryError e) {
        System.err.println(RED_FG + "Memory overflow inside Atkin sieve. Try a smaller number." + RESET);
        return new ArrayList<>();
    }
}

// expression evaluation

/**
 * performs arithmetic expression evaluation.
 */
static void expressionEv() 
{
    Scanner sc = new Scanner(System.in);
    while (true) {
        System.out.print("Enter an expression or press q to quit: ");
        String expr = sc.nextLine().trim();
        if (expr.equalsIgnoreCase("q")) return;

        if (!isValidExpression(expr)) {
            System.err.println("Re-enter a valid expression. use +, -, x, :, parentheses, digits)\n");
            continue;
        }

       /* expr = expr.replace('x', '*').replace(':', '/');
        System.out.println("Expression evaluation:");

        try {
            recursiveEval(expr);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.print("\nPress enter to go back");
        sc.nextLine();
        return;*/

        String evalReady = mapUserOps(expr);

        System.out.println("Expression evaluation:");
        try
        {
            recursiveEval(evalReady);
        }
        catch (Exception e)
        {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.print("\nPress enter to go back");
        sc.nextLine();
        return;
    }
}

/**
 * Converts user-input operators 'x' and ':' into '*' and '/'
 * only when they appear between two operands or parentheses.
 */
private static String mapUserOps(String expr)
{
    String s = expr;

    // Replace 'x' between a digit or ')' and a digit or '(' → '*'
    s = s.replaceAll("(?<=(\\d|\\)))\\s*x\\s*(?=(\\d|\\())", "*");

    // Replace ':' between a digit or ')' and a digit or '(' → '/'
    s = s.replaceAll("(?<=(\\d|\\)))\\s*:\\s*(?=(\\d|\\())", "/");

    return s;
}


/**
 * Validates an arithmetic expression to ensure it only contains safe characters.
 */
static boolean isValidExpression(String expr)
{
    if (!expr.matches("[0-9\\.\\s\\+\\-\\(\\)x:]+"))
    {
        return false;
    }

    
    if (expr.indexOf('*') >= 0 || expr.indexOf('/') >= 0)
    {
        return false;
    }

    return true;
}

/**
 * evaluates a mathematical expression and prints each step.
 */
static void recursiveEval(String expr) throws Exception {
    expr = expr.replaceAll("\\s+", "");

    while (expr.contains("(")) {
        int close = expr.indexOf(')');
        int open = expr.lastIndexOf('(', close);
        String inside = expr.substring(open + 1, close);

        int val = evaluateFlat(inside, false);

        expr = expr.substring(0, open) + val + expr.substring(close + 1);

        System.out.println("= " + expr);
    }

    int result = evaluateFlat(expr, true);
    System.out.println("= " + result);
}

/**
 * Evaluates a flat (no parentheses) arithmetic expression step by step.
 * Every step is printed with an '=' prefix if printSteps is true.
 */
static int evaluateFlat(String expr, boolean printSteps) throws Exception
{
    if (!expr.matches("[-0-9+*/]+"))
    {
        throw new Exception("Unsafe or invalid expression.");
    }

    List<String> tokens = new ArrayList<>();
    StringBuilder num = new StringBuilder();

    for (int i = 0; i < expr.length(); i++)
    {
        char c = expr.charAt(i);
        if (Character.isDigit(c) || (c == '-' && (i == 0 || "+-*/".indexOf(expr.charAt(i - 1)) != -1)))
        {
            num.append(c);
        }
        else if ("+-*/".indexOf(c) != -1)
        {
            tokens.add(num.toString());
            tokens.add(String.valueOf(c));
            num.setLength(0);
        }
    }
    tokens.add(num.toString());

    // --- Multiply / Divide first ---
    for (int i = 0; i < tokens.size(); i++)
    {
        String t = tokens.get(i);
        if (t.equals("*") || t.equals("/"))
        {
            int left, right;

            try
            {
                left = Integer.parseInt(tokens.get(i - 1));
                right = Integer.parseInt(tokens.get(i + 1));
            }
            catch (NumberFormatException e)
            {
                throw new Exception("Invalid number near operator '" + t + "'");
            }

            if (t.equals("/") && right == 0)
            {
                throw new Exception("Division by zero.");
            }

            int val = t.equals("*") ? left * right : left / right;

            tokens.set(i - 1, Integer.toString(val));
            tokens.remove(i); // remove operator
            tokens.remove(i); // remove right operand
            i--;

            if (printSteps)
            {
                System.out.println("= " + String.join("", tokens));
            }
        }
    }

    // --- Add / Subtract ---
    int result;
    try
    {
        result = Integer.parseInt(tokens.get(0));
    }
    catch (NumberFormatException e)
    {
        throw new Exception("Invalid starting number.");
    }

    for (int i = 1; i < tokens.size(); i += 2)
    {
        String op = tokens.get(i);
        int val;
        try
        {
            val = Integer.parseInt(tokens.get(i + 1));
        }
        catch (NumberFormatException e)
        {
            throw new Exception("Invalid number after operator '" + op + "'");
        }

        result = op.equals("+") ? result + val : result - val;

        if (printSteps && i < tokens.size() - 2)
        {
            StringBuilder sb = new StringBuilder();
            sb.append(result);
            for (int j = i + 2; j < tokens.size(); j++)
            {
                sb.append(tokens.get(j));
            }
            System.out.println("= " + sb.toString());
        }
    }

    return result;

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
        static final String GREEN_FG  = "\u001B[38;5;82m";
        static final String PINK_FG   = "\u001B[38;5;213m";
        static final String WHITE_FG  = "\u001B[38;5;15m";


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
 * @return A list of 0-indexed columns that arent full.
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
//--------------------------------------------------------------------------------------------------------

//-----------------------------------------HIGH SCHOOL LEVEL----------------------------------------------

/** Main part of the program.
     * I used a while loop here so the menu can repeat.
     * This makes it easier for the user to try both parts without restarting the program. */
    public static void highschool() 
    {

        Scanner input = new Scanner(System.in);
        boolean runProgram = true;

        while (runProgram) {
            System.out.println("\n===== HIGH SCHOOL MENU =====");
            System.out.println("[1] Statistical Information about an Array");
            System.out.println("[2] Distance between Two Arrays");
            System.out.println("[3] Return to Main Menu");
            System.out.print("Select an option: ");

            int choice = safeIntInput(input);

            if (choice == 1){
                clearConsole();
                statisticalInfo();
            } 
            else if (choice == 2) {
                clearConsole();
                distanceBetweenArrays();
            } 
            else if (choice == 3) {
                clearConsole();
                callMenu();
            } 
            else {
                System.out.println("Invalid choice! Please try again.");
            }
        }

        System.out.println("Program finished. Returning to Main Menu...");
    }

    /** Custom method to safely read integers.
     * I added this because nextInt() throws an error if the user writes text.
     * This way, it just asks again until a valid number is entered. */
    public static int safeIntInput(Scanner input) {
        while (true) {
            try {
                return input.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Invalid input! Please enter a number: ");
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
                System.out.print("Invalid input! Please enter a valid number: ");
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
            System.out.print("Enter array size (positive integer): ");
            n = safeIntInput(input);
            if (n > 0) break;
            System.out.println("Size must be greater than 0!");
        }
        
        double[] arr;
        try
        {
            arr = new double[n];
        }
        catch (OutOfMemoryError e)
        {
            System.err.println("Error: Array too large! Please try a smaller size.");
            pause(); 
            return;  
        }

        System.out.println("Enter " + n + " elements:");

        for (int i = 0; i < n; i++) {
            arr[i] = safeDoubleInput(input);
        }

        Arrays.sort(arr); // sorting makes median easier

        double median = getMedian(arr);
        double arithmeticMean = getArithmeticMean(arr);
        double geometricMean = getGeometricMean(arr);
        double harmonicMean = getHarmonicMeanRecursive(arr, 0);

        System.out.println("\nSorted Array: " + Arrays.toString(arr));
        System.out.printf("Median: %.4f\n", median);
        System.out.printf("Arithmetic Mean: %.4f\n", arithmeticMean);
        System.out.printf("Geometric Mean: %.4f\n", geometricMean);
        System.out.printf("Harmonic Mean (recursive): %.4f\n", harmonicMean);

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
    public static double getGeometricMean(double[] arr)
    {
        double product = 1;
        for (double num : arr) {
            product *= num;
        }

        if (product < 0)
        {
            System.err.println("Geometric mean of a negative number cannot be calculated");
            return Double.NaN; //not a number
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
            System.out.println("Note: skipped a zero value here.");
            return getHarmonicMeanRecursive(arr, index + 1);
        }

        double sum = (1 / current) + getHarmonicMeanRecursive(arr, index + 1);

        if (index == 0) {
            int count = 0;
            for (double num : arr) 
                if (num != 0) 
                    count++;
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
            System.out.print("Enter array dimension (positive integer): ");
            dim = safeIntInput(input);
            if (dim > 0) break;
            System.out.println("Dimension must be greater than 0!");
        }

        int[] arr1;
        int[] arr2;

    try
    {   
        arr1 = new int[dim];
        arr2 = new int[dim];
    }
    catch (OutOfMemoryError e)
    {
        System.err.println("Error: Java heap space-dimension is too large for this machine. Please try a smaller value.");
        pause();
        return;
    }

        System.out.println("Enter elements for first array (0-9):");
        readValidatedArray(arr1, input);

        System.out.println("Enter elements for second array (0-9):");
        readValidatedArray(arr2, input);

        double manhattan = manhattanDistance(arr1, arr2);
        double euclidean = euclideanDistance(arr1, arr2);
        double cosine = cosineSimilarity(arr1, arr2);

        System.out.printf("\nManhattan Distance: %.4f\n", manhattan);
        System.out.printf("Euclidean Distance: %.4f\n", euclidean);
        System.out.printf("Cosine Similarity: %.4f\n", cosine);

        pause();
    }

    /** Reads each element carefully and only accepts values between 0 and 9.
     * If something else is written, it asks again. */
    public static void readValidatedArray(int[] arr, Scanner input) {
        for (int i = 0; i < arr.length; i++) {
            while (true) {
                System.out.print("Element " + (i + 1) + ": ");
                try {
                    int val = input.nextInt();
                    if (val >= 0 && val <= 9) {
                        arr[i] = val;
                        break;
                    } else {
                        System.out.println("Please enter a number between 0 and 9.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("That doesn't look like a number. Try again.");
                    input.nextLine();
                }
            }
        }
    }

    /** Manhattan distance = sum of |x - y|.
     * I chose this one because it’s simple and easy to understand visually. */
    public static double manhattanDistance(int[] a, int[] b) {
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += Math.abs(a[i] - b[i]);
        }
        return sum;
    }

    /** Euclidean distance = square root of sum of squared differences.
     * Basically how far two points are “straight-line” apart. */
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
            System.out.println(BLUE_FG + "One of the vectors is all zeros, so I just return 0.");
            return 0;
        }

        return dot / (Math.sqrt(normA) * Math.sqrt(normB));
    }

    /** Just a short pause so the user can read the results before menu appears again. */
    public static void pause() {
        Scanner input = new Scanner(System.in);
        System.out.print(BLUE_FG + "\nPress Enter to return...");
        input.nextLine();
    }

}
