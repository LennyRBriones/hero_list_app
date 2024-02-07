package com.example.herolistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.herolistapp.databinding.ActivityDetailSuperheroBinding
import com.example.herolistapp.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailSuperheroActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityDetailSuperheroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSuperheroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()
        getSuperheroInformation(id)

    }

    private fun getSuperheroInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val superheroDetail =
                getRetrofit().create(ApiService::class.java).getSuperHeroDetail(id)

            if(superheroDetail.body() != null){
                runOnUiThread{ createUI(superheroDetail.body()!!)}

            }
        }

    }
    private fun createUI(superhero: SuperHeroDetailResponse) {
        Picasso.get().load(superhero.image.url).into(binding.ivSuperhero)
        binding.tvsuperheroname.text = superhero.name
        prepareStats(superhero.powerstats)

    }

    private fun prepareStats(powerstats: PowerStatsResponse){
        updateHeight(binding.viewCombat, powerstats.combat.toInt())
        updateHeight(binding.viewDurability, powerstats.durability.toInt())
        updateHeight(binding.viewSpeed, powerstats.speed.toInt())
        updateHeight(binding.viewPower, powerstats.power.toInt())
        updateHeight(binding.viewStrength, powerstats.strength.toInt())
        updateHeight(binding.viewIntelligence, powerstats.intelligence.toInt())

    }

    private fun updateHeight(view: View, stat:Int){
        val params = view.layoutParams
        params.height = stat
        view.layoutParams = params
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}