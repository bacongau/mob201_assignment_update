package com.example.testaddgooglemap;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testaddgooglemap.Adapter.MonhocAdapter;
import com.example.testaddgooglemap.DAO.MonhocDAO;
import com.example.testaddgooglemap.Model.MonHoc;

import java.util.ArrayList;
import java.util.List;

public class XemLichThiActivity extends AppCompatActivity {
    ListView listView;
    MonhocAdapter monhocAdapter;
    List<MonHoc> monHocList;
    MonhocDAO monhocDAO;
    ImageView button_back;
    TextView textView_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_lich_thi);

        anhxa();

        textView_title.setText("Lịch thi");

        khoitao();

        loadListViewLanDau();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Dialog dialog = new Dialog(XemLichThiActivity.this);
                dialog.setContentView(R.layout.dialog_thongtinlichthi);

                // anh xa
                EditText edt_mamon = dialog.findViewById(R.id.view_dialog_lichthi_mamon);
                EditText edt_tenmon = dialog.findViewById(R.id.view_dialog_lichthi_tenmon);
                EditText edt_ngayhoc = dialog.findViewById(R.id.view_dialog_lichthi_ngaythi);
                EditText edt_cahoc = dialog.findViewById(R.id.view_dialog_lichthi_cathi);
                EditText edt_phonghoc = dialog.findViewById(R.id.view_dialog_lichthi_phongthi);
                Button button_xacnhan = dialog.findViewById(R.id.button_dialog_lichthi_xacnhan);

                edt_mamon.setText(monHocList.get(position).getMaMon());
                edt_tenmon.setText(monHocList.get(position).getTenMon());
                edt_ngayhoc.setText(monHocList.get(position).getNgayThi());
                edt_cahoc.setText(monHocList.get(position).getCaHoc());
                edt_phonghoc.setText(monHocList.get(position).getPhongHoc());

                button_xacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(XemLichThiActivity.this, HocTapActivity.class));
            }
        });
    }

    private void anhxa() {
        listView = findViewById(R.id.listview_lichthi);
        textView_title = findViewById(R.id.textView_appbar_title);
        button_back = findViewById(R.id.button_back);
    }

    private void khoitao() {
        monhocDAO = new MonhocDAO(XemLichThiActivity.this);
        monHocList = new ArrayList<>();
        monHocList = monhocDAO.getAllData();
        monhocAdapter = new MonhocAdapter(XemLichThiActivity.this,R.layout.item_monhoc,monHocList);
    }

    private void loadListViewLanDau() {
        if (monHocList.size() == 0){
            Toast.makeText(this, "Chưa có dữ liệu", Toast.LENGTH_SHORT).show();
        }else {
            listView.setAdapter(monhocAdapter);
        }
    }

    //end
}