public class MainActivity extends AppCompatActivity {

  private static final String API_URL = "https://hacker-news.firebaseio.com/v0/topstories.json";
  private List<Story> storiesList;
  private ListView storiesListView;
  private ProgressBar progressBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    storiesList = new ArrayList<>();
    storiesListView = findViewById(R.id.stories_list);
    progressBar = findViewById(R.id.progress_bar);

    Button fetchButton = findViewById(R.id.fetch_button);
    fetchButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        fetchStories();
      }
    });
  }

  private void fetchStories() {
    progressBar.setVisibility(View.VISIBLE);

    // Instantiate the RequestQueue
    RequestQueue queue = Volley.newRequestQueue(this);

    // Request a JSON response from the provided URL
    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(API_URL,
      new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
          storiesList.clear();

          // Process the response and add the stories to the list
          for (int i = 0; i < response.length(); i++) {
            try {
              int itemId = response.getInt(i);
              String itemUrl = "https://hacker-news.firebaseio.com/v0/item/" + itemId + ".json";

              JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, itemUrl, null,
                new Response.Listener<JSONObject>() {
                  @Override
                  public void onResponse(JSONObject item) {
                    try {
                      String title = item.getString("title");
                      Story story = new Story(itemId, title);
                      storiesList.add(story);

                      // Update the list view
                      if (storiesList.size() == response.length()) {
                        updateListView();
                      }
                    } catch (JSONException e) {
                      e.printStackTrace();
                    }
                  }
                },
                new Response.ErrorListener() {
                  @Override
                  public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
