package com.example.pertemuan7_71190444

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class KontakData(val listKontak: ArrayList<ListRincianKontak>): RecyclerView.Adapter<KontakData.KontakHolder>() {
    class KontakHolder(val v: View): RecyclerView.ViewHolder(v){
        var kontak: ListRincianKontak? = null

        fun bindView(kontak: ListRincianKontak){
            this.kontak = kontak
            v.findViewById<TextView>(R.id.textName).text = "${kontak.nama}"
            v.findViewById<TextView>(R.id.textNumber).text = "${kontak.nomer}"
            v.findViewById<ImageView>(R.id.icon1).setImageResource(kontak.icon)
            v.setOnClickListener {
//                Toast.makeText(v.context, "${kontak?.nomer} - ${kontak?.nama}", Toast.LENGTH_LONG).show()
                val i = Intent(v.context, RincianKontak::class.java)
                i.putExtra("nama",kontak.nama)
                i.putExtra("nomor", kontak.nomer)
                i.putExtra("email", kontak.email)
                i.putExtra("ttl", kontak.ttl)
                v.context.startActivity(i)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KontakHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.icon_datakontak, parent, false)
        return KontakHolder(v)
    }

    override fun onBindViewHolder(holder: KontakHolder, position: Int) {
        holder.bindView(listKontak[position])
    }

    override fun getItemCount(): Int {
        return listKontak.size
    }
}