package com.example.lab2_nt118;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class bai_5 extends AppCompatActivity {
    private ArrayList<Dish> dishList;
    private DishAdapter dishAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai_5);

        GridView gridView = findViewById(R.id.grid_view);
        Spinner spinner = findViewById(R.id.spinner);
        EditText editTextDishName = findViewById(R.id.et_tenmon);
        CheckBox checkBoxPromotion = findViewById(R.id.cb_promotion);
        Button buttonAddDish = findViewById(R.id.btn_add);

        dishList = new ArrayList<>();
        dishAdapter = new DishAdapter(this, dishList);
        gridView.setAdapter(dishAdapter);

        ThumbnailAdapter thumbnailAdapter = new ThumbnailAdapter(this);
        spinner.setAdapter(thumbnailAdapter);

        buttonAddDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dishName = editTextDishName.getText().toString().trim();

                // Kiểm tra xem tên món ăn không trống
                if (dishName.isEmpty()) {
                    Toast.makeText(bai_5.this, "Vui lòng nhập tên món ăn", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Lấy ID tài nguyên hình ảnh từ thumbnail được chọn
                int selectedThumbnail = Thumbnail.values()[spinner.getSelectedItemPosition()].getImg();

                // Lấy trạng thái của checkbox khuyến mãi
                boolean hasPromotion = checkBoxPromotion.isChecked();

                // Tạo món ăn mới với tên nhập từ EditText
                Dish newDish = new Dish(dishName, selectedThumbnail, hasPromotion);
                dishList.add(newDish);
                dishAdapter.notifyDataSetChanged();

                // Xóa nội dung trong EditText và uncheck checkbox
                editTextDishName.setText("");
                checkBoxPromotion.setChecked(false);

                // Hiển thị thông báo
                Toast.makeText(bai_5.this, "Đã thêm món ăn", Toast.LENGTH_SHORT).show();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Không cần xử lý gì khi chọn thumbnail, đã xử lý trong nút thêm
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
