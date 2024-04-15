import java.util.Scanner;

public class SafeInput {
    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a String response that is not zero length
     */
    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String retString = ""; // Set this to zero length. Loop runs until it isn’t
        do {
            System.out.print("\n" + prompt + ": "); // show prompt add space
            retString = pipe.nextLine();
        } while (retString.length() == 0);

        return retString;

    }
    /**
     * Gets an unranged int value from the user
     *
     * @param pipe - a Scanner set on System.in to read from the console
     * @param prompt - msg to tell the user what to input
     * @return - an int provided by the user
     */
    public static int getInt(Scanner pipe, String prompt) {
        int ret = 0;
        boolean done = false;
        String trash;

        do {
            System.out.print(prompt + ": ");

            if (pipe.hasNextInt()) {
                ret = pipe.nextInt();
                pipe.nextLine();
                done = true;

            }
            else {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid number not " + trash);
            }

        } while (!done);

        return ret;
    }
    /**
     * Gets an ranged int value from the user
     *
     * @param pipe   - a Scanner set on System.in to read from the console
     * @param prompt - msg to tell the user what to input
     * @param low - bottom inclusive range value
     * @param high - top inclusive range value
     * @return - an int provided by the user
     */
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int ret = 0;
        boolean done = false;
        String trash;

        do {
            System.out.println(prompt + "[" + low + " - " + high + "]: ");

            if (pipe.hasNextInt()) {
                ret = pipe.nextInt();
                pipe.nextLine();
                if (ret >= low && ret <= high) {
                    done = true;
                } else {
                    System.out.println("You must enter a value between " + low + "and" + high + "not" + ret);
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid number not " + trash);
            }

        } while (!done);

        return ret;
    }
    /**
     * Gets an unranged double value from the user
     *
     * @param pipe - a Scanner set on System.in to read from the console
     * @param prompt - msg to tell the user what to input
     * @return - a double provided by the user
     */
    public static double getDouble(Scanner pipe, String prompt) {
        double ret = 0;
        boolean done = false;
        String trash;

        do {
            System.out.print(prompt + ": ");

            if (pipe.hasNextDouble()) {
                ret = pipe.nextDouble();
                pipe.nextLine();
                done = true;

            }
            else {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid number not " + trash);
            }

        } while (!done);

        return ret;
    }
    /**
     * Gets a ranged double value from the user
     *
     * @param pipe   - a Scanner set on System.in to read from the console
     * @param prompt - msg to tell the user what to input
     * @param low - bottom inclusive range value
     * @param high - top inclusive range value
     * @return - an double provided by the user
     */
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        double ret = 0;
        boolean done = false;
        String trash;

        do {
            System.out.println(prompt + "[" + low + " - " + high + "]: ");

            if (pipe.hasNextDouble()) {
                ret = pipe.nextDouble();
                pipe.nextLine();
                if (ret >= low && ret <= high) {
                    done = true;
                } else {
                    System.out.println("You must enter a value between " + low + "and" + high + "not" + ret);
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid number not " + trash);
            }

        } while (!done);

        return ret;
    }
    /**
     * Get a [Y/N] confirmation from the user
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return - true for yes and false for no
     */
    public static Boolean getYNConfirm (Scanner pipe, String prompt) {
        boolean done = false;
        boolean ret = true;
        String response;
        do {

            System.out.print("\n" + prompt +  "[Y/N]: ");
            response = pipe.nextLine();
            if (response.equalsIgnoreCase("Y")) {
                done = true;
                ret = true;
            }
            else if (response.equalsIgnoreCase("N")) {
                done = true;
                ret = false;
            }
            else {
                System.out.println("You must answer [Y/N]! " + response);
            }

        } while (!done);

        return ret;
    }
    /**
     * Get a string that matches a RegEx
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @param regEx - java style RegEx pattern to constrain the input
     * @return - a string that matches the RegEx pattern supplied
     */
    public static String getRegExString(Scanner pipe, String prompt, String regEx) {
        String response = "";
        boolean gotAVal = false;
        do {
            System.out.print("\n" + prompt + ": ");
            response = pipe.nextLine();
            if (response.matches(regEx)) {
                gotAVal = true;
            } else {
                System.out.println("\n" + response + " does not match the pattern");
                System.out.println("Try Again!");
            }
        } while (!gotAVal);

        return response;
    }

}
