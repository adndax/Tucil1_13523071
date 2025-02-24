package models;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Piece {
    // attributes
    private int row, col;
    private char[][] piece;

    // constructor
    public Piece(char[][] shape) {
        this.row = shape.length;
        this.col = shape[0].length;
        this.piece = new char[row][col];
        for (int i = 0; i < row; i++) {
            System.arraycopy(shape[i], 0, this.piece[i], 0, col);
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
    public void flip() {
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col / 2; c++) {
                char temp = piece[r][c];
                piece[r][c] = piece[r][col - 1 - c];
                piece[r][col - 1 - c] = temp;
            }
        }
    }

    // copy piece
    public char[][] copyShape() {
        char[][] copy = new char[row][col];
        for (int i = 0; i < row; i++) {
            System.arraycopy(piece[i], 0, copy[i], 0, col);
        }
        return copy;
    }

    // set piece copied
    public void setPiece(char[][] newShape) {
        this.piece = newShape;
        this.row = newShape.length;
        this.col = newShape[0].length;
    }

    // 4 rotation and 2 flip
    public Set<Piece> getUniqueOrientations() {
        Set<String> seen = new HashSet<>();
        Set<Piece> orientations = new HashSet<>();
        char[][] original = copyShape();
    
        for (int flipCount = 0; flipCount < 2; flipCount++) {
            for (int rotationCount = 0; rotationCount < 4; rotationCount++) {
                String serialized = serializeShape();
                if (seen.add(serialized)) { 
                    orientations.add(new Piece(copyShape()));
                }
                rotate();
            }
            flip();
            setPiece(original); 
        }
    
        return orientations;
    }

    private String serializeShape() {
        StringBuilder sb = new StringBuilder();
        for (char[] row : piece) {
            for (char c : row) {
                sb.append(c);
            }
            sb.append(';');
        }
        return sb.toString();
    }
}