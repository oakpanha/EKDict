package ekdict.khmersoft.com.ekdictkhmersoft;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import java.io.IOException;
import java.io.PipedInputStream;
import java.util.ArrayList;

/**
 * Created by Arcjun5 on 8/7/2016.
 */
public class DatabaseAdapter {
    private Context mContext;
    private SQLiteDatabase mDb;
    private DatabaseHelper mDbHelper;
    private int count = 0;
    Cursor word;

    public DatabaseAdapter(Context context){
        this.mContext = context;
        mDbHelper = new DatabaseHelper(mContext);
    }

    public DatabaseAdapter createDatabase() throws SQLException{
        try{
            mDbHelper.createDatabase();
        }catch(IOException exp){
            throw new Error("Unable to create DataBase");
        }
        return this;
    }

    public DatabaseAdapter open() throws SQLException{
        try{
            mDbHelper.openDdatabase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        }catch(SQLException exp){
            throw exp;
        }
        return this;
    }

    public Cursor getWordList(){
        try{
            String sql = "SELECT word FROM tbldata";

            Cursor mCur = mDb.rawQuery(sql, null);
            if(mCur != null){
                mCur.moveToNext();
            }
            return mCur;
        }catch(SQLException exp){
            throw exp;
        }
    }

    public int ItemCount(){
        return word.getCount();
    }

    public String[] getWordList_cstr(){
        word = this.getWordList();
        ArrayList<String> w = new ArrayList<String>();
        while(!word.isAfterLast()){
            w.add(word.getString(word.getColumnIndex("word")));
            word.moveToNext();
        }
        return w.toArray(new String[w.size()]);
    }

    public String getDefinition(String word){
        String translate = "No Definition";
        try{
            String sql = "SELECT translate FROM tbldata WHERE word='" + word + "'";

            Cursor mCur = mDb.rawQuery(sql, null);
            if(mCur != null) {
                mCur.moveToNext();

                translate = mCur.getString(mCur.getColumnIndex("translate"));
                mCur.moveToNext();
            }
        }catch(SQLException exp){
            throw exp;
        }
        return translate;
    }
}
