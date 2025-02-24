package models;

import java.util.List;
import java.util.Set;

public class Solver {

    public static boolean solve(Board board, List<Piece> pieces, int index, int[] iterations) {
        if (index == pieces.size()) {
            return board.isBoardFull(); 
        }

        Piece piece = pieces.get(index);
        Set<Piece> uniqueOrientations = piece.getUniqueOrientations();

        for (Piece orientation : uniqueOrientations) {
            for (int y = 0; y <= board.getHeight() - orientation.getRow(); y++) {
                for (int x = 0; x <= board.getWidth() - orientation.getCol(); x++) {
                    iterations[0]++;

                    if (board.canPlacePiece(orientation, x, y)) {
                        board.placePiece(orientation, x, y);

                        if (solve(board, pieces, index + 1, iterations)) {
                            return true;
                        }

                        board.removePiece(orientation, x, y); 
                    }
                }
            }
        }

        return false;
    }
}