package models;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Board {
    // attributes
    private int height, width;
    private char[][] board;
    private static Map<Character, String> ansiColorMap = new HashMap<>();

    // constructor
    public Board(int N, int M) {
        this.height = N;
        this.width = M;
        this.board = new char[N][M];
        for (int i = 0; i < height; i++) {
            Arrays.fill(board[i], '.');
        }
    }

    // color mapping
    static {
        char[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        String[] pastelAnsiColors = {
            "\u001B[38;5;217m",
            "\u001B[38;5;151m",
            "\u001B[38;5;153m",
            "\u001B[38;5;222m",
            "\u001B[38;5;225m", 
            "\u001B[38;5;144m",  
            "\u001B[38;5;159m",
            "\u001B[38;5;186m", 
            "\u001B[38;5;182m", 
            "\u001B[38;5;176m",  
            "\u001B[38;5;114m",
            "\u001B[38;5;174m",
            "\u001B[38;5;181m",
            "\u001B[38;5;225m",
            "\u001B[38;5;157m",
            "\u001B[38;5;180m",
            "\u001B[38;5;178m",
            "\u001B[38;5;147m",
            "\u001B[38;5;219m",
            "\u001B[38;5;186m",
            "\u001B[38;5;216m",
            "\u001B[38;5;156m",
            "\u001B[38;5;138m",
            "\u001B[38;5;117m",
            "\u001B[38;5;224m",
            "\u001B[38;5;226m"
        };
        for (int i = 0; i < letters.length; i++) {
            ansiColorMap.put(letters[i], pastelAnsiColors[i % pastelAnsiColors.length]);
        }
    }

    // methods

    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }

    public char[][] getBoard() {
        return board;
    }
    
    // return true if the board is full, false if not
    public boolean isBoardFull() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if (board[i][j] == '.') {
                    return false;
                }
            }
        }
        return true;
    }

    // check if the piece can fit the board
    public boolean canPlacePiece(Piece piece, int x, int y) {
        if (x + piece.getCol() > width || y + piece.getRow() > height) {
            return false;
        }
        char[][] pieceArray = piece.getPiece();
        for (int i = 0; i < piece.getRow(); i++) {
            for (int j = 0; j < piece.getCol(); j++) {
                if (pieceArray[i][j] != '.' && board[y + i][x + j] != '.') {
                    return false;
                }
            }
        }
        return true;
    }

    // place the piece into the board if can
    public void placePiece(Piece piece, int x, int y) {
        char[][] pieceArray = piece.getPiece();
        if (canPlacePiece(piece, x, y)) {
            for (int i = 0; i < piece.getRow(); i++) {
                for (int j = 0; j < piece.getCol(); j++) {
                    if (pieceArray[i][j] != '.') {
                        board[y + i][x + j] = pieceArray[i][j];
                    }
                }
            }
        }
    }
     
    // board printing
    public void printBoard() {
        System.out.print("\u001B[0m"); 
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                char c = board[i][j];
                System.out.print(ansiColorMap.getOrDefault(c, "") + c + " ");
            }
            System.out.println(); 
        }
        System.out.print("\u001B[0m\n");
    }

}