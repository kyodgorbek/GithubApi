package yodgobekkomilov.edgar.com.githubapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class NextActivity extends AppCompatActivity {
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next_activity);

        resultTextView = findViewById(R.id.resultTextView);


        Intent intent = getIntent();
        String username = intent.getStringExtra("USER_NAME");

        CharSequence result = "username: " + username;
        resultTextView.setText(result);

    }
}