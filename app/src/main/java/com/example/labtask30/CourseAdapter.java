package com.example.labtask30;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private final List<Course> courseList;
    private final OnItemClickListener listener;

    public CourseAdapter(List<Course> courseList, OnItemClickListener listener) {
        this.courseList = courseList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Course course = courseList.get(position);
        holder.tvCourseCode.setText(course.getCode());
        holder.tvCourseTitle.setText(course.getTitle());
        String creditHoursText = course.getCreditHours() + " Credit Hours";
        holder.tvCreditHours.setText(creditHoursText);
    }

    @Override
    public int getItemCount() {
        return courseList != null ? courseList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvCourseCode;
        public final TextView tvCourseTitle;
        public final TextView tvCreditHours;

        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            tvCourseCode = itemView.findViewById(R.id.tvCourseCode);
            tvCourseTitle = itemView.findViewById(R.id.tvCourseTitle);
            tvCreditHours = itemView.findViewById(R.id.tvCreditHours);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getBindingAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }
}
