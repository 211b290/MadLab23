public class MainActivity extends AppCompatActivity {

    private TextView greetingTextView;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        greetingTextView = findViewById(R.id.greeting_text_view);

        // Get the SharedPreferences instance
        sharedPreferences = getPreferences(Context.MODE_PRIVATE);

        // Get the last selected language from SharedPreferences
        String selectedLanguage = sharedPreferences.getString("selectedLanguage", "");

        // Set the greeting text based on the last selected language
        if (selectedLanguage.equals("Spanish")) {
            greetingTextView.setText("Bienvenido al mundo de Android");
        } else if (selectedLanguage.equals("French")) {
            greetingTextView.setText("Bienvenue dans le monde d'Android");
        } else {
            greetingTextView.setText("Welcome to the world of Android");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle menu item selection
        switch (item.getItemId()) {
            case R.id.english:
                greetingTextView.setText("Welcome to the world of Android");
                // Save the selected language to SharedPreferences
                sharedPreferences.edit().putString("selectedLanguage", "English").apply();
                return true;
            case R.id.spanish:
                greetingTextView.setText("Bienvenido al mundo de Android");
                // Save the selected language to SharedPreferences
                sharedPreferences.edit().putString("selectedLanguage", "Spanish").apply();
                return true;
            case R.id.french:
                greetingTextView.setText("Bienvenue dans le monde d'Android");
                // Save the selected language to SharedPreferences
                sharedPreferences.edit().putString("selectedLanguage", "French").apply();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
