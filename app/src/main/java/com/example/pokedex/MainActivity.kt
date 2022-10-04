package com.example.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.api.PokemonRepositorio
import com.example.pokedex.domain.Pokemon

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rvPokemon);
//
//        val Squirtle = Pokemon(
//            "https://www.pokemon.com/br/pokedex/squirtle", 1, "Squirtle",
//            listOf(PokemonType("Water"))
//        )
//
//
//        val pokemons = listOf(Squirtle,Squirtle,Squirtle,Squirtle,Squirtle)

        Thread(Runnable {
            loadPokemons(recyclerView)

        }).start()


    }

    private fun loadPokemons(


        recyclerView: RecyclerView

    ) {
        val pokemonsApiResult = PokemonRepositorio.listPokemons()

        pokemonsApiResult?.results?.let {
            val pokemons: List<Pokemon?> = it.map { pokemonResult ->
                val number = pokemonResult.url
                    .replace("https://pokeapi.co/api/v2/pokemon/", "")
                    .replace("/","").toInt()
                val pokemonApiResult = PokemonRepositorio.getPokemon(number)

                pokemonApiResult?.let {
                    Pokemon(
                        pokemonApiResult.id,
                        pokemonApiResult.name,
                        pokemonApiResult.types.map { type ->
                            type.type
                        }
                    )
                }
            }

            val layoutManager = LinearLayoutManager(this)

            recyclerView.post {
                recyclerView.layoutManager = layoutManager;
                recyclerView.adapter = PokemonAdapter(pokemons)
            }
        }

    }

}

