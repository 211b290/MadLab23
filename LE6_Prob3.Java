public class MainActivity extends AppCompatActivity {

    private GridLayout gridLayout;
    private Button[] cells = new Button[9]; // an array to hold the buttons in the grid

    private int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // horizontal winning positions
                                        {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // vertical winning positions
                                        {0, 4, 8}, {2, 4, 6}}; // diagonal winning positions

    private String currentPlayer = "X"; // start with X player

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
                    if (cell.getText().toString().equals("")) { // if cell is empty
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
                resetGame(); // reset the game
                return; // exit the method
            }
        }
    }

    private void resetGame() {
        // loop through each cell and clear its text
        for (Button cell : cells) {
            cell.setText("");
        }
        currentPlayer = "X"; // start with X player again
    }
}
