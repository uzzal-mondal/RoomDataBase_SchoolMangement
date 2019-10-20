package com.example.roomdatawithstudentinfo.Search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

import com.example.roomdatawithstudentinfo.MainActivity;
import com.example.roomdatawithstudentinfo.R;

public class SearchActivity extends AppCompatActivity {

    private SearchView searchView;
    private TextView sTv;
    private EditText sEt;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = findViewById(R.id.toolbar_id);
        setSupportActionBar(toolbar);

        sTv = findViewById(R.id.searchText_id);
        sEt = findViewById(R.id.searchEditInfo_id);


        sEt.addTextChangedListener(serchTextWatcher);
        sTv.addTextChangedListener(serchTextWatcher);

       startActivity(new Intent(SearchActivity.this,MainActivity.class));

    }


    private TextWatcher serchTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            String searchInput = sEt.getText().toString().trim();
            String seachShow = sTv.getText().toString();

        }

        @Override
        public void afterTextChanged(Editable editable) {


            // this is search


        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search,menu);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(onQueryTextListener);

        return super.onCreateOptionsMenu(menu);
    }

    private SearchView.OnQueryTextListener onQueryTextListener =
            new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {

                    getSearchFromDB(newText);
                    return true;
                }

                private void getSearchFromDB(String searchText) {



                }

    };
}
