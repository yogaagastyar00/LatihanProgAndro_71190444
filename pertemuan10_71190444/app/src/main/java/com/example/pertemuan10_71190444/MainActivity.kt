package com.example.pertemuan10_71190444

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbHelper = DatabaseHelper(this)
        db = dbHelper.writableDatabase

        val inputNama = findViewById<EditText>(R.id.inputNama)
        val inputUsia = findViewById<EditText>(R.id.inputUsia)
        val saveData = findViewById<Button>(R.id.saveData)
        val hapusData = findViewById<Button>(R.id.hapusData)
        val cariDatabase = findViewById<Button>(R.id.cariDatabase)

        saveData.setOnClickListener {
            saveData(inputNama.text.toString(), inputUsia.text.toString())
        }
        hapusData.setOnClickListener {
            deleteData(inputNama.text.toString(), inputUsia.text.toString())
        }
        cariDatabase.setOnClickListener {
            searchData(inputNama.text.toString(), inputUsia.text.toString())
        }
        refreshData()
    }

    fun saveData(nama: String, usia: String){
        val values = ContentValues().apply {
            put(DatabaseContract.Penduduk.COLUMN_NAME_NAMA, nama)
            put(DatabaseContract.Penduduk.COLUMN_NAME_USIA, usia)
        }
        db.insert(DatabaseContract.Penduduk.TABLE_NAME, null, values)
        refreshData()
    }

    fun deleteData(nama: String, usia: String){
        val selection = "${DatabaseContract.Penduduk.COLUMN_NAME_NAMA} LIKE ? OR "+
                "${DatabaseContract.Penduduk.COLUMN_NAME_USIA} LIKE ?"
        val selectionArgs = arrayOf(nama, usia)
        val deletedRows = db.delete(DatabaseContract.Penduduk.TABLE_NAME, selection, selectionArgs)
        refreshData()
    }

    @SuppressLint("Range")
    fun searchData(nama: String, usia: String){
        val query = "SELECT * FROM ${DatabaseContract.Penduduk.TABLE_NAME} WHERE ${DatabaseContract.Penduduk.COLUMN_NAME_NAMA} LIKE ? OR "+
                "${DatabaseContract.Penduduk.COLUMN_NAME_USIA} LIKE ?"
        val selectionArgs = arrayOf(nama, usia)
        val cursor = db.rawQuery(
            query,
            selectionArgs
        )
        var result = ""
        with(cursor!!) {
            while (moveToNext()) {
                val nama = getString(getColumnIndex(DatabaseContract.Penduduk.COLUMN_NAME_NAMA))
                val usia = getString(getColumnIndex(DatabaseContract.Penduduk.COLUMN_NAME_USIA))
                result += "${nama} - ${usia} tahun\n"
            }
        }
        val tvHasilCari = findViewById<TextView>(R.id.cariData)
        tvHasilCari.text = result
    }

    @SuppressLint("Range")
    fun refreshData(){
        val columns = arrayOf(
            BaseColumns._ID,
            DatabaseContract.Penduduk.COLUMN_NAME_NAMA,
            DatabaseContract.Penduduk.COLUMN_NAME_USIA
        )
        val cursor = db.query(
            DatabaseContract.Penduduk.TABLE_NAME,
            columns,
            null,
            null,
            null,
            null,
            null
        )
        var result = ""
        with(cursor!!) {
            while (moveToNext()) {
                val nama = getString(getColumnIndex(DatabaseContract.Penduduk.COLUMN_NAME_NAMA))
                val usia = getString(getColumnIndex(DatabaseContract.Penduduk.COLUMN_NAME_USIA))
                result += "${nama} - ${usia} tahun\n"
            }
        }
        val tvHasil = findViewById<TextView>(R.id.upData)
        tvHasil.text = result
    }
}