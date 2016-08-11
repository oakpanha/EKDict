package ekdict.khmersoft.com.ekdictkhmersoft;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.CursorJoiner;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Arcjun5 on 8/6/2016.
 */
public class act_word extends Activity {

    private ListView lv;
    public String[] myItems;
    ListItemAdapter adapter;
    Dialog dialog;

    private class AsyncRunner extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute(){
            // Get control
            lv = (ListView)findViewById(R.id.WordListList);
            dialog = new Dialog(act_word.this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
            dialog.setContentView(R.layout.activity_splash);
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // load word list
            MainActivity.dBAdapter.createDatabase();
            MainActivity.dBAdapter.open();

            // DataLoad
            myItems = MainActivity.dBAdapter.getWordList_cstr();
            int numOfItem = MainActivity.dBAdapter.ItemCount();

            // Build Adapter
            adapter = new ListItemAdapter(act_word.this, R.layout.db_item, myItems);
            return null;
        }

        @Override
        protected void onPostExecute(Void v){
            lv.setAdapter(adapter);
            dialog.dismiss();
        }
    }

    @Override
    protected void onCreate(Bundle Inst){
        super.onCreate(Inst);
        setContentView(R.layout.tab_words);

        // DB
        MainActivity.dBAdapter = new DatabaseAdapter(this);

        // Splash
        new AsyncRunner().execute();

        // All listener
        MainActivity.mSearchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int index = BinarySearch.search(myItems, s.toString());
                int tab = MainActivity.mTabHost.getCurrentTab();
                if(tab == 1) {
                    MainActivity.mTabHost.setCurrentTab(0);
                    MainActivity.mSearchBox.requestFocus();
                }
                lv.setSelection(index);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.searchTExt = myItems[position];
                MainActivity.mTabHost.setCurrentTab(1);
            }
        });
    }
}
