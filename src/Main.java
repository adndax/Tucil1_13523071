import models.*;
import utils.*;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nSelamat datang di Program IQ Puzzler Pro\n");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nama file input dengan format .txt: ");
        String fileName = scanner.nextLine().trim();

        String inputFile = "test/" + fileName;

        try {
            // read input file
            InputHandler inputHandler = new InputHandler(inputFile);
            Board board = inputHandler.getBoard();
            List<Piece> pieces = inputHandler.getPieces();
            String mode = inputHandler.getMode();

            System.out.println("\nMode permainan: " + mode);
            System.out.println("Dimensi papan: " + board.getHeight() + " x " + board.getWidth());
            System.out.println("Jumlah blok: " + pieces.size());
            System.out.println("\nMemulai pencarian solusi...\n");

            // solve the puzzle
            long startTime = System.currentTimeMillis();
            int[] iterations = {0};
            boolean solutionFound = Solver.solve(board, pieces, 0, iterations);
            long endTime = System.currentTimeMillis();
            long searchTime = endTime - startTime;

            if (solutionFound) {
                System.out.println("\nSolusi ditemukan!\n");
                SolutionHandler.displaySolution(board, searchTime, iterations[0]);
                SolutionHandler.saveSolution(board, searchTime, iterations[0], fileName);
            } else {
                System.out.println("\nSolusi tidak ditemukan.");
                System.out.println("Waktu pencarian: " + searchTime + "ms");
                System.out.println("Banyak kasus yang ditinjau: " + iterations[0]);
            }

        } catch (IOException e) {
            System.err.println("Terjadi kesalahan saat membaca file: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error input: " + e.getMessage());
        }
    }
}