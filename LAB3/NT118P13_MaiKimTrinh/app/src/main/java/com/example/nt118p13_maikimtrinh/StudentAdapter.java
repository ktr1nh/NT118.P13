package com.example.nt118p13_maikimtrinh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    private List<StudentData> studentList;
    private Context context;
    private OnStudentClickListener onStudentClickListener;

    public StudentAdapter(Context context, List<StudentData> studentList, OnStudentClickListener onStudentClickListener) {
        this.context = context;
        this.studentList = studentList;
        this.onStudentClickListener = onStudentClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_student, parent, false);
        return new ViewHolder(view, onStudentClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StudentData student = studentList.get(position);
        holder.tvIdName.setText(student.getId() + " - " + student.getName());

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public void removeStudent(int position) {
        studentList.remove(position);
        notifyItemRemoved(position);
    }

    public void updateStudentList(List<StudentData> students) {
        this.studentList = students;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView tvIdName;
        OnStudentClickListener onStudentClickListener;

        public ViewHolder(@NonNull View itemView, OnStudentClickListener onStudentClickListener) {
            super(itemView);
            tvIdName = itemView.findViewById(R.id.tv_id_name);
            this.onStudentClickListener = onStudentClickListener;

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onStudentClickListener.onStudentClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            onStudentClickListener.onStudentLongClick(getAdapterPosition());
            return true;
        }
    }

    public interface OnStudentClickListener {
        void onStudentClick(int position);
        void onStudentLongClick(int position);
    }
}