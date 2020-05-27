package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase sdb;
    ListView listView;
    EditText editText;
    OpenHelper dbHelper;
    SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper =new OpenHelper(this);
        sdb=dbHelper.getWritableDatabase();
        LinkedList<Book> listBook=new LinkedList<Book>();

        //read data from the interface
        //editText name.getText.toString();
        listBook.add(new Book("Л. Толстой", "Война и мир", 1972, R.drawable.l));
        listBook.add(new Book("Ф. Достоевский", "Идиот", 1867, R.drawable.l));
        listBook.add(new Book("Н. Лесков", "Очарованный странник", 1995, R.drawable.l));
        listBook.add(new Book("Н. Лесков", "Левша", 2004, R.drawable.v));
        listBook.add(new Book("А. Чехов", "Вишневый сад", 2008, R.drawable.v));
        listBook.add(new Book("А. Пушкин", "Дубровский", 1833, R.drawable.v));
        listBook.add(new Book("Н. Гоголь", "Ночь перед Рождеством", 1832, R.drawable.v));
        listBook.add(new Book("А. Пушкин", "Капитанская дочка", 1833, R.drawable.v));
        listBook.add(new Book("Ф. Достоевский", "Идиот", 1983, R.drawable.v));
        listBook.add(new Book("Ф. Достоевский", "Бесы", 1872, R.drawable.v));
        listBook.add(new Book("Ф. Достоевский", "Бедные Люди", 1967, R.drawable.l));
        listBook.add(new Book("Ф. Достоевский", "Белые Ночи", 1878, R.drawable.l));
        listBook.add(new Book("Ф. Достоевский", "Игрок", 1967, R.drawable.l));
        listBook.add(new Book("М. Булгаков", "Мастер и Маргарита", 1966, R.drawable.l));
        listBook.add(new Book("Карл Маркс", "Капитал", 1867, R.drawable.l));

        ArrayList<HashMap<String,Object>> booklist=new ArrayList<>();
        HashMap<String,Object> map;
        for(int i=0;i<listBook.size();i++){
            map=new HashMap<String, Object>();
            map.put("name",listBook.get(i).name);
            map.put("author",listBook.get(i).author);
            map.put("year",listBook.get(i).year);
            map.put("cover",listBook.get(i).cover);
            booklist.add(map);
            if(simpleAdapter!=null){
               simpleAdapter.notifyDataSetChanged();
            }
            //sdb.execSQL("INSERT INTO"+ null+" " );
            ContentValues contents=new ContentValues();
            contents.put("name",listBook.get(i).name);
            contents.put("author",listBook.get(i).author);
            contents.put("year",listBook.get(i).year);
            contents.put("cover",listBook.get(i).cover);
            //sdb.insert(OpenHelper.DATABASE_TABLE,null,contents);
        }
        String id[]={"name","author","year","cover"};
        int to[]={R.id.name,R.id.author,R.id.year,R.id.cover};
        simpleAdapter=new SimpleAdapter(this,booklist,R.layout.list_view,id,to);
        simpleAdapter.notifyDataSetChanged();
        listView=(ListView)findViewById(R.id.listView);
        listView.setAdapter(simpleAdapter);
    }
}
class Book {
     String name;
     String author;
     int year;
    int cover;

    public Book(String name, String author, int year, int image) {
        this.name = name;
        this.author = author;
        this.year = year;
        cover = image;
    }
}