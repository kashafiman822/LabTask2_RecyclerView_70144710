package com.example.labtask30;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.labtask30.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CourseAdapter.OnItemClickListener {

    private ArrayList<Course> courses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setSupportActionBar(binding.toolbar);

        // Populate course list with BS-SE programme courses
        courses = new ArrayList<>();
        courses.add(new Course("CS101", "Programming Fundamentals", "Mr. Shakeel", "shakeel@uol.edu.pk", "Introduction to core programming concepts, control structures, functions, arrays, and problem-solving techniques using C++/Java.", 4));
        courses.add(new Course("SE201", "Software Engineering Concepts", "Ms. Sara Ahmed", "sara.ahmed@uol.edu.pk", "Overview of software engineering principles, SDLC models, requirements gathering, system modeling, and software ethics.", 3));
        courses.add(new Course("SE202", "Object Oriented Programming", "Ms. Saira", "saira@uol.edu.pk", "Comprehensive study of OOP paradigms including encapsulation, inheritance, polymorphism, abstraction, and design patterns.", 4));
        courses.add(new Course("SE301", "Data Structures & Algorithms", "Mr. Farooq", "farooq@uol.edu.pk", "In-depth coverage of dynamic memory allocation, stacks, queues, linked lists, trees, graphs, sorting, and complexity analysis.", 4));
        courses.add(new Course("SE302", "Database Systems", "Mr. Babar", "babar@uol.edu.pk", "Fundamentals of relational databases, SQL queries, ER modeling, normalization, indexing, and transaction management.", 4));
        courses.add(new Course("SE303", "Software Design & Architecture", "Ms. Aqsa", "aqsa@uol.edu.pk", "Architectural styles, design patterns, UML diagrams, component-based software development, and system quality attributes.", 3));
        courses.add(new Course("SE401", "Web Engineering", "Ms. Seemal", "Seemal@uol.edu.pk", "Full-stack web application development including HTML5, CSS3, JavaScript, RESTful web services, and backend frameworks.", 3));
        courses.add(new Course("SE402", "Mobile Application Development", "Mr. Arsalan", "arsalan@uol.edu.pk", "Design and development of native Android applications using Java/Kotlin, layouts, RecyclerViews, Intents, and Material Design.", 3));

        // Find RecyclerView rvCourses
        RecyclerView rvCourses = findViewById(R.id.rvCourses);

        // Set a LinearLayoutManager on the RecyclerView rvCourses
        rvCourses.setLayoutManager(new LinearLayoutManager(this));

        // Attach the adapter with setAdapter()
        CourseAdapter adapter = new CourseAdapter(courses, this);
        rvCourses.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        Course course = courses.get(position);
        Intent intent = new Intent(MainActivity.this, CourseDetailActivity.class);
        intent.putExtra(CourseDetailActivity.EXTRA_CODE, course.getCode());
        intent.putExtra(CourseDetailActivity.EXTRA_TITLE, course.getTitle());
        intent.putExtra(CourseDetailActivity.EXTRA_INSTRUCTOR, course.getInstructor());
        intent.putExtra(CourseDetailActivity.EXTRA_EMAIL, course.getEmail());
        intent.putExtra(CourseDetailActivity.EXTRA_DESCRIPTION, course.getDescription());
        intent.putExtra(CourseDetailActivity.EXTRA_CREDIT_HOURS, course.getCreditHours());
        startActivity(intent);
    }
}
