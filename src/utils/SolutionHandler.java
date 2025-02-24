package utils;

import models.Board;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

public class SolutionHandler {

    private static final int CELL_SIZE = 50;  
    private static final int PADDING = 10;    

    public static void displaySolution(Board board, long time, int iterations) {
        board.printBoard();
        System.out.println("Waktu pencarian: " + time + " ms");
        System.out.println("Banyak kasus yang ditinjau: " + iterations);
    }

    public static void saveSolution(Board board, long time, int iterations, String inputFilename) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Apakah Anda ingin menyimpan solusi? (ya/tidak): ");
        String response = scanner.nextLine().trim().toLowerCase();

        if (!response.equals("ya")) {
            System.out.println("Solusi tidak disimpan.");
            return;
        }

        String baseFilename = inputFilename.replace(".txt", "_solution");
        saveTextSolution(board, time, iterations, "test/" + baseFilename + ".txt");
        saveImageSolution(board, "test/" + baseFilename + ".png");
    }

    private static void saveTextSolution(Board board, long time, int iterations, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            char[][] boardArray = board.getBoard();

            for (char[] row : boardArray) {
                for (char cell : row) {
                    writer.write(cell);
                }
                writer.newLine();
            }

            writer.newLine();
            writer.write("Waktu pencarian: " + time + " ms\n");
            writer.write("Banyak kasus yang ditinjau: " + iterations + "\n");

            System.out.println("Solusi berhasil disimpan di: " + filename);
        } catch (IOException e) {
            System.err.println("Gagal menyimpan solusi ke file: " + e.getMessage());
        }
    }

    private static void saveImageSolution(Board board, String filename) {
        int width = board.getWidth();
        int height = board.getHeight();
        int imageWidth = width * CELL_SIZE + PADDING * 2;
        int imageHeight = height * CELL_SIZE + PADDING * 2;

        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, imageWidth, imageHeight);

        char[][] boardArray = board.getBoard();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                char cell = boardArray[i][j];
                if (cell != '.' && cell != '#') {
                    g.setColor(Board.getColorMap().getOrDefault(cell, Color.BLACK));
                    int x = PADDING + j * CELL_SIZE;
                    int y = PADDING + i * CELL_SIZE;
                    g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                }
            }
        }

        g.dispose();

        try {
            File outputfile = new File(filename);
            ImageIO.write(image, "png", outputfile);
            System.out.println("Solusi disimpan sebagai gambar di: " + filename);
        } catch (IOException e) {
            System.err.println("Gagal menyimpan gambar: " + e.getMessage());
        }
    }
}