package com.example.pharmassest;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public   static final String DB_NAME = "PharmAssist_DB";
    public  static  final  Integer DB_VERSION = 1;
    public  static  final  String USER_TAB_NAME = "user";
    public  static  final  String USER_CLN_ID = "id";
    public  static  final  String USER_CLN_NAME = "name";
    public  static  final  String USER_CLN_PASSWORD = "password";
    public  static  final  String USER_CLN_ROLL = "roll";
    public  static  final  String MED_TAB_NAME = "medicine";
    public  static  final  String MED_CLN_ID = "id";
    public  static  final  String MED_CLN_NAME = "name";
    public  static  final  String MED_CLN_DESC = "description";
    public  static  final  String COM_TAB_NAME = "company";
    public  static  final  String COM_CLN_ID = "id";
    public  static  final  String COM_CLN_NAME = "name";
    public  static  final  String COM_CLN_CLOSET = "closet";
    public  static  final  String STORAGE_TAB_NAME = "storage";
    public  static  final  String STORAGE_CLN_MED = "med_id";
    public  static  final  String STORAGE_CLN_COM = "company";
    public  static  final  String STORAGE_CLN_SHELF = "shelf";
    //   public  static  final  String STORAGE_CLN_QUAN = "quantity";

    // Constructor
    public Database(@Nullable Context context) {super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        // create User Table
        db.execSQL( " CREATE TABLE " + USER_TAB_NAME + " ( " +
                USER_CLN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USER_CLN_NAME + " TEXT NOT NULL , " +
                USER_CLN_PASSWORD + " TEXT NOT NULL , " +
                USER_CLN_ROLL + " TEXT NOT NULL ) " );
        // create Medicine Table
        db.execSQL( " CREATE TABLE " + MED_TAB_NAME + " ( " +
                MED_CLN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MED_CLN_NAME + " TEXT NOT NULL , " +
                MED_CLN_DESC + " TEXT NOT NULL ) " );
        // create Company Table
        db.execSQL( " CREATE TABLE " + COM_TAB_NAME + " ( " +
                COM_CLN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COM_CLN_NAME + " TEXT NOT NULL UNIQUE , " +
                COM_CLN_CLOSET + " TEXT NOT NULL ) " );

        //create Storage Table
        db.execSQL( " CREATE TABLE " + STORAGE_TAB_NAME + " ( " +
                STORAGE_CLN_MED + " INTEGER NOT NULL , " +
                STORAGE_CLN_COM + " INTEGER NOT NULL , " +
                STORAGE_CLN_SHELF + " INTEGER NOT NULL , " +
                " PRIMARY KEY ( " + STORAGE_CLN_MED + " , " + STORAGE_CLN_COM +
                " ), FOREIGN KEY ( " + STORAGE_CLN_MED +
                " ) REFERENCES " + MED_TAB_NAME + " ( " +  MED_CLN_ID +
                " ), FOREIGN KEY ( " + STORAGE_CLN_COM +
                " ) REFERENCES " + COM_TAB_NAME + " ( " +  COM_CLN_ID +
                " ) ) " );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + USER_TAB_NAME );
        db.execSQL(" DROP TABLE IF EXISTS " + MED_TAB_NAME );
        db.execSQL(" DROP TABLE IF EXISTS " + COM_TAB_NAME );
        db.execSQL(" DROP TABLE IF EXISTS " + STORAGE_TAB_NAME );
        onCreate(db);
    }

    public  boolean checkCompany(String company){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + COM_TAB_NAME +" WHERE " +
                COM_CLN_NAME + " = ?" ,new String[]{company});
        if(c.moveToFirst()){
            c.close();
            return true;
        }else {
            return false;
        }
    }
    public  boolean checkMedicine(Medicine medicine){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + MED_TAB_NAME +" WHERE " +
                MED_CLN_NAME + " = ?" ,new String[]{medicine.getName()});
        if(c.moveToFirst()){
            c.close();
            return true;
        }else {
            return false;
        }

    }
    public  boolean checkMedicine(String medicine){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + MED_TAB_NAME +" WHERE " +
                MED_CLN_NAME + " = ?" ,new String[]{medicine});
        if(c.moveToFirst()){
            c.close();
            return true;
        }else {
            return false;
        }

    }
    public  Medicine getMedicine(String med){
        Medicine medicine = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + MED_TAB_NAME +" WHERE " +
                MED_CLN_NAME + "= ?" ,new String[]{med});
        if(c.moveToFirst()){
            do {
                int id = c.getInt(0);
                String name = c.getString(1);
                String description = c.getString(2);
                medicine = new Medicine(id,name,description);
            } while (c.moveToNext());
        }
        c.close();
        return  medicine;
    }
    public  Medicine getMedicine(int med){
        Medicine medicine = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + MED_TAB_NAME +" WHERE " +
                MED_CLN_ID + "= ?" ,new String[]{med+""});
        if(c.moveToFirst()){
            do {
                int id = c.getInt(0);
                String name = c.getString(1);
                String description = c.getString(2);
                medicine = new Medicine(id,name,description);

            } while (c.moveToNext());
        }
        c.close();
        return  medicine;
    }
    public Company getCompany(String co){
        Company company = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + COM_TAB_NAME +" WHERE " +
                COM_CLN_NAME + " LIKE ?" ,new String[]{ "%"+ co +"%"});
        if(c.moveToFirst()){
            do {
                int id = c.getInt(0);
                String name = c.getString(1);
                String closet = c.getString(2);
                company = new Company(id,name,closet);

            } while (c.moveToNext());
        }
        c.close();
        return company;
    }
    public  Company getComCloset(String clos){
        Company company = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + COM_TAB_NAME +" WHERE " +
                COM_CLN_CLOSET + " LIKE ?" ,new String[]{ "%"+ clos + "%"});
        if(c.moveToFirst()){
            do {
                int id = c.getInt(0);
                String name = c.getString(1);
                String closet = c.getString(2);
                company = new Company(id,name,closet);
            } while (c.moveToNext());
        }
        c.close();

        return  company;
    }
    public  boolean checkCloset(String clos){
        Company company = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + COM_TAB_NAME +" WHERE " +
                COM_CLN_CLOSET + " LIKE ?" ,new String[]{ "%"+ clos + "%"});
        if (c != null && c.moveToFirst()) {
            c.close();
            return true;
        } else
            return false;
    }
    public  boolean checkShelf(String closet,String shelf){
        Company company = getComCloset(closet);
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + STORAGE_TAB_NAME +" WHERE " +
                STORAGE_CLN_COM  + " LIKE ? AND " + STORAGE_CLN_SHELF + " LIKE ?" ,new String[]{ company.getId()+"", shelf});
        if (c != null && c.moveToFirst()) {
            c.close();
            return true;
        } else
            return false;
    }

    //Insert into Company
    public  boolean insertCompany (Company company){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COM_CLN_NAME,company.getName());
        values.put(COM_CLN_CLOSET,company.getCloset());
        long result = db.insert(COM_TAB_NAME,null,values);
        return result != -1;
    }

    public ArrayList<Company> getAllCompanies(){
        ArrayList<Company> companies = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + COM_TAB_NAME,null);
        if(c.moveToFirst()){
            do {
                int id = c.getInt(0);
                String name = c.getString(1);
                String closet = c.getString(2);
                Company company = new Company(id,name,closet);
                companies.add(company);
            } while (c.moveToNext());
        }
        c.close();
        return  companies;
    }

    public String getCloset(String company){
        String closet = "";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + COM_TAB_NAME + " WHERE " +
                COM_CLN_NAME + " = ?" ,new String[]{company});
        if(c.moveToFirst()){
            do {
                closet= c.getString(2);
            } while (c.moveToNext());
        }
        c.close();
        return closet;
    }
    public int getMedicineId(String medicine){
        int id = 0;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + MED_TAB_NAME + " WHERE " +
                MED_CLN_NAME + " = ?" ,new String[]{medicine});
        if(c.moveToFirst()){
            do {
                id= c.getInt(0);
            } while (c.moveToNext());
        }
        c.close();

        return id;
    }
    public int getCompanyId(String company){
        int id = 0;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + COM_TAB_NAME + " WHERE " +
                COM_CLN_NAME + " = ?" ,new String[]{company});
        if(c.moveToFirst()){
            do {
                id= c.getInt(0);
            } while (c.moveToNext());
        }
        c.close();

        return id;
    }
    public ArrayList<Storage> getMedStorage(String company){
        ArrayList<Storage> storage = new ArrayList<>();
        int id = 0;
        id = getCompanyId(company);
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + STORAGE_TAB_NAME+ " WHERE " +
                STORAGE_CLN_COM + " = ?" ,new String[]{id + ""});
        if(c.moveToFirst()){
            do {
                int idMed = c.getInt(0);
                int idCom= c.getInt(1);
                int shelf= c.getInt(2);
                storage.add(new Storage(idMed,idCom,shelf));
            } while (c.moveToNext());
        }
        c.close();
        return storage;
    }
    public ArrayList<Storage> getMedStorage(String company,String sh){
        ArrayList<Storage> storage = new ArrayList<>();
        int id = 0;
        id = getCompanyId(company);
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + STORAGE_TAB_NAME+ " WHERE " +
                STORAGE_CLN_COM + " = ? AND "+STORAGE_CLN_SHELF+ " = ?" ,new String[]{id + "" , sh});
        if(c.moveToFirst()){
            do {
                int idMed = c.getInt(0);
                int idCom= c.getInt(1);
                int shelf= c.getInt(2);
                storage.add(new Storage(idMed,idCom,shelf));
            } while (c.moveToNext());
        }
        c.close();
        return storage;
    }
    public ArrayList<Storage> getStorageShelf(String sh){
        ArrayList<Storage> storage = new ArrayList<>();
        int id = 0;

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + STORAGE_TAB_NAME+ " WHERE " +
                STORAGE_CLN_SHELF+ " = ?" ,new String[]{sh + ""});
        if(c.moveToFirst()){
            do {
                int idMed = c.getInt(0);
                int idCom= c.getInt(1);
                int shelf= c.getInt(2);
                storage.add(new Storage(idMed,idCom,shelf));
            } while (c.moveToNext());
        }
        c.close();
        return storage;
    }

    public Company getCompany(int companyId){
        SQLiteDatabase db = getReadableDatabase();
        Company company = null;
        Cursor c = db.rawQuery("SELECT * FROM " + COM_TAB_NAME + " WHERE " +
                COM_CLN_ID + " = ?" ,new String[]{companyId + ""});
        if(c.moveToFirst()){
            do {
                String name = c.getString(1);
                String closet = c.getString(2);
                company = new Company(companyId,name,closet);
            } while (c.moveToNext());
        }
        c.close();
        return company;
    }
    public  boolean addMedicine (Medicine medicine){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MED_CLN_NAME,medicine.getName());
        values.put(MED_CLN_DESC,medicine.getDescription());
        long result = db.insert(MED_TAB_NAME,null,values);
        return (result != -1 );

    }
    public  boolean addStorage (String medicine,String company,int shelf){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STORAGE_CLN_MED,getMedicineId(medicine));
        values.put(STORAGE_CLN_COM,getCompanyId(company));
        values.put(STORAGE_CLN_SHELF,shelf);
        long result = db.insert(STORAGE_TAB_NAME,null,values);
        return (result != -1 );
    }
    public  Storage getStorage (String medicine){
        SQLiteDatabase db = getReadableDatabase();
        Storage storage =  null ;
        Cursor c = db.rawQuery("SELECT * FROM " + STORAGE_TAB_NAME + " WHERE " +
                STORAGE_CLN_MED + " = ?" ,new String[]{getMedicineId(medicine)+""});
        if(c.moveToFirst()){
            do {
                int medId = c.getInt(0);
                int comId = c.getInt(1);
                int shelf = c.getInt(2);
                storage = new Storage(medId,comId,shelf);
            } while (c.moveToNext());
        }
        c.close();

        return storage;
    }
    public  boolean deleteStorage(String medicine){
        SQLiteDatabase db = getWritableDatabase();
        String args [] = {String.valueOf(getMedicineId(medicine))};
        int result = db.delete(STORAGE_TAB_NAME, STORAGE_CLN_MED + " = ?",args);
        return  result > 0;
    }
    public  boolean deleteMedicine(String medicine){
        SQLiteDatabase db = getWritableDatabase();
        String args [] = {"%" + medicine + "%"};
        int result = db.delete(MED_TAB_NAME, MED_CLN_NAME + " LIKE ?",args);
        return  result > 0;
    }
    public boolean updateMedicine(Medicine medicine){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MED_CLN_NAME,medicine.getName());
        values.put(MED_CLN_DESC,medicine.getDescription());
        String args [] = {medicine.getName()};
        long result = db.update(MED_TAB_NAME,values,MED_CLN_NAME + " = ?",args);
        return (result != -1 );
    }
    public  boolean updateStorage (String medicine,String company,int shelf){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STORAGE_CLN_COM,getCompanyId(company));
        values.put(STORAGE_CLN_SHELF,shelf);
        String args [] = {getMedicineId(medicine) + ""};
        long result = db.update(STORAGE_TAB_NAME,values,STORAGE_CLN_MED + " = ?",args);
        return (result != -1 );
    }
}
