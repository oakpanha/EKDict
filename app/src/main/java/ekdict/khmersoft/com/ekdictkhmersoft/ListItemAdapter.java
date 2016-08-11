package ekdict.khmersoft.com.ekdictkhmersoft;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Arcjun5 on 8/8/2016.
 */
public class ListItemAdapter extends ArrayAdapter<String> {
    private Activity context;
    private String[] data;
    private int itemLayout;

    public ListItemAdapter(Activity context, int item_layout, String[] data){
        super(context, item_layout, data);
        this.context = context;
        this.data = data;
        this.itemLayout = item_layout;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(itemLayout, null, true);
        TextView txtWord = (TextView)rowView.findViewById(R.id.itemTxtBoxO);
        txtWord.setText(data[position]);
        return rowView;
    }
}
