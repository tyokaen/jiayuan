package com.example.jiayuan.library;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.io.File;
import java.util.ArrayList;

import Tool.DealwithFile;

public class search_result extends Activity {
ListView listView;
ArrayList<String> arrayList=new ArrayList<String>();
SearchView searchView;
    DealwithFile dealwithFile=new DealwithFile();
    ArrayAdapter<String> adapter1;
    File saveSearch=new File(Environment.getExternalStorageDirectory(),"search.csv");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        ActionBar actionBar=getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        listView=(ListView) findViewById(R.id.search_result);
        listView.setTextFilterEnabled(true);

            //ReadFromFile(savelist, saveSearch);
        dealwithFile.ReadFromFile(arrayList,saveSearch);


        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
            //autoCompleteTextView.setAdapter(adapter1);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.find,menu);
        searchView=(SearchView) menu.findItem(R.id.find1).getActionView();
        searchView.setQueryHint("please enter");
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(TextUtils.isEmpty(newText)){
                    listView.clearTextFilter();
                }
                else{
                    listView.setFilterText(newText);
                    listView.setAdapter(adapter1);
                }
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==android.R.id.home){
            Intent intent=new Intent(this,Main.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
