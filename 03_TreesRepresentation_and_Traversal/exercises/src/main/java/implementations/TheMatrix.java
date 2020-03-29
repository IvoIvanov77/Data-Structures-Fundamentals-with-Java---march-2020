package implementations;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TheMatrix {
    private char[][] matrix;
    private char fillChar;
    private char startChar;
    private int startRow;
    private int startCol;

    public TheMatrix(char[][] matrix, char fillChar, int startRow, int startCol) {
        this.matrix = matrix;
        this.fillChar = fillChar;
        this.startRow = startRow;
        this.startCol = startCol;
        this.startChar = this.matrix[this.startRow][this.startCol];
    }

    public void solve() {
        process(startRow, startCol);
    }

    private void process(int row, int col) {
        matrix[row][col] = fillChar;
        if(canProcess(row + 1, col)) {
            process(row + 1, col);
        }
        if(canProcess(row , col + 1)) {
            process(row, col + 1);
        }
        if(canProcess(row -1, col)) {
            process(row - 1, col);
        }
        if(canProcess(row, col -1)) {
            process(row, col - 1);
        }
    }

    public String toOutputString() {
        return Arrays.stream(matrix)
                .map(String::valueOf)
                .collect(Collectors.joining("\r\n"));
    }

    private boolean canProcess(int row, int col) {
        return row >= 0 && row < matrix.length &&
                col >= 0 && col < matrix[row].length &&
                matrix[row][col] == startChar;
    }
}
