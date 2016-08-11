package ekdict.khmersoft.com.ekdictkhmersoft;

import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;

import java.util.List;
import java.util.Locale;

public class MainActivity extends ActivityGroup {
    public static TabHost mTabHost;
    public static EditText mSearchBox;
    public static String searchTExt = "a";
    public static DatabaseAdapter dBAdapter;
    public static TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Few control
        mSearchBox = (EditText)findViewById(R.id.txtSearch);
        //setup tab layout
        mTabHost = (TabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this.getLocalActivityManager());
        //Tab UI
        final TabHost.TabSpec spec_word, spec_def;

        spec_word = mTabHost.newTabSpec("Words");
        spec_word.setIndicator("Words");

        spec_def = mTabHost.newTabSpec("Definition");
        spec_def.setIndicator("Definition");

        final Intent tword = new Intent(this, act_word.class);
        spec_word.setContent(tword);

        final Intent tdef = new Intent(this, act_definition.class);
        spec_def.setContent(tdef);

        mTabHost.addTab(spec_word);
        mTabHost.addTab(spec_def);

        // go speaker
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.UK);
                }
            }
        });
    }
}
