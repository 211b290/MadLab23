public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createDbButton = findViewById(R.id.create_db_button);
        Button insertDataButton = findViewById(R.id.insert_data_button);
        Button deleteDataButton = findViewById(R.id.delete_data_button);

        createDbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDatabase();
            }
        });

        insertDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });

        deleteDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData();
            }
        });
    }

    private void createDatabase() {
        mDatabase = openOrCreateDatabase("my_database", MODE_PRIVATE, null);

        String createTableQuery = "CREATE TABLE IF NOT EXISTS users " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "email TEXT);";

        mDatabase.execSQL(createTableQuery);
    }

    private void insertData() {
        ContentValues values = new ContentValues();
        values.put("name", "John Doe");
        values.put("email", "johndoe@example.com");

        long rowId = mDatabase.insert("users", null, values);
        if (rowId != -1) {
            Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteData() {
        int deletedRows = mDatabase.delete("users", null, null);
        if (deletedRows > 0) {
            Toast.makeText(this, "Data deleted successfully", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        if (mDatabase != null) {
            mDatabase.close();
        }
        super.onDestroy();
    }
}
