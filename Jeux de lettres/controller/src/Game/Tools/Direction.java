package Game.Tools;

/**
 * Orientation, either horizontal or vertical.
 */
public enum Direction {
    VERTICAL, HORIZONTAL;

    /**
     * Used to simplify text syntax.
     *
     * @return True if horizontal, else otherwise.
     */
    public boolean isHorizontal() {
        return this == HORIZONTAL;
    }

    /**
     * Get the opposite direction.
     *
     * @return VERTICAL if horizontal, HORIZONTAL otherwise.
     */
    public Direction getOpposite() {
        if (this.isHorizontal()) {
            return VERTICAL;
        } else {
            return HORIZONTAL;
        }
    }
}
