package com.example.labtask30;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class CourseDetailActivity extends AppCompatActivity {

    public static final String EXTRA_CODE = "com.example.labtask30.EXTRA_CODE";
    public static final String EXTRA_TITLE = "com.example.labtask30.EXTRA_TITLE";
    public static final String EXTRA_INSTRUCTOR = "com.example.labtask30.EXTRA_INSTRUCTOR";
    public static final String EXTRA_EMAIL = "com.example.labtask30.EXTRA_EMAIL";
    public static final String EXTRA_DESCRIPTION = "com.example.labtask30.EXTRA_DESCRIPTION";
    public static final String EXTRA_CREDIT_HOURS = "com.example.labtask30.EXTRA_CREDIT_HOURS";

    private String instructorEmail;
    private String courseCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        TextView tvDetailCode = findViewById(R.id.tvDetailCode);
        TextView tvDetailTitle = findViewById(R.id.tvDetailTitle);
        TextView tvDetailInstructor = findViewById(R.id.tvDetailInstructor);
        TextView tvDetailEmail = findViewById(R.id.tvDetailEmail);
        TextView tvDetailCreditHours = findViewById(R.id.tvDetailCreditHours);
        TextView tvDetailDescription = findViewById(R.id.tvDetailDescription);
        Button btnEmailInstructor = findViewById(R.id.btnEmailInstructor);

        Intent intent = getIntent();
        if (intent != null) {
            courseCode = intent.getStringExtra(EXTRA_CODE);
            String title = intent.getStringExtra(EXTRA_TITLE);
            String instructor = intent.getStringExtra(EXTRA_INSTRUCTOR);
            instructorEmail = intent.getStringExtra(EXTRA_EMAIL);
            String description = intent.getStringExtra(EXTRA_DESCRIPTION);
            int creditHours = intent.getIntExtra(EXTRA_CREDIT_HOURS, 0);

            if (courseCode != null) {
                setTitle(courseCode);
                tvDetailCode.setText(courseCode);
            }

            if (title != null) {
                tvDetailTitle.setText(title);
            }

            if (instructor != null) {
                tvDetailInstructor.setText(getString(R.string.instructor_format, instructor));
            }

            if (instructorEmail != null) {
                tvDetailEmail.setText(getString(R.string.email_format, instructorEmail));
            }

            tvDetailCreditHours.setText(getString(R.string.credit_hours_format, creditHours));

            if (description != null) {
                tvDetailDescription.setText(description);
            }
        }

        btnEmailInstructor.setOnClickListener(v -> sendEmail());
    }

    private void sendEmail() {
        if (instructorEmail != null && !instructorEmail.isEmpty()) {
            try {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:" + instructorEmail));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Query regarding course " + (courseCode != null ? courseCode : ""));
                startActivity(emailIntent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(CourseDetailActivity.this, "No email client application found.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(CourseDetailActivity.this, "Instructor email is not available.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
