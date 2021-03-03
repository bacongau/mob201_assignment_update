package com.example.testaddgooglemap;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testaddgooglemap.Adapter.MonhocAdapter;
import com.example.testaddgooglemap.DAO.MonhocDAO;
import com.example.testaddgooglemap.Model.MonHoc;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DangKyHocActivity extends AppCompatActivity {
    ListView listView;
    FloatingActionButton floatingActionButton;
    MonhocAdapter monhocAdapterDaChon;
    MonhocAdapter monhocAdapterCoTheChon;
    MonhocDAO monhocDAO;
    List<MonHoc> monHocListDaChon;
    List<MonHoc> monHocListCoTheDangKy;
    String maMonDuocChon;
    String tenMonDuocChon;
    String ngayHocMonDuocChon,caHocMonDuocChon,ngayThiMonDuocChon,phongHocMonDuocChon;
    TextView textView_title;
    ImageView button_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_hoc);

        // anh xa
        anhxa();

        textView_title.setText("Đăng ký học");

        // khoi tao
        khoitao();

        // load listview
        LoadListview();

        // set click
        ClickEvents();
    }

    private void khoitao() {
        monHocListCoTheDangKy = new ArrayList<>();
        monHocListCoTheDangKy.add(new MonHoc("MOB203", "Java 1","Thứ: 2 4 6","Ca 1","15/03/2020","P203"));
        monHocListCoTheDangKy.add(new MonHoc("MOB202", "Android cơ bản","Thứ: 3 5 7","Ca 2","16/03/2020","P204"));
        monHocListCoTheDangKy.add(new MonHoc("MOB201", "Dự án mẫu","Thứ: 2 4 6","Ca 3","17/03/2020","P303"));
        monHocListCoTheDangKy.add(new MonHoc("MOB204", "Android nâng cao","Thứ: 3 5 7","Ca 4","18/03/2020","P403"));
        monHocListCoTheDangKy.add(new MonHoc("MOB198", "Nhập môn lập trình","Thứ: 3 5 7","Ca 5","19/03/2020","P503"));
        monHocListCoTheDangKy.add(new MonHoc("ENG04", "Tiếng Anh 4","Thứ: 2 4 6","Ca 1","20/03/2020","P501"));

        monhocDAO = new MonhocDAO(this);
        monHocListDaChon = new ArrayList<>();
        monHocListDaChon = monhocDAO.getAllData();
        monhocAdapterCoTheChon = new MonhocAdapter(this, R.layout.item_monhoc, monHocListCoTheDangKy);
        monhocAdapterDaChon = new MonhocAdapter(this, R.layout.item_monhoc, monHocListDaChon);

    }

    private void LoadListview() {
        if (monHocListDaChon.size() == 0) {
            Toast.makeText(this, "Chưa có dữ liệu", Toast.LENGTH_SHORT).show();
        } else {
            listView.setAdapter(monhocAdapterDaChon);
        }
    }

    private void CapNhatListView() {
        monHocListDaChon = monhocDAO.getAllData();

        monhocAdapterDaChon = new MonhocAdapter(this, R.layout.item_monhoc, monHocListDaChon);

        listView.setAdapter(monhocAdapterDaChon);
    }

    private void ClickEvents() {
        // fab
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // khoi tao dialog + set view
                Dialog dialog = new Dialog(DangKyHocActivity.this);
                dialog.setContentView(R.layout.dialog_dang_ky_hoc);

                // anh xa
                Spinner spinner = dialog.findViewById(R.id.spinner_dialog_danhsachmonhoc);
                Button button_dangky = dialog.findViewById(R.id.button_dialog_dangkyhoc_dangky);
                Button button_huy = dialog.findViewById(R.id.button_dialog_dangkyhoc_huy);

                button_dangky.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MonHoc monHoc = new MonHoc();
                        monHoc.setMaMon(maMonDuocChon);
                        monHoc.setTenMon(tenMonDuocChon);
                        monHoc.setNgayHoc(ngayHocMonDuocChon);
                        monHoc.setCaHoc(caHocMonDuocChon);
                        monHoc.setNgayThi(ngayThiMonDuocChon);
                        monHoc.setPhongHoc(phongHocMonDuocChon);

                        if (monhocDAO.insert(monHoc) > 0) {
                            Toast.makeText(DangKyHocActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(DangKyHocActivity.this, "Bạn đã đăng ký môn học này rồi", Toast.LENGTH_SHORT).show();
                        }
                        CapNhatListView();
                        dialog.dismiss();
                    }
                });

                button_huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                // set up spinner
                spinner.setAdapter(monhocAdapterCoTheChon);
                // set click
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        maMonDuocChon = monHocListCoTheDangKy.get(position).getMaMon();
                        tenMonDuocChon = monHocListCoTheDangKy.get(position).getTenMon();
                        ngayHocMonDuocChon = monHocListCoTheDangKy.get(position).getNgayHoc();
                        caHocMonDuocChon = monHocListCoTheDangKy.get(position).getCaHoc();
                        ngayThiMonDuocChon = monHocListCoTheDangKy.get(position).getNgayThi();
                        phongHocMonDuocChon = monHocListCoTheDangKy.get(position).getPhongHoc();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                dialog.setCanceledOnTouchOutside(false);

                dialog.show();
            }
        });
        // end fab

        // listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //          Toast.makeText(DangKyHocActivity.this, "" + monHocListDaChon.size() + "\n" + monHocListDaChon.get(position).getMaMon() + "\n" + monHocListDaChon.get(position).getTenMon(), Toast.LENGTH_SHORT).show();
                Dialog dialog = new Dialog(DangKyHocActivity.this);
                dialog.setContentView(R.layout.dialog_thongtin_monhoc);

                // anh xa
                TextView textView_mamon = dialog.findViewById(R.id.textView_dialog_thongtin_monhoc_mamon);
                TextView textView_tenmon = dialog.findViewById(R.id.textView_dialog_thongtin_monhoc_tenmon);
                Button button_huymon = dialog.findViewById(R.id.button_dialog_thongtin_monhoc_huymon);
                Button button_dong = dialog.findViewById(R.id.button_dialog_thongtin_monhoc_dong);

                textView_mamon.setText(monHocListDaChon.get(position).getMaMon());
                textView_tenmon.setText(monHocListDaChon.get(position).getTenMon());

                button_huymon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(DangKyHocActivity.this);
                        builder.setTitle("Xác nhận hủy môn");
                        builder.setMessage("Bạn chắc chắn muốn hủy môn học này");
                        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog1, int which) {
                                if (monhocDAO.delete(monHocListDaChon.get(position).getMaMon()) > 0) {
                                    Toast.makeText(DangKyHocActivity.this, "Hủy môn thành công", Toast.LENGTH_SHORT).show();
                                    CapNhatListView();
                                    dialog.dismiss();
                                } else {
                                    Toast.makeText(DangKyHocActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        builder.setNegativeButton("Đóng", null);
                        AlertDialog alertDialog = builder.create();
                        builder.show();
                    }
                });

                button_dong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
        // end listview

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangKyHocActivity.this, HocTapActivity.class));
            }
        });
    }

    private void anhxa() {
        listView = findViewById(R.id.listview_dangkyhoc);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        textView_title = findViewById(R.id.textView_appbar_title);
        button_back = findViewById(R.id.button_back);
    }
}