public class LanguageSelectionActivity extends AppCompatActivity {

    private TextView mSelectedLanguageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_selection);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSelectedLanguageTextView = findViewById(R.id.selected_language_text_view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_english:
                mSelectedLanguageTextView.setText("English");
                return true;
            case R.id.action_french:
                mSelectedLanguageTextView.setText("French");
                return true;
            case R.id.action_german:
                mSelectedLanguageTextView.setText("German");
                return true;
            case R.id.action_spanish:
                mSelectedLanguageTextView.setText("Spanish");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
