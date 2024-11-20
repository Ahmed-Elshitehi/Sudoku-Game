package sudoku.sudoku_game;

public class GameBoard {
    private static final int Size = 9;
    private int SRN;
    private int removals;
    private int[][] solution;
    private int[][] initial;
    private int[][] player;

    public GameBoard(int removals) {
        this.removals = removals;
        this.SRN = (int) Math.sqrt(Size);
        solution = new int[Size][Size];
        initial = new int[Size][Size];
        player = new int[Size][Size];
        resetPlayer();
    }

    public void resetPlayer() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                player[row][col] = initial[row][col];
            }
        }
    }

    public int[][] getPlayer() {
        return player;
    }

    public int[][] getInitial() {
        return initial;
    }

    public void modifyPlayer(int val, int row, int col) {
        if (val >= 0 && val <= 9)
            player[row][col] = val;
        else
            System.out.println("Value passed to player falls out of range");
    }

    Boolean check() {
        for (int row = 0; row < Size; row++) {
            for (int col = 0; col < Size; col++) {
                if (player[row][col] != solution[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void newValues() {
        for (int row = 0; row < Size; row++) {
            for (int col = 0; col < Size; col++) {
                solution[row][col] = 0;
                initial[row][col] = 0;
                player[row][col] = 0;
            }
        }
        fillDiagonal();
        fillRemaining(0, SRN);
        removeKDigits();
        resetPlayer();
    }

    // Fill the diagonal SRN number of SRN x SRN matrices
    void fillDiagonal() {
        for (int i = 0; i < Size; i = i + SRN) {
            fillBox(i, i);
        }
    }

    // Returns false if given 3 x 3 block contains num.
    boolean unUsedInBox(int rowStart, int colStart, int num) {
        for (int i = 0; i < SRN; i++)
            for (int j = 0; j < SRN; j++)
                if (solution[rowStart + i][colStart + j] == num)
                    return false;

        return true;
    }

    // Fill a 3 x 3 matrix.
    void fillBox(int row, int col) {
        int num;
        for (int i = 0; i < SRN; i++) {
            for (int j = 0; j < SRN; j++) {
                do {
                    num = randomGenerator(Size);
                }
                while (!unUsedInBox(row, col, num));
                solution[row + i][col + j] = num;
            }
        }
    }

    // Random generator
    int randomGenerator(int num) {
        return (int) Math.floor((Math.random() * num + 1));
    }

    // Check if safe to put in cell
    boolean CheckIfSafe(int i, int j, int num) {
        return (unUsedInRow(i, num) &&
                unUsedInCol(j, num) &&
                unUsedInBox(i - i % SRN, j - j % SRN, num));
    }

    // check in the row for existence
    boolean unUsedInRow(int i, int num) {
        for (int j = 0; j < Size; j++)
            if (solution[i][j] == num)
                return false;
        return true;
    }

    // check in the row for existence
    boolean unUsedInCol(int j, int num) {
        for (int i = 0; i < Size; i++)
            if (solution[i][j] == num)
                return false;
        return true;
    }

    // A recursive function to fill remaining
    // matrix
    boolean fillRemaining(int i, int j) {
        if (j >= Size && i < Size - 1) {
            i = i + 1;
            j = 0;
        }
        if (i >= Size && j >= Size)
            return true;

        if (i < SRN) {
            if (j < SRN)
                j = SRN;
        } else if (i < Size - SRN) {
            if (j == (int) (i / SRN) * SRN)
                j = j + SRN;
        } else {
            if (j == Size - SRN) {
                i = i + 1;
                j = 0;
                if (i >= Size)
                    return true;
            }
        }

        for (int num = 1; num <= Size; num++) {
            if (CheckIfSafe(i, j, num)) {
                solution[i][j] = num;
                if (fillRemaining(i, j + 1))
                    return true;

                solution[i][j] = 0;
            }
        }
        return false;
    }

    // Remove the K no. of digits to
    // complete game
    public void removeKDigits() {
        for (int row = 0; row < Size; row++) {
            for (int col = 0; col < Size; col++) {
                initial[row][col] = solution[row][col];
            }
        }
        int count = removals;
        while (count != 0) {
            int cellId = randomGenerator(Size * Size) - 1;
            int i = (cellId / Size);
            int j = cellId % Size;
            if (j != 0)
                j = j - 1;
            Boolean can = (initial[i][j] != 0);
            for (int k = 0, c1 = 0, c2 = 0; k < Size; k++) {
                if (initial[i][k] == 0) c1++;
                if (initial[k][j] == 0) c2++;
                if (c1 >= 7 || c2 >= 7) {
                    can = false;
                }
            }

            if (can) {
                count--;
                initial[i][j] = 0;
            } else if (initial[i][Size - 1] != 0) {
                count--;
                initial[i][Size - 1] = 0;
            }
        }
    }
}
