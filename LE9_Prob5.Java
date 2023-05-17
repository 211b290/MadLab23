import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView facultyImageView;
    private TextView scoreTextView;
    private Button optionButton1;
    private Button optionButton2;
    private Button optionButton3;
    private Button optionButton4;

    private List<Faculty> facultyList;
    private int score;
    private Faculty currentFaculty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        facultyImageView = findViewById(R.id.facultyImageView);
        scoreTextView = findViewById(R.id.scoreTextView);
        optionButton1 = findViewById(R.id.optionButton1);
        optionButton2 = findViewById(R.id.optionButton2);
        optionButton3 = findViewById(R.id.optionButton3);
        optionButton4 = findViewById(R.id.optionButton4);

        // Initialize the faculty list
        facultyList = new ArrayList<>();
        facultyList.add(new Faculty(R.drawable.faculty1, "Faculty 1"));
        facultyList.add(new Faculty(R.drawable.faculty2, "Faculty 2"));
        facultyList.add(new Faculty(R.drawable.faculty3, "Faculty 3"));
        facultyList.add(new Faculty(R.drawable.faculty4, "Faculty 4"));

        // Set up the initial state of the game
        resetGame();
    }

    private void resetGame() {
        // Set the score to zero
        score = 0;
        updateScoreTextView();

        // Choose a random faculty member
        currentFaculty = getRandomFaculty();
        facultyImageView.setImageResource(currentFaculty.getImageResourceId());

        // Set up the option buttons
        List<Button> optionButtons = Arrays.asList(optionButton1, optionButton2, optionButton3, optionButton4);
        List<Faculty> optionFaculties = getRandomFacultyOptions(currentFaculty);
        for (int i = 0; i < optionButtons.size(); i++) {
            Button button = optionButtons.get(i);
            Faculty faculty = optionFaculties.get(i);
            button.setText(faculty.getName());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (faculty == currentFaculty) {
                        // Correct answer, increment score and choose a new faculty
                        score++;
                        updateScoreTextView();
                        currentFaculty = getRandomFaculty();
                        facultyImageView.setImageResource(currentFaculty.getImageResourceId());
                        optionFaculties.clear();
                        optionFaculties.addAll(getRandomFacultyOptions(currentFaculty));
                        for (int i = 0; i < optionButtons.size(); i++) {
                            Button button = optionButtons.get(i);
                            Faculty faculty = optionFaculties.get(i);
                            button.setText(faculty.getName());
                        }
                        showToast("Correct!");
                    } else {
                        // Incorrect answer, show toast with correct answer
                        showToast("Incorrect. The correct answer was " + currentFaculty.getName());
                    }
                }
            });
        }
    }

    private Faculty getRandomFaculty() {
        Random random = new Random();
        int index = random.nextInt(facultyList.size());
        return facultyList.get(index);
    }

    private List<Faculty> getRandomFacultyOptions(Faculty currentFaculty) {
        List<Faculty> options = new ArrayList<>();
        options.add(currentFaculty);
        while (options.size() < 4) {
            Faculty option = getRandomFaculty();
            if (!options.contains(option)) {
                options.add(option);
            }
        }
        return options;
    }

    private void
