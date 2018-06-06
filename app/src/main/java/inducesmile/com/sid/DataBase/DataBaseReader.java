package inducesmile.com.sid.DataBase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;

import inducesmile.com.sid.DataBase.DataBaseConfig;


/**
 * Created by joao on 11/04/2018.
 */

public class DataBaseReader {

    SQLiteDatabase db;

    public DataBaseReader(DataBaseHandler dbHandler){
        db = dbHandler.getReadableDatabase();

    }

    // https://stackoverflow.com/questions/10600670/sqlitedatabase-query-method


    public Cursor ReadHumidadeTemperatura(String data){

        //To Do
       if (data!=null){
           Log.d("dataString",data);
       }

        Cursor cursor = db.query(
                DataBaseConfig.HumidadeTemperatura.TABLE_NAME,   // Nome da tabela
                null,
                data,
                null,
                null,
                null,
                null
        );
        return cursor;
    }


    public Cursor readAlertas(int idcultura){
        //Só vai buscar os alertas cuja cultura seja a escolhida e cujo campo migrado seja = 0.
        //O Migrado serve para indicar se o alerta já foi apresentado ao Investigador ou não.
        /*Questão: Aqui o readalertas vai ler os alertas no sybase ou no próprio sqlite?!
         * Se é no sqlite devia mostrar todos, correto?!*/
        String [] columns = {DataBaseConfig.Alertas.COLUMN_NAME_IDALERTA, DataBaseConfig.Alertas.COLUMN_NAME_TEXTO};
        String whereClause = DataBaseConfig.Alertas.COLUMN_NAME_MIGRADO+"=? AND "+
                DataBaseConfig.Alertas.COLUMN_NAME_IDCULTURA+"=?";
        String [] whereArgs = {Integer.toString(0),Integer.toString(idcultura)};

        Cursor cursor = db.query(
                DataBaseConfig.Alertas.TABLE_NAME,   // Nome da tabela
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return cursor;
    }

    public Cursor readCultura(){
        //To do
        Cursor cursor = db.query(
                DataBaseConfig.Cultura.TABLE_NAME,   // Nome da tabela
                null,
                null,
                null,
                null,
                null,
                null
        );
        return cursor;
    }

    public void update_Alerta_Migrado(int idAlerta){

        //ANTES DE UPDATE
        Cursor cursor = readAlertas(idAlerta);
        while (cursor.moveToNext()){
            Log.d("ANTES", cursor.getString(cursor.getColumnIndex("migrado")));
        }

        final ContentValues values = new ContentValues();
        values.put(DataBaseConfig.Alertas.COLUMN_NAME_MIGRADO, 1);
        String whereClause = DataBaseConfig.Alertas.COLUMN_NAME_IDALERTA+"=?";
        String [] whereArgs = {Integer.toString(idAlerta)};
        db.update(
                DataBaseConfig.Alertas.TABLE_NAME,
                values,
                DataBaseConfig.Alertas.COLUMN_NAME_IDALERTA+"="+idAlerta,
                null);

        /*db.delete(DataBaseConfig.Alertas.TABLE_NAME,
                    whereClause,
                    whereArgs);
         */
       //DEPOIS DO UPDATE
       cursor = readAlertas(idAlerta);
        while (cursor.moveToNext()){
            Log.d("DEPOIS", cursor.getString(cursor.getColumnIndex("migrado")));
        }
    }




}
