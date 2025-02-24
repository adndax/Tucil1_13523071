package utils;

import java.io.*;
import java.util.*;
import models.*;

public class InputHandler {
    private Board board;
    private List<Piece> pieces;
    private String mode;

    public InputHandler(String filePath) throws IOException {
        readInputFromFile(filePath);
    }

    private void readInputFromFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            // read board dimensions and number of pieces
            String firstLine = readNonEmptyLine(reader);
            if (firstLine == null) throw new IllegalArgumentException("File kosong.");

            String[] parts = firstLine.trim().split(" ");
            if (parts.length != 3) throw new IllegalArgumentException("Format baris pertama harus 3 angka.");

            int N = Integer.parseInt(parts[0]);
            int M = Integer.parseInt(parts[1]);
            int P = Integer.parseInt(parts[2]);

            // read mode
            String modeLine = readNonEmptyLine(reader);
            if (modeLine == null) throw new IllegalArgumentException("File tidak memiliki mode permainan.");
            mode = modeLine.trim();

            if (mode.equals("DEFAULT")) {
                board = new Board(N, M);  // empty board
            } else if (mode.equals("CUSTOM")) {
                board = readCustomBoard(reader, N, M);  // custom filled board
            } else {
                throw new IllegalArgumentException("Mode permainan tidak valid: " + mode);
            }

            // read puzzle pieces
            pieces = readPieces(reader, P);

            // validate piece count
            if (pieces.size() != P) {
                throw new IllegalArgumentException("Jumlah puzzle piece tidak sesuai. Diharapkan " + P + ", ditemukan " + pieces.size());
            }

        } catch (IOException e) {
            throw new IllegalArgumentException("Gagal membaca file: " + e.getMessage());
        }
    }

    private Board readCustomBoard(BufferedReader reader, int rows, int cols) throws IOException {
        Board customBoard = new Board(rows, cols);
        for (int i = 0; i < rows; i++) {
            String line = readNonEmptyLine(reader);
            if (line.length() != cols) {
                throw new IllegalArgumentException("Panjang baris ke-" + (i + 1) + " tidak sesuai dengan lebar papan.");
            }
            customBoard.setRow(i, line); 
        }
        return customBoard;
    }

    private List<Piece> readPieces(BufferedReader reader, int totalPieces) throws IOException {
        List<Piece> pieceList = new ArrayList<>();
        List<String> pieceLines = new ArrayList<>();
        char currentLetter = '\0';
        String line;

        while ((line = reader.readLine()) != null) {
            line = line.replace(' ', '.').trim();
            if (line.isEmpty()) continue;

            char firstChar = line.replace(".", "").isEmpty() ? '.' : line.charAt(line.indexOf(line.replace(".", "").charAt(0)));

            if (firstChar != currentLetter && !pieceLines.isEmpty()) {
                pieceList.add(convertToPiece(pieceLines, currentLetter));
                pieceLines.clear();
            }

            currentLetter = firstChar;
            pieceLines.add(line);
        }

        // save the last piece
        if (!pieceLines.isEmpty()) {
            pieceList.add(convertToPiece(pieceLines, currentLetter));
        }

        return pieceList;
    }

    private Piece convertToPiece(List<String> pieceLines, char letter) {
        int rows = pieceLines.size();
        int cols = pieceLines.stream().mapToInt(String::length).max().orElse(0);
        char[][] shape = new char[rows][cols];
    
        for (int i = 0; i < rows; i++) {
            String line = pieceLines.get(i);
            for (int j = 0; j < cols; j++) {
                shape[i][j] = (j < line.length() && line.charAt(j) == letter) ? letter : '.'; 
            }
        }
        return new Piece(shape);
    }

    private String readNonEmptyLine(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (!line.isEmpty()) return line;
        }
        return null;
    }

    public Board getBoard() { 
        return board; 
    }

    public List<Piece> getPieces() { 
        return pieces; 
    }

    public String getMode() { 
        return mode; 
    }
}