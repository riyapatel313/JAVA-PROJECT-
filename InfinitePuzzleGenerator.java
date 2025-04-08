import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class InfinitePuzzleGenerator extends JFrame {
    private static final int PUZZLE_SIZE = 3; // Size of the puzzle grid
    private static final int TILE_SIZE = 100; // Size of each puzzle tile

    private JPanel puzzlePanel;
    private JButton[][] tiles;
    private int emptyTileRow, emptyTileCol;

    public InfinitePuzzleGenerator() {
        setTitle("Infinite Puzzle Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(PUZZLE_SIZE * TILE_SIZE + 200, PUZZLE_SIZE * TILE_SIZE + 100);
        setLocationRelativeTo(null);

        // Create the puzzle panel
        puzzlePanel = new JPanel(new GridLayout(PUZZLE_SIZE, PUZZLE_SIZE));
        puzzlePanel.setBounds(100, 50, PUZZLE_SIZE * TILE_SIZE, PUZZLE_SIZE * TILE_SIZE);
        add(puzzlePanel, BorderLayout.CENTER);

        // Create the puzzle tiles
        tiles = new JButton[PUZZLE_SIZE][PUZZLE_SIZE];
        for (int i = 0; i < PUZZLE_SIZE; i++) {
            for (int j = 0; j < PUZZLE_SIZE; j++) {
                tiles[i][j] = new JButton(String.valueOf(i * PUZZLE_SIZE + j + 1));
                tiles[i][j].setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
                tiles[i][j].addActionListener(e -> swapTiles((JButton) e.getSource()));
                puzzlePanel.add(tiles[i][j]);
            }
        }

        // Set the empty tile
        emptyTileRow = PUZZLE_SIZE - 1;
        emptyTileCol = PUZZLE_SIZE - 1;
        tiles[emptyTileRow][emptyTileCol].setText("");

        // Shuffle the puzzle
        shufflePuzzle();

        setVisible(true);
    }

    private void swapTiles(JButton clickedTile) {
        for (int i = 0; i < PUZZLE_SIZE; i++) {
            for (int j = 0; j < PUZZLE_SIZE; j++) {
                if (tiles[i][j] == clickedTile) {
                    if (Math.abs(i - emptyTileRow) + Math.abs(j - emptyTileCol) == 1) {
                        // Swap the tiles
                        tiles[emptyTileRow][emptyTileCol].setText(tiles[i][j].getText());
                        tiles[i][j].setText("");
                        emptyTileRow = i;
                        emptyTileCol = j;
                        return;
                    }
                }
            }
        }
    }

    private void shufflePuzzle() {
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int row = random.nextInt(PUZZLE_SIZE);
            int col = random.nextInt(PUZZLE_SIZE);
            if (Math.abs(row - emptyTileRow) + Math.abs(col - emptyTileCol) == 1) {
                swapTiles(tiles[row][col]);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InfinitePuzzleGenerator::new);
    }
}