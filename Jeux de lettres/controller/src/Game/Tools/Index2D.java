package Game.Tools;

/**
 * Stored the position of something in a 2D array.
 */
public class Index2D {
    /**
     * Index of the line.
     */
    public final int LINE;
    /**
     * Index of the column.
     */
    public final int COLUMN;

    /**
     * Constructor
     *
     * @param row    Index of the line.
     * @param column Index of the column.
     */
    public Index2D(int row, int column) {
        this.LINE = row;
        this.COLUMN = column;
    }
}
