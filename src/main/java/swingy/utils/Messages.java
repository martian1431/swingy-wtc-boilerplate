package swingy.utils;

import static swingy.utils.Colors.*;
import static swingy.utils.Log.log;

//TODO: Rename class
public class Messages {
    public static void showWelcomeMessage() {
        log(logo());
        log(introduction());
        log(instructions());
    }

    public static String  logo() {
        return  ANSI_WHITE + "                                                                                    \n" + ANSI_RESET +
                BLUE_BRIGHT  + "      111011    1010        0111 1101 1100101    0110  100101111    1101     1010 \n" + ANSI_RESET +
                BLUE_BRIGHT  + "    0010  0111  0111        0001 0111 11101111   0010 1110    1001  1001    1111  \n" + ANSI_RESET +
                BLUE_BRIGHT  + "    1101        0011        1101 0100 0110 1001  0101 1011    1001   1011  1011   \n" + ANSI_RESET +
                BLUE_BRIGHT  + "      1101       1010 1101 0101  1010 0010  111110101 1111            10011011    \n" + ANSI_RESET +
                BLUE_BRIGHT  + "        1101      101011101011   1101 1000   11011111 1010  00111010    0110      \n" + ANSI_RESET +
                CYAN_BRIGHT  + "    0111  1011    1000   1100    1011 1010       0101 1110    1011      0010      \n" + ANSI_RESET +
                CYAN_BRIGHT  + "      010111      1011   1000    1110 1100       0110   10110111        0101 " + ANSI_WHITE + "v1.0\n" + ANSI_RESET +
                "\t\t" + CYAN_UNDERLINED + "Copyright " + Symbols.COPYRIGHT + " 2020 Wethinkcode_ Developed with " + Symbols.HEART + " by pmalope\n" + ANSI_RESET;
    }

    private static String introduction() {
        return CYAN_BOLD_BRIGHT + " Hi, Welcome!!!\n" + ANSI_RESET  +
                " This is an Implementation of a minimalistic text-based RPG game in Java\n\n" +
                ANSI_YELLOW + ":::" + ANSI_RESET + CYAN_BOLD_BRIGHT + "HOW TO PLAY" + ANSI_RESET + ANSI_YELLOW + ":::\n" + ANSI_RESET +
                "  * North: Move Up\n" +
                "  * East: Move Right\n" +
                "  * West: Move Left\n" +
                "  * South: Move Down\n";
    }

    private static String instructions() {
        return ANSI_YELLOW + ":::" + ANSI_RESET + CYAN_BOLD_BRIGHT + "HERE ARE THE RULES" + ANSI_RESET + ANSI_YELLOW + ":::\n" + ANSI_RESET +
                "  * You can have multiple heros of different types\n" +
                "  * Stats are affected by the hero's level and artifacts\n" +
                "  * If you reach the end of the border of the map you win\n" +
                "  * When you move to a position occupied by a villian you have two options; Run or Fight.\n" +
                "  * If you win the fight you will gain experience and an artifact which you can choose to keep or drop.\n" +
                "  * You will level up when you reach the next level experience\n" +
                "  * If you Lose you will die and start over.\n" +
                "  * To Advance to next Level You need to accumulate above 1000xp \n" +
                "  * The Map is based on your level\n";
    }

    public static String logoText = "\n\n\n\n\n\n\nSwingy 1.0 We Think Code Developed by pmalope 2020:"
            +  "\n\nA minimalistic text-based Role Play Game in Java Swing.";
}
