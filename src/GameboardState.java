public enum GameboardState {
    EMPTY (0, " "),
    PLAYERBOAT (1, "@"),
    COMPUTERBOAT (2, " "),
    SUNKPLAYERBOAT (3, "x"),
    SUNKCOMPUTERBOAT (4, "!"),
    PLAYERMISSED (5, "-")
    ;

    private final int arraySymbol;
    private final String printSymbol;

    GameboardState(int arraySymbol, String printSymbol) {
        this.arraySymbol = arraySymbol;
        this.printSymbol = printSymbol;
    }

    public int getValue() {
        return arraySymbol;
    }

    public static boolean isEnum(int value) {
        for (GameboardState state : GameboardState.values()) {
            if (state.getValue() == value) {
                return true;
            }
        }
        return false;
    }

    public static GameboardState getEnumFromValue(int value) {
        if (isEnum(value)) {
            for (GameboardState state : GameboardState.values()) {
                if (state.getValue() == value) {
                    return state;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return printSymbol;
    }
}
