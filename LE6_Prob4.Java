public class MainActivity extends AppCompatActivity {

    private GridLayout gridLayout;
    private Button[] cells = new Button[9]; // an array to hold the buttons in the grid

    private int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // horizontal winning positions
                                        {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // vertical winning positions
                                        {0, 4, 8}, {2, 4, 6}}; // diagonal winning positions

    private String currentPlayer = "X"; // start with X player
    private boolean gameOver = false; // start with game not over

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = findViewById(R.id.grid_layout);

        // loop through each cell in the grid
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            Button cell = (Button) gridLayout.getChildAt(i);
            cells[i] = cell; // add button to the array
            cell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!gameOver && cell.getText().toString().equals("")) { // only allow user to play if game is not over and cell is empty
                        cell.setText(currentPlayer); // mark the cell with the current player's symbol
                        checkForWinner(); // check for a winner
                        currentPlayer = (currentPlayer == "X") ? "0" : "X"; // switch to the other player
                    }
                }
            });
        }
    }

    private void checkForWinner() {
        // loop through each winning position
        for (int[] position : winningPositions) {
            String symbol = cells[position[0]].getText().toString();
            if (!symbol.equals("") &&
                symbol.equals(cells[position[1]].getText().toString()) &&
                symbol.equals(cells[position[2]].getText().toString())) {
                // we have a winner!
                Toast.makeText(MainActivity.this, symbol + " wins!", Toast.LENGTH_SHORT).show();
                gameOver = true; // set game over to true
                return; // exit the method
            }
        }
        // if all cells are filled and no winner is detected, declare a draw
        if (isGridFull()) {
            Toast.makeText(MainActivity.this, "It's a draw!", Toast.LENGTH_SHORT).show();
            gameOver = true; // set game over to true
        }
    }

    private boolean isGridFull() {
        // loop through each cell and check if it is empty
        for (Button cell : cells) {
            if (cell.getText().toString().equals("")) {
                return false; // cell is empty, grid is not full
            }
        }
        return true; // all cells are filled, grid is full
    }

    private void resetGame() {
        // loop through each cell and clear its text
        for (Button cell : cells) {
            cell.setText("");
        }
        currentPlayer = "X"; // start with X player again
        gameOver = false; // set game over to false
    }
}
