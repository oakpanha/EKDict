package ekdict.khmersoft.com.ekdictkhmersoft;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;

/**
 * Created by Arcjun5 on 8/9/2016.
 */
public class TextRendering {
    private SpannableStringBuilder ss;

    public TextRendering(Context myCon, String defi){

        String newTxt = defi;
        newTxt = newTxt.replace("<fn>", "");
        newTxt = newTxt.replace("</fn>", "");
        newTxt = newTxt.replace("<kh>", "");
        newTxt = newTxt.replace("</kh>", "");
        newTxt = newTxt.replace("<en>", "");
        newTxt = newTxt.replace("</en>", "");

        ss = new SpannableStringBuilder(newTxt);
        int cur = 0;
        int st = 0, ed = 0;
        Typeface tf = Typeface.createFromAsset(myCon.getAssets(), "limons1.ttf");

        for(int i = 0; i < defi.length() - 4; i++){
            if(defi.substring(i, i + 4).compareToIgnoreCase("<fn>") == 0) st = i + 4;
            if(defi.substring(i, i + 5).compareToIgnoreCase("</fn>") == 0){
                ed = i;
                int len = ed - st;
                int curl = cur + len;
                ss.setSpan(new StyleSpan(Typeface.BOLD), cur, curl, 0);
                ss.setSpan(new RelativeSizeSpan(1.5f), cur, curl, 0);
                ss.setSpan(new ForegroundColorSpan(Color.parseColor("#CC6600")), cur, curl, 0);
                ss.setSpan(new UnderlineSpan(), cur, curl, 0);
                cur = cur + len;
            }
            if(defi.substring(i, i + 4).compareToIgnoreCase("<en>") == 0) st = i + 4;
            if(defi.substring(i, i + 5).compareToIgnoreCase("</en>") == 0){
                ed = i;
                int len = ed - st;
                int curl = cur + len;
                ss.setSpan(new RelativeSizeSpan(1.2f), cur, curl, 0);
                cur = cur + len;
            }
            if(defi.substring(i, i + 4).compareToIgnoreCase("<kh>") == 0) st = i + 4;
            if(defi.substring(i, i + 5).compareToIgnoreCase("</kh>") == 0){
                ed = i;
                int len = ed - st;
                int curl = cur + len;
                ss.setSpan(new CustomTypefaceSpan("", tf), cur, curl, 0);
                ss.setSpan(new RelativeSizeSpan(2.5f), cur, curl, 0);
                ss.setSpan(new ForegroundColorSpan(Color.BLUE), cur, curl, 0);
                cur = cur + len;
            }
        }
    }

    public SpannableStringBuilder getRenderedText(){
        return ss;
    }
}
