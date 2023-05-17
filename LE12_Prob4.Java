public class DownloadNewsTask extends AsyncTask<Void, Void, List<NewsItem>> {

    private Context mContext;

    public DownloadNewsTask(Context context) {
        mContext = context;
    }

    @Override
    protected List<NewsItem> doInBackground(Void... voids) {
        List<NewsItem> newsItemList = new ArrayList<>();

        try {
            URL url = new URL("https://hacker-news.firebaseio.com/v0/topstories.json");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            StringBuilder result = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            JSONArray jsonArray = new JSONArray(result.toString());

            for (int i = 0; i < jsonArray.length(); i++) {
                int newsItemId = jsonArray.getInt(i);
                String newsItemUrl = "https://hacker-news.firebaseio.com/v0/item/" + newsItemId + ".json";
                URL newsItemUrlObject = new URL(newsItemUrl);
                HttpURLConnection newsItemUrlConnection = (HttpURLConnection) newsItemUrlObject.openConnection();
                InputStream newsItemIn = new BufferedInputStream(newsItemUrlConnection.getInputStream());
                BufferedReader newsItemReader = new BufferedReader(new InputStreamReader(newsItemIn));

                String newsItemLine;
                StringBuilder newsItemResult = new StringBuilder();
                while ((newsItemLine = newsItemReader.readLine()) != null) {
                    newsItemResult.append(newsItemLine);
                }

                JSONObject newsItemJson = new JSONObject(newsItemResult.toString());

                String title = newsItemJson.getString("title");
                String url = newsItemJson.getString("url");

                NewsItem newsItem = new NewsItem(newsItemId, title, url);
                newsItemList.add(newsItem);
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return newsItemList;
    }

    @Override
    protected void onPostExecute(List<NewsItem> newsItemList) {
        super.onPostExecute(newsItemList);

        ContentResolver contentResolver = mContext.getContentResolver();
        for (NewsItem newsItem : newsItemList) {
            ContentValues values = new ContentValues();
            values.put(NewsContract.NewsEntry.COLUMN_NEWS_ID, newsItem.getId());
            values.put(NewsContract.NewsEntry.COLUMN_TITLE, newsItem.getTitle());
            values.put(NewsContract.NewsEntry.COLUMN_URL, newsItem.getUrl());
            contentResolver.insert(NewsContract.NewsEntry.CONTENT_URI, values);
        }
    }
}
public class NewsListActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor>, AdapterView.OnItemClickListener {

    private static final int NEWS_LOADER_ID = 1;

    private SimpleCursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        ListView newsListView = findViewById(R.id.news_list_view);

        String[] fromColumns = {NewsContract.NewsEntry.COLUMN_TITLE};
        int
