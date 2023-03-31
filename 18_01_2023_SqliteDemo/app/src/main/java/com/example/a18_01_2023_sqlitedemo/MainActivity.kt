package com.example.a18_01_2023_sqlitedemo

import android.app.Activity
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.a18_01_2023_sqlitedemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var db : SQLiteDatabase
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        db = openOrCreateDatabase("db_student",Activity.MODE_PRIVATE,null)
        try {
                db.execSQL("create table STUDENT(_id integer primary key,name text not null)")
        }catch (e : Exception){
            Log.e("tag","exception is $e")
        }

        //insertRecords()
        //updateRecords()
        retriveRecords()
        updateRecords()
        retriveRecords()
        //insertRecords()
        deleteRecords()
        retriveRecords()
    }
    private fun deleteRecords(){
        var count = db.delete(
            "STUDENT",
            "_id =  ?",
            arrayOf("400")
        )
    }

    private fun retriveRecords(){
        var c = db.rawQuery("SELECT * FROM STUDENT",null)

        while(c.moveToNext()){
            var id = c.getInt(0)
            var name = c.getString(1)
            Log.e("tag","name -- $name id -- $id")
        }
        c.close()

        var c1 = db.rawQuery(
            "SELECT _id,name from STUDENT where _id > ? and _id < ?",
            arrayOf("300","600")
        )
        while(c1.moveToNext()){
            var id = c1.getInt(0)
            var name = c1.getString(1)
            Log.e("tag","$id -- $name")
        }
        c1.close()
    }

    private fun updateRecords(){
        var values = ContentValues()
        values.put("name","Bhakti")
        values.put("_id",103)

        var count = db.update(
            "STUDENT",
            values,
            "_id = ?",
            arrayOf("102")
        )
        Log.e("tag","Count : $count")
    }

    /*private fun updateRecords(){
        var values = ContentValues()
        values.put("price",3333)

        var count = db.update(
            "products1",
            values,
            "_id = ?",
            arrayOf("110")
        )
    }*/

    private fun insertRecords(){

        var values = ContentValues()
        values.put("_id",500)
        values.put("name","Pravin")

        var rowNum = db.insert("STUDENT",null,values)
        Log.e("tag","$rowNum")


        /*var values = ContentValues()
        values.put("_id",110)
        values.put("title","Product 1")
        values.put("price",1000)

        var rowNum = db.insert("products1",null,values)
        */

        /*for(i in 1..20){
            values.put("_id",i)
            values.put("title","Product$i")
            values.put("price",i)

            db.insert("products1",null,values)
        }*/

    }
}