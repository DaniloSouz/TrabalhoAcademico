package com.example.trabalhoacademico;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "UNICARIOCA.DB";

    public static final String TABLE_DISC = "DISCIPLINA_TB";
    public static final String COL2_D = "NOME";
    public static final String COL3_D = "PROFESSOR";

    public static final String TABLE_PROF = "PROFESSOR_TB";
    public static final String COL1 = "MATRICULA";
    public static final String COL2 = "NOME";

    public static final String TABLE_ALU = "ALUNO_TB";
    public static final String COL1_A = "id";
    public static final String COL2_A = "NOME";
    public static final String COL4_A = "APS_AV1";
    public static final String COL5_A = "PROVA_AV1";
    public static final String COL6_A = "NOTA_AV1";
    public static final String COL7_A = "APS_AV2";
    public static final String COL8_A = "PROVA_AV2";
    public static final String COL9_A = "NOTA_AV2";
    public static final String COL10_A = "NOTA_AV3";
    public static final String COL11_A = "AV1_AV2";
    public static final String COL12_A = "AV1_AV3";
    public static final String COL13_A = "AV2_AV3";
    public static final String COL14_A = "MEDIA";
    public static final String COL15_A = "STATUS";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_ALU + " (  id integer primary key autoincrement, NOME TEXT, APS_AV1 FLOAT, PROVA_AV1 FLOAT, NOTA_AV1 FLOAT, APS_AV2 FLOAT, PROVA_AV2 FLOAT, NOTA_AV2 FLOAT, NOTA_AV3 FLOAT, AV1_AV2 FLOAT, AV1_AV3 FLOAT, AV2_AV3 FLOAT, MEDIA FLOAT, STATUS TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALU);
        onCreate(db);

    }

    public boolean insertData( String NOME, String APS_AV1, String PROVA_AV1, String NOTA_AV1, String APS_AV2, String PROVA_AV2, String NOTA_AV2, String NOTA_AV3, String MEDIA, String STATUS) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL2_A, NOME);
        contentValues.put(COL4_A, APS_AV1);
        contentValues.put(COL5_A, PROVA_AV1);
        contentValues.put(COL6_A, NOTA_AV1);
        contentValues.put(COL7_A, APS_AV2);
        contentValues.put(COL8_A, PROVA_AV2);
        contentValues.put(COL9_A, NOTA_AV2);
        contentValues.put(COL10_A, NOTA_AV3);
        contentValues.put(COL14_A, MEDIA);
        contentValues.put(COL15_A, STATUS);
        long result = db.insert(TABLE_ALU, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_ALU, null);
        return res;

    }
    public boolean updateData(String id, String APS_AV1,String PROVA_AV1,String Nota_Av1 ,String APS_AV2,String PROVA_AV2, String Nota_Av2, String NOTA_AV3, String MEDIA, String STATUS) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1_A,id);
        contentValues.put(COL4_A,APS_AV1);
        contentValues.put(COL5_A,PROVA_AV1);
        contentValues.put(COL6_A,Nota_Av1);
        contentValues.put(COL9_A,Nota_Av2);
        contentValues.put(COL7_A,APS_AV2);
        contentValues.put(COL8_A,PROVA_AV2);
        contentValues.put(COL10_A,NOTA_AV3);
        contentValues.put(COL14_A,MEDIA);
        contentValues.put(COL15_A,STATUS);
        db.update(TABLE_ALU, contentValues, " id1= ?",new String[] { id });
        return true;
    }
    public Integer deleteData ( String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        return db.delete(TABLE_ALU, "id = ?",new String[] {id});
    }
}
