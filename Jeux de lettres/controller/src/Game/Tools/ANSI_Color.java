package Game.Tools;

/**
 * String used to color text in console
 */
public enum ANSI_Color {
    /**
     * Reset text color. Use: "normal text " + ANSI_Color.* + "colored text" + ANSI_Color.RESET + " normal text"
     */
    RESET("\u001B[0m"),
    /**
     * Colors
     */
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m"),
    GREY("\u001B[90m");

    /**
     * String of the color code.
     */
    private final String ANSI_CODE;

    /**
     * Constructor
     *
     * @param ANSI_CODE Code
     */
    ANSI_Color(String ANSI_CODE) {
        //this.ANSI_CODE = ANSI_CODE;
    	this.ANSI_CODE = "";
    }

    @Override
    public String toString() {
        return ANSI_CODE;
    }
}
