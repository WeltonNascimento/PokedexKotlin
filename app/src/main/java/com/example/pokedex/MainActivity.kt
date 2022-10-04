package com.example.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.api.PokemonRepositorio
import com.example.pokedex.domain.Pokemon
import com.example.pokedex.domain.PokemonType

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rvPokemon);

        val Squirtle = Pokemon(
            "https://www.pokemon.com/br/pokedex/squirtle", 1, "Squirtle",
            listOf(
                PokemonType("Water")
            )
        )
        val pokemons = listOf(Squirtle,Squirtle,Squirtle,Squirtle,Squirtle)
        val pokemonsApi = PokemonRepositorio.listPokemons()

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager;
        recyclerView.adapter = PokemonAdapter(pokemons)
    }
}