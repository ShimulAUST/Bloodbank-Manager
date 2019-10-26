package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import Donor.donor;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="BloodBankManager";
    // private  static final String KEY_CONFIRM_PASSWORD="conf_pass";



    public DatabaseHandler(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        Log.v("db created", "yes");
        String sql= "CREATE TABLE DONORS (FIRSTNAME TEXT,LASTNAME TEXT, GENDER TEXT,BLOODGROUP TEXT,DATEOFBIRTH TEXT,DISTRICT TEXT ,ADDRESS TEXT,EMAILID TEXT,PHONENO TEXT,USERNAME TEXT,PASSWORD TEXT)";
        db.execSQL(sql);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stu
        db.execSQL("DROP TABLE IF EXISTS DONORS");
        onCreate(db);
    }
    public  boolean addDonor(donor d)
    {
        SQLiteDatabase db=this.getWritableDatabase();


        ContentValues contentValues=new ContentValues();
        contentValues.put("FIRSTNAME",d.getFirstname());
        contentValues.put("LASTNAME",d.getLastname());
        contentValues.put("GENDER",d.getGender());
        contentValues.put("BLOODGROUP",d.getBloodGroup());
        contentValues.put("DATEOFBIRTH",d.getDateofbirth());
        contentValues.put("DISTRICT",d.getDist());
        contentValues.put("ADDRESS",d.getAddress());
        contentValues.put("EMAILID",d.getEmail_id());
        contentValues.put("PHONENO",d.getPhoneno());
        contentValues.put("USERNAME",d.getUsername());
        contentValues.put("PASSWORD",d.getPass());

        long result=db.insert("DONORS",null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
public boolean loginValidation(donor d) {
    SQLiteDatabase db = this.getReadableDatabase();
    String query = "SELECT PASSWORD FROM DONORS WHERE USERNAME='"+d.getUsername()+"' AND PASSWORD='"+d.getPass()+"'";
    Cursor cursor=db.rawQuery(query, null);
    if(cursor.moveToFirst())
    {
        cursor.close();
        db.close();
        return true;
    }

    cursor.close();
    db.close();
    return false;
}
    public donor getProfileInfo(String username)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM DONORS WHERE USERNAME = '"+username+"'";

        Cursor cursor=db.rawQuery(query, null);
        donor myDonor = null;
        if(cursor.moveToFirst())
        {

            String fname=cursor.getString(cursor.getColumnIndex("FIRSTNAME"));
            String lname=cursor.getString(cursor.getColumnIndex("LASTNAME"));
            String sex=cursor.getString(cursor.getColumnIndex("GENDER"));
            String grp=cursor.getString(cursor.getColumnIndex("BLOODGROUP"));
            String date=cursor.getString(cursor.getColumnIndex("DATEOFBIRTH"));
            String dist=cursor.getString(cursor.getColumnIndex("DISTRICT"));
            String add=cursor.getString(cursor.getColumnIndex("ADDRESS"));
            String email=cursor.getString(cursor.getColumnIndex("EMAILID"));
            String phone=cursor.getString(cursor.getColumnIndex("PHONENO"));
            String pass=cursor.getString(cursor.getColumnIndex("PASSWORD"));


            myDonor=new donor(fname,lname,sex,grp,date,dist,add,email,phone,username,pass);

            cursor.close();
            db.close();
            return myDonor;
        }
        cursor.close();
        db.close();
        return null;
    }
    public List<donor> getAllDonor(String requiredGroup, String location)
    {
        List<donor> myDonorList=new ArrayList<donor>();
        SQLiteDatabase db=this.getReadableDatabase();

        String query = "SELECT * FROM DONORS WHERE BLOODGROUP = '"+requiredGroup+"' AND DISTRICT = '"+location+"'";


        Cursor cursor=db.rawQuery(query, null);

        if(cursor.moveToFirst())
        {
            do
            {
                String fsname=cursor.getString(cursor.getColumnIndex("FIRSTNAME"));
                String lsname=cursor.getString(cursor.getColumnIndex("LASTNAME"));
                String sex=cursor.getString(cursor.getColumnIndex("GENDER"));
                String grp=cursor.getString(cursor.getColumnIndex("BLOODGROUP"));
                String date=cursor.getString(cursor.getColumnIndex("DATEOFBIRTH"));
                String dist=cursor.getString(cursor.getColumnIndex("DISTRICT"));
                String add=cursor.getString(cursor.getColumnIndex("ADDRESS"));
                String email=cursor.getString(cursor.getColumnIndex("EMAILID"));
                String phone=cursor.getString(cursor.getColumnIndex("PHONENO"));
                String username=cursor.getString((cursor.getColumnIndex("USERNAME")));
                donor myDonor=new donor(fsname,lsname,sex,grp,date,dist,add,email,phone,username);
                myDonorList.add(myDonor);

            }while(cursor.moveToNext());
        }

        return myDonorList;
    }
    public int updateProfile(String username,String newusername,String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("USERNAME", newusername);
        values.put("PASSWORD", password);
        // updating row
        return db.update("DONORS", values, "USERNAME" + " = ?",
                new String[] { username });
    }

    public void deleteDonorAccount(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("DONORS", "USERNAME" + " = ?",
                new String[] { username });
        db.close();
    }


}


