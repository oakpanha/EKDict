package ekdict.khmersoft.com.ekdictkhmersoft;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Arcjun5 on 8/7/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_PATH="";
    private static String DB_NAME="ek.db";
    private SQLiteDatabase mDatabase;
    private Context mContext;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        if(android.os.Build.VERSION.SDK_INT >= 17){
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        }
        else
        {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
        this.mContext = context;
    }

    private boolean checkDataBase(){
        SQLiteDatabase checkDB = null;
        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }catch(SQLiteException e){}
        if(checkDB != null){
            checkDB.close();
            return true;
        }else{
            return false;
        }
    }

    private void copyDatabase() throws IOException{
        InputStream myInput = mContext.getAssets().open(DB_NAME);

        String outFileName = DB_PATH + DB_NAME;

        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void createDatabase() throws IOException{
        boolean mDataBaseExist = checkDataBase();
        if(!mDataBaseExist){
            this.getReadableDatabase();
            try{
                copyDatabase();
            }catch(IOException exp){
                throw new Error("Error Copying DataBase");
            }
        }
    }

    public void openDdatabase() throws SQLException{
        String mPath = DB_PATH + DB_NAME;
        mDatabase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    @Override
    public synchronized void close(){
        if(mDatabase != null) mDatabase.close();
        super.close();
    }
}
