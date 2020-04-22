public enum GameboardState {
    EMPTY ('0', " "),
    PLAYERBOAT ('1', "@"),
    COMPUTERBOAT ('2', " "),
    SUNKPLAYERBOAT ('3', "x"),
    SUNKCOMPUTERBOAT ('4', "!"),
    PLAYERMISSED ('5', "-")
    ;

    private final char arraySymbol;
    private final String printSymbol;

    GameboardState(char arraySymbol, String printSymbol) {
        this.arraySymbol = arraySymbol;
        this.printSymbol = printSymbol;
    }

    public char getValue() {
        return arraySymbol;
    }

    public static boolean isEnum(char value) {
        for (GameboardState state : GameboardState.values()) {
            if (state.getValue() == value) {
                return true;
            }
        }
        return false;
    }

    public String getEnumFromValue(char value) {
        if (isEnum(value)) {
            for (GameboardState state : GameboardState.values()) {
                if (state.getValue() == value) {
                    return name();
                }
            }
        }
        return value + " is not a valid GameBoardState";
    }

    @Override
    public String toString() {
        return printSymbol;
    }
}
