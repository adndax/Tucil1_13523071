package models;

import java.util.Arrays;

public class Piece {
    // attributes
    private int row, col;
    private char[][] piece;

    // constructor
    public Piece(char[][] piece) {
        this.row = piece.length; 
        this.col = piece[0].length;
        for (int i = 1; i < this.row; i++) {
            if (piece[i].length > this.col) {
                this.col = piece[i].length;
            }
        }
        this.piece = new char[row][col];
        for (int i = 0; i < row; i++) {
            Arrays.fill(this.piece[i], '.'); 
            for (int j = 0; j < piece[i].length; j++) {
                this.piece[i][j] = piece[i][j]; 
            }
        }
    }

    // methods

    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
    
    public char[][] getPiece() {
        return piece;
    }
    
    // print the piece
    public void printPiece() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(piece[i][j] + " ");
            }
            System.out.println();
        }
    }

    // rotate the piece 90 degrees clockwise
    public void rotate() {
        int newRow = col;
        int newCol = row;
        char[][] rotated = new char[newRow][newCol];

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                rotated[c][newCol - 1 - r] = piece[r][c];  // Rotasi searah jarum jam
            }
        }

        this.piece = rotated;
        this.row = newRow;
        this.col = newCol;
    }

    // flip the piece horizontally
    public void flipHorizontally() {
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col / 2; c++) {
                char temp = piece[r][c];
                piece[r][c] = piece[r][col - 1 - c];
                piece[r][col - 1 - c] = temp;
            }
        }
    }

    // flip the piece vertically 
    public void flipVertically() {
        for (int r = 0; r < row / 2; r++) {
            char[] temp = piece[r];
            piece[r] = piece[row - 1 - r];
            piece[row - 1 - r] = temp;
        }
    }
}