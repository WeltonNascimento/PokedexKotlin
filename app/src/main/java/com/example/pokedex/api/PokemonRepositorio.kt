package com.example.pokedex.api

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.api.model.PokemonApiResult
import com.example.pokedex.api.model.PokemonsApiResult
import com.example.pokedex.domain.Pokemon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object  PokemonRepositorio  {
    private val service : PokemonService
    // https://pokeapi.co/api/v2/pokemon/?limit=151

   init {
       val retrofit = Retrofit.Builder()
           .baseUrl("https://pokeapi.co/api/v2/")
           .addConverterFactory(GsonConverterFactory.create())
           .build()

       service = retrofit.create(PokemonService::class.java)

   }
    fun listPokemons (limit: Int = 151): PokemonsApiResult? {
        val call = service.listPokemons(limit)

        return call.execute().body()
    }

    fun getPokemon (number: Int): PokemonApiResult? {
         val call = service.getPokemon(number)

        return call.execute().body()
 }

        //jeito mais "dificil"

//        call.enqueue(object  : Callback<PokemonsApiResult>{
//            override fun onResponse(call: Call<PokemonsApiResult>,
//                response: Response<PokemonsApiResult>)
//             {
//                 if (response.isSuccessful){
//                     val body = response.body()
//                      body?.results?.let{
//                          Log.d("POKEMON_API", it[0].name)
//                      }
//                 }
//
//                Log.e("POKEMON_API", "Pokemons Lista Carregado")
//
//            }
//
//            override fun onFailure(call: Call<PokemonsApiResult>, t: Throwable) {
//                Log.e("POKEMON_API","Error ao carregar pokemons list",t)
//
//            }
//        })

    }
