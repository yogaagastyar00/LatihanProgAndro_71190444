package com.example.pertemuan7_71190444

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listKontak = ArrayList<ListRincianKontak>()
        listKontak.add(ListRincianKontak("Ruffi", "33445", R.mipmap.icon1,
            "ruffi33@gmail.com", "1 Juni 2000"))
        listKontak.add(ListRincianKontak("Angga", "44556", R.mipmap.icon2,
        "angga44@gmail.com", "12 September 2001"))
        listKontak.add(ListRincianKontak("Rani", "55678", R.mipmap.icon3,
        "rani55@gmail.com", "9 Oktober 2002"))

        val recKontak = findViewById<RecyclerView>(R.id.recKontak)
        recKontak.layoutManager = LinearLayoutManager(this)
        val kontakAdapter = KontakData(listKontak)
        recKontak.adapter = kontakAdapter
    }
}