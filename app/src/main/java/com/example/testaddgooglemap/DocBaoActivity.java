package com.example.testaddgooglemap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.testaddgooglemap.Adapter.TinTucAdapter;
import com.example.testaddgooglemap.Model.TinTuc;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DocBaoActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> arrayListLink;
    List<TinTuc> tinTucList = new ArrayList<>();
    ImageView button_back;
    TextView textView_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_bao);

        textView_title = findViewById(R.id.textView_appbar_title);
        button_back = findViewById(R.id.button_back);
        textView_title.setText("Tin tức");

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DocBaoActivity.this, MainActivity.class));
            }
        });

        listView = findViewById(R.id.listview_docbao);
        arrayListLink = new ArrayList<>();

        loadTinTucListview();
    }

    private void loadTinTucListview(){
        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                loadData();
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                // duoc goi khi luong ket thuc
                // khai bao adapter
                // bo  array vao adapter
                // set adapter cho listview

                TinTucAdapter adapter = new TinTucAdapter(DocBaoActivity.this,R.layout.item_tintuc,tinTucList);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //  Toast.makeText(MainActivity.this, ""+arrayListLink.get(position), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DocBaoActivity.this, TinTucActivity.class);
                        intent.putExtra("linktintuc",arrayListLink.get(position));
                        startActivity(intent);
                    }
                });

            }
        };
        asyncTask.execute();
    }


    private void loadData() {

        try {
            // kiem tra link dung hay sai
            URL url = new URL("https://giaoducthoidai.vn/rss/giao-duc.rss");

            // mo ket noi
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
            xmlPullParserFactory.setNamespaceAware(false);

            XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
            xmlPullParser.setInput(inputStream, "utf-8");

            int event = xmlPullParser.getEventType();

            TinTuc tinTuc = null;

            String text = null;
            Log.e("step1","step1");
            // neu su kien khac the dong cuoi cung thi se doc tiep
            while (event != xmlPullParser.END_DOCUMENT) {

                String tag = xmlPullParser.getName();

                switch (event) {
                    case XmlPullParser.START_TAG: {
                        if (tag.equalsIgnoreCase("item")) {
                            tinTuc = new TinTuc();
                        }

                        break;
                    }

                    case XmlPullParser.TEXT: {
                        text = xmlPullParser.getText();
                        break;
                    }

                    case XmlPullParser.END_TAG: {
                        if (tinTuc != null) {
                            if (tag.equalsIgnoreCase("title")) {
                                tinTuc.title = text;
                            }
                            if (tag.equalsIgnoreCase("description")) {
                                tinTuc.description = text;
                            }
                            if (tag.equalsIgnoreCase("id")) {
                                tinTuc.id = text;
                            }
                            if (tag.equalsIgnoreCase("pubDate")) {
                                tinTuc.pubDate = text;
                            }
                            if (tag.equalsIgnoreCase("link")) {
                                tinTuc.link = text;
                                arrayListLink.add(text);
                            }
                            if (tag.equalsIgnoreCase("item")) {
                                tinTucList.add(tinTuc);
                            }
                        }

                        break;
                    }

                }

                event = xmlPullParser.next();
            }

            Log.e("finish","" + tinTucList.size());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

}