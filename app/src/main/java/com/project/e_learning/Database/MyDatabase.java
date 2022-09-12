package com.project.e_learning.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.project.e_learning.Model.Common;
import com.project.e_learning.Model.History;
import com.project.e_learning.Model.Question;
import com.project.e_learning.Model.User;

import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {
    private Context context;

    public static final String TABLE_NAME = "users";
    public static final String COLUMN_NAME_FIRSTNAME = "Firstname";
    public static final String COLUMN_NAME_LASTNAME = "Lastname";
    public static final String COLUMN_NAME_EMAIL = "Email";
    public static final String COLUMN_NAME_PASSWORD = "Password";

    public static final String QUIZ_TABLE_NAME = "Question";
    public static final String COLUMN_NAME_QUESTION = "Question";
    public static final String COLUMN_NAME_ANSWER_A = "AnswerA";
    public static final String COLUMN_NAME_ANSWER_B = "AnswerB";
    public static final String COLUMN_NAME_ANSWER_C = "AnswerC";
    public static final String COLUMN_NAME_ANSWER_D = "AnswerD";
    public static final String COLUMN_NAME_CORRECT_ANSWER = "CorrectAnswer";
    public static final String COLUMN_NAME_CATEGORY_ID = "CategoryId";
    public static final String COLUMN_NAME_IS_IMAGE_QUESTION = "IsImageQuestion";

    public static final String HISTORY_TABLE_NAME = "History";
    public static final String COLUMN_NAME_USER= "Username";
    public static final String COLUMN_NAME_SUBJECT= "Subject";
    public static final String COLUMN_NAME_ATTEMPTED_QUESTIONS= "AttemptedQuestions";
    public static final String COLUMN_NAME_CORRECT= "Correct";
    public static final String COLUMN_NAME_INCORRECT= "Incorrect";
    public static final String COLUMN_NAME_TOTAL= "Total";
    public static final String COLUMN_NAME_DATE= "Date";


    private String[] userscoloumname;
    private String[] questioncoloumname;
    private String[] historycoloumnname;

    public MyDatabase(@Nullable Context context) {
        super(context, "Mydb", null, 1);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + "(id integer primary key autoincrement," + COLUMN_NAME_FIRSTNAME + " text," + COLUMN_NAME_LASTNAME + " text," + COLUMN_NAME_EMAIL + " text," + COLUMN_NAME_PASSWORD + " text)");
        sqLiteDatabase.execSQL("create table " + QUIZ_TABLE_NAME + "(id integer primary key autoincrement," + COLUMN_NAME_QUESTION + " text," + COLUMN_NAME_ANSWER_A + " text," + COLUMN_NAME_ANSWER_B + " text," + COLUMN_NAME_ANSWER_C + " text," + COLUMN_NAME_ANSWER_D + " text," + COLUMN_NAME_CORRECT_ANSWER + " text," + COLUMN_NAME_CATEGORY_ID + " text," + COLUMN_NAME_IS_IMAGE_QUESTION + " text)");
        sqLiteDatabase.execSQL("create table " + HISTORY_TABLE_NAME + "(id integer primary key autoincrement," + COLUMN_NAME_USER + " text," + COLUMN_NAME_SUBJECT + " text," + COLUMN_NAME_ATTEMPTED_QUESTIONS + " integer," + COLUMN_NAME_CORRECT + " integer," + COLUMN_NAME_INCORRECT + " integer," + COLUMN_NAME_TOTAL + " integer," + COLUMN_NAME_DATE + " integer)");
        userscoloumname = getdata(TABLE_NAME).getColumnNames();
        questioncoloumname = getdata(QUIZ_TABLE_NAME).getColumnNames();
        historycoloumnname = getdata(HISTORY_TABLE_NAME).getColumnNames();

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertuserdata(User user){
        SQLiteDatabase sd = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME_FIRSTNAME,user.getFirstname());
        cv.put(COLUMN_NAME_LASTNAME,user.getLastname());
        cv.put(COLUMN_NAME_EMAIL,user.getEmail());
        cv.put(COLUMN_NAME_PASSWORD,user.getPassword());
        sd.insert(TABLE_NAME,null,cv);
    }

    public String checklogin(String user,String password){
        SQLiteDatabase sd = this.getReadableDatabase();
        //"Select * From " + TABLE_NAME + " Where " + COLUMN_NAME_EMAIL + " = " + user
        Cursor cursor = sd.query(TABLE_NAME, userscoloumname,COLUMN_NAME_EMAIL + " = ?",new String[]{user},null,null,null);

        if (cursor != null) {
            if (cursor.moveToFirst())
                if (password.toUpperCase().equals(cursor.getString(4).toString().toUpperCase())) {
                    return "Login Successfully";
                } else {
                    return "Password don't match";
                }
            }else{
            return "Email don't match";
        }
        return "User not Exist";
        }

    public Cursor getdata(String tablename){
        SQLiteDatabase sd = this.getReadableDatabase();
        Cursor cursor = sd.rawQuery("select * From " + tablename,null);
        return cursor;
    }

    public void deleteuserdata(User user){
        SQLiteDatabase sd  = this.getWritableDatabase();
        sd.delete(TABLE_NAME," id = ?", new String[] {String.valueOf(user.getID())});
    }

    public int getCount(String tablename) {
        String countQuery = "SELECT  * FROM " + tablename;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }

    public void insertquizdata(Question q){
        SQLiteDatabase sd = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME_QUESTION,q.getQuestion());
        cv.put(COLUMN_NAME_ANSWER_A,q.getAnswerA());
        cv.put(COLUMN_NAME_ANSWER_B,q.getAnswerB());
        cv.put(COLUMN_NAME_ANSWER_C,q.getAnswerC());
        cv.put(COLUMN_NAME_ANSWER_D,q.getAnswerD());
        cv.put(COLUMN_NAME_CORRECT_ANSWER,q.getCorrectAnswer());
        cv.put(COLUMN_NAME_CATEGORY_ID,q.getCategoryId());
        cv.put(COLUMN_NAME_IS_IMAGE_QUESTION,q.getIsImageQuestion());
        sd.insert(QUIZ_TABLE_NAME,null,cv);
    }

    public ArrayList<Question> getQuestions(String CategoryId){
        SQLiteDatabase sd = this.getReadableDatabase();
        ArrayList<Question> list = new ArrayList<>();
        Cursor cursor = sd.query(QUIZ_TABLE_NAME,questioncoloumname,COLUMN_NAME_CATEGORY_ID + " = ?",new String[]{CategoryId},null,null,null);
        if (cursor != null){
            if (cursor.moveToFirst())
            do {
           list.add(new Question(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8)));
            } while (cursor.moveToNext());
            }
        return list;
    }

    public void inserthistorydata(History h){
        SQLiteDatabase sd = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME_USER,h.getUsername());
        cv.put(COLUMN_NAME_SUBJECT,h.getSubject());
        cv.put(COLUMN_NAME_ATTEMPTED_QUESTIONS,h.getAttempted_Questions());
        cv.put(COLUMN_NAME_CORRECT,h.getCorrect());
        cv.put(COLUMN_NAME_INCORRECT,h.getIncorrect());
        cv.put(COLUMN_NAME_TOTAL,h.getTotal());
        cv.put(COLUMN_NAME_DATE,h.getDate());
        sd.insert(HISTORY_TABLE_NAME,null,cv);
    }

    public Cursor gethistorydata(String username){
        SQLiteDatabase sd = this.getWritableDatabase();
        Cursor cursor = sd.query(HISTORY_TABLE_NAME,historycoloumnname,COLUMN_NAME_USER + " = ?",new String[]{username},null,null,null);
        return cursor;
    }

    public User getcurrentuserdata(String user){
        SQLiteDatabase sd = this.getReadableDatabase();
        //"Select * From " + TABLE_NAME + " Where " + COLUMN_NAME_EMAIL + " = " + user
        Cursor cursor = sd.query(TABLE_NAME, userscoloumname,COLUMN_NAME_EMAIL + " = ?",new String[]{user},null,null,null);
        User u = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                u = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
            }
        }
        return u;
        }

}
