package com.example.strangeplace

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvPlaces: RecyclerView
    private val list = ArrayList<Places>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvPlaces = findViewById(R.id.rv_places)
        rvPlaces.setHasFixedSize(true)
        list.addAll(getListPlace())
        showRecyclerList()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val iconSizeInPx = 60
        val iconBitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.icon_profile)
        val scaledIcon = Bitmap.createScaledBitmap(iconBitmap, iconSizeInPx, iconSizeInPx, false)
        val iconDrawable = BitmapDrawable(resources, scaledIcon)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(iconDrawable)
            setHomeButtonEnabled(true)
        }
    }

    private fun getListPlace(): ArrayList<Places> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataCoordinate = resources.getStringArray(R.array.data_coordinate)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listPlace = ArrayList<Places>()
        for (i in dataName.indices) {
            val hero = Places(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1), dataCoordinate[i], )
            listPlace.add(hero)
        }
        return listPlace
    }

    private fun showRecyclerList() {
        rvPlaces.layoutManager = LinearLayoutManager(this)
        val listPlaceAdapter = ListPlaceAdapter(list)
        rvPlaces.adapter = listPlaceAdapter

        listPlaceAdapter.setOnItemClickCallback(object : ListPlaceAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Places) {
                val intent = Intent(this@MainActivity, Detail::class.java)
                intent.putExtra(Detail.ITEM_EXTRA, data)
                startActivity(intent)
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Tindakan yang akan dijalankan saat ikon ActionBar diklik
                val intent = Intent(this@MainActivity, About::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}