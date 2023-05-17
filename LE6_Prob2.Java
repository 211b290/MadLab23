public class MainActivity extends AppCompatActivity {

    private GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = findViewById(R.id.grid_layout);

        // loop through each cell in the grid
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            View cell = gridLayout.getChildAt(i);
            cell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String message;
                    if (v.getTag() == "X") {
                        message = "Cell " + v.getId() + " contains X";
                    } else if (v.getTag() == "0") {
                        message = "Cell " + v.getId() + " contains 0";
                    } else {
                        message = "Cell " + v.getId() + " is empty";
                    }
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
