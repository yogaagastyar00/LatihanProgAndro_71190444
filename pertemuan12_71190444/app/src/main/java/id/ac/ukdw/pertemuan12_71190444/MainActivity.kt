package id.ac.ukdw.pertemuan12_71190444

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val edtKota = findViewById<EditText>(R.id.namaKota)
        val btnCari = findViewById<Button>(R.id.cekCuaca)
        btnCari.setOnClickListener {
            cekCuaca(edtKota.text.toString())
        }
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    private fun cekCuaca(kota: String) {
        val txtresult = findViewById<TextView>(R.id.kolomHasil)
        val queue = Volley.newRequestQueue(this)
        val url = "https://api.openweathermap.org/data/2.5/forecast?q=${kota}&lang=id&&APPID=0cf87b48cb5fbd97521a181a5e1dc2ea"
        val request = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                val weatherjson = JSONObject(response).getJSONArray("list")
                var str = ""
                val cuacahariini = weatherjson.getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("description")
                val cuacahariesok = weatherjson.getJSONObject(9).getJSONArray("weather").getJSONObject(0).getString("description")
                val cuacaharilusa = weatherjson.getJSONObject(17).getJSONArray("weather").getJSONObject(0).getString("description")
                str += "Cuaca di $kota Sekarang : $cuacahariini\nCuaca di $kota Besok : $cuacahariesok\nCuaca di $kota Lusa : $cuacaharilusa"
                txtresult.text = str
            },
            {
                txtresult.text = "Tidak Ada Nama Kota $kota"
            }
        )
        queue.add(request)
    }
}