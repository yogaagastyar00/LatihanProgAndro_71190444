package id.ac.ukdw.pertemuan5_71190444

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity  : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val edtUsername: EditText = findViewById(R.id.edtUsername)
        val edtPassword = findViewById<EditText>(R.id.edtPassword)
        val edtLogin = findViewById<Button>(R.id.edtLogin)
        edtLogin.setOnClickListener{
            login(edtUsername.text.toString(), edtPassword.text.toString())
        }
    }

    fun login(username: String, password: String){
        if(password.equals("1234")){
            val i: Intent = Intent(this,MainActivity::class.java)
            i.putExtra("namauser", username)
            startActivity(i)

        }else{
            showMessage("Password Salah")
        }
    }
    fun showMessage(message: String){
        Toast.makeText(this,  message, Toast.LENGTH_LONG).show()
    }
}