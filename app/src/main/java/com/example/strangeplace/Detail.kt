package com.example.strangeplace

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
class Detail : AppCompatActivity() {
    companion object {
        const val ITEM_EXTRA = "item_extra"
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val imgDetail: ImageView = findViewById(R.id.img_item_photo)
        val tvDetailName: TextView = findViewById(R.id.tv_item_name)
        val tvDetailDescription: TextView = findViewById(R.id.tv_item_description)
        val tvDetailCoor: TextView = findViewById(R.id.coordinate)

        val place = intent.getParcelableExtra(ITEM_EXTRA) ?: Places(name ="place", description ="place", photo = 1, coordinate = "1111" )
        if (place !== null) {
            imgDetail.setImageResource(place.getPhotoCustom())
            tvDetailName.text = place.getNameCustom()
            tvDetailDescription.text = place.getDescriptionCustom()
            tvDetailCoor.text = place.getCoordinateCustom()
        }

        supportActionBar?.apply {
            title = "Detail Page"
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
