package ekdict.khmersoft.com.ekdictkhmersoft;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.SpannableString;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by Arcjun5 on 8/6/2016.
 */
public class act_definition extends Activity {

    public static TextView  wv, tv;
    public static ImageView iv;
    private ScrollView sv;

    @Override
    protected void onCreate(Bundle Inst){
        super.onCreate(Inst);
        setContentView(R.layout.tab_definition);
        //get all control
        tv = (TextView)findViewById(R.id.txtDefinition);
        wv = (TextView)findViewById(R.id.txtTextWord);
        iv = (ImageView)findViewById(R.id.ivSpeaker);

        iv.setClickable(true);
        iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        iv.setBackgroundDrawable(getResources().getDrawable(R.drawable.speakersec));
                        break;
                    case MotionEvent.ACTION_UP:
                        iv.setBackgroundDrawable(getResources().getDrawable(R.drawable.speaker));
                        if(!MainActivity.tts.isSpeaking()) {
                            MainActivity.tts.speak(MainActivity.searchTExt, TextToSpeech.QUEUE_FLUSH, null);
                        }
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        String translate = MainActivity.dBAdapter.getDefinition(MainActivity.searchTExt);
        //get search text
        TextRendering tr = new TextRendering(this, translate);
        //operation
        wv.setText(MainActivity.searchTExt);
        tv.setText(tr.getRenderedText());
    }
}
