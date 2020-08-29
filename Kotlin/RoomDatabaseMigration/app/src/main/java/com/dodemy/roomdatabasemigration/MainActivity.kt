package com.dodemy.roomdatabasemigration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbHelper = DBHelper(this)


        object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

            }
        }

        btnInsertSqlite.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    dbHelper.insertContact("velm", "8122", "vel@gmail.com")
                } catch (e: Exception) {
                    Log.d("Error", e.message.toString())
                }
            }
        }

        fetchFromRoom.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val db1: Table1Dao = Database1.getInstance(this@MainActivity)!!.table1Dao()
                    Log.d("us1", db1.fetchAll().toString())
                    Log.d("us2", db1.fetchAll2().toString())
                    val items = db1.fetchAll();
                    db1.deleteItem(items[0])
                    Log.d("us1", db1.fetchAll().toString())
                } catch (e: Exception) {
                    e.message?.let { it1 -> Log.d("Error", it1) }
                }
            }

        }
    }
}