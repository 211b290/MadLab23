public class MainActivity extends AppCompatActivity {

    private ImageView rocketImageView;
    private SeekBar seekBar;
    private TextView countdownTextView;
    private Button startButton;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rocketImageView = findViewById(R.id.rocket_image_view);
        seekBar = findViewById(R.id.seek_bar);
        countdownTextView = findViewById(R.id.countdown_text_view);
        startButton = findViewById(R.id.start_button);

        mediaPlayer = MediaPlayer.create(this, R.raw.siren_sound);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                timeLeftInMillis = (long) (progress * 1000);
                updateCountdownText();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // do nothing
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // do nothing
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });

        timeLeftInMillis = 60000; // set initial time to 1 minute
        updateCountdownText();
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountdownText();
            }

            @Override
            public void onFinish() {
                updateCountdownText();
                mediaPlayer.start();
            }
        }.start();

        startButton.setEnabled(false);
        seekBar.setEnabled(false);
    }

    private void updateCountdownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        countdownTextView.setText(timeLeftFormatted);

        if (timeLeftInMillis == 0) {
            startButton.setEnabled(true);
            seekBar.setEnabled(true);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}
