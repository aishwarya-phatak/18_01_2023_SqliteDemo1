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

        insertRecords()

    }

    private fun updateRecords(){
        var values = ContentValues()
        values.put("price",3333)

        var count = db.update(
            "products1",
            values,
            "_id = ?",
            arrayOf("110")
        )
    }

    private fun insertRecords(){

        var values = ContentValues()
        values.put("_id",100)
        values.put("name","Gauri")

        var rowNum = db.insert("STUDENT",null,values)
        Log.e("tag","$rowNum")


        /*var values = ContentValues()
        values.put("_id",110)
        values.put("title","Product 1")
        values.put("price",1000)

        var rowNum = db.insert("products1",null,values)
        */

        for(i in 1..20){
            values.put("_id",i)
            values.put("title","Product$i")
            values.put("price",i)

            db.insert("products1",null,values)
        }

    }
}