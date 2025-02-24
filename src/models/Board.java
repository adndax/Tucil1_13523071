package models;

import java.awt.*;
import models.Board;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Board {
    // attributes
    private int height, width;
    private char[][] board;
    private static Map<Character, String> ansiColorMap = new HashMap<>();
    private static final Map<Character, Color> colorMap = new HashMap<>();

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
        Color[] colors = {
            new Color(255, 99, 132),  // Red
            new Color(54, 162, 235),  // Blue
            new Color(75, 192, 192),  // Teal
            new Color(255, 206, 86),  // Yellow
            new Color(153, 102, 255), // Purple
            new Color(255, 159, 64),  // Orange
            new Color(201, 203, 207), // Light Gray
            new Color(255, 0, 0),     // Bright Red
            new Color(0, 255, 0),     // Bright Green
            new Color(0, 0, 255),     // Bright Blue
            new Color(255, 255, 0),   // Bright Yellow
            new Color(0, 255, 255),   // Cyan
            new Color(255, 0, 255),   // Magenta
            new Color(128, 0, 128),   // Dark Purple
            new Color(255, 165, 0),   // Dark Orange
            new Color(46, 139, 87),   // Sea Green
            new Color(0, 206, 209),   // Turquoise
            new Color(139, 69, 19),   // Saddle Brown
            new Color(70, 130, 180),  // Steel Blue
            new Color(219, 112, 147), // Pale Violet Red
            new Color(240, 230, 140), // Khaki
            new Color(210, 105, 30),  // Chocolate
            new Color(154, 205, 50),  // Yellow Green
            new Color(233, 150, 122), // Dark Salmon
            new Color(72, 61, 139),   // Dark Slate Blue
            new Color(244, 164, 96)   // Sandy Brown
        };

        for (int i = 0; i < letters.length; i++) {
            colorMap.put(letters[i], colors[i % colors.length]);
        }
        for (int i = 0; i < letters.length; i++) {
            ansiColorMap.put(letters[i], pastelAnsiColors[i % pastelAnsiColors.length]);
        }
    }

    // methods

    //  custom mode
    public void setRow(int rowIndex, String line) {
        for (int j = 0; j < width; j++) {
            board[rowIndex][j] = (line.charAt(j) == 'X') ? '.' : '#';
        }
    }
    
    public static Map<Character, Color> getColorMap() {
        return colorMap;
    }

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
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == '.') { 
                    return false;
                }
            }
        }
        return true;
    }

    // check if the piece can fit the board
    public boolean canPlacePiece(Piece piece, int x, int y) {
        char[][] shape = piece.getPiece();
        for (int i = 0; i < piece.getRow(); i++) {
            for (int j = 0; j < piece.getCol(); j++) {
                if (shape[i][j] != '.') {  
                    if (y + i >= height || x + j >= width || board[y + i][x + j] != '.') {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // place the piece into the board if can
    public void placePiece(Piece piece, int x, int y) {
        char[][] shape = piece.getPiece();
        for (int i = 0; i < piece.getRow(); i++) {
            for (int j = 0; j < piece.getCol(); j++) {
                if (shape[i][j] != '.' && board[y + i][x + j] == '.') {  
                    board[y + i][x + j] = shape[i][j];
                }
            }
        }
    }

    public void removePiece(Piece piece, int x, int y) {
        char[][] shape = piece.getPiece();
        for (int i = 0; i < piece.getRow(); i++) {
            for (int j = 0; j < piece.getCol(); j++) {
                if (shape[i][j] != '.' && board[y + i][x + j] != '#') {  
                    board[y + i][x + j] = '.'; 
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