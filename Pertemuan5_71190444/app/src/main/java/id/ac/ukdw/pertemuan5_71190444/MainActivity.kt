package id.ac.ukdw.pertemuan5_71190444

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class  MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val username = intent.getStringExtra("namauser")
        val SDUser = findViewById<TextView>(R.id.SDUser)
        SDUser.text = "Selamat Datang ${username}"


        val edtlogout = findViewById<Button>(R.id.edtlogout)
        edtlogout.setOnClickListener{
            val i= Intent(this, LoginActivity::class.java)
            startActivity(i)
        }
    }
}