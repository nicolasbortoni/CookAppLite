package com.example.cookapplite.RecipeFeature.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.cookapplite.R
import com.example.cookapplite.RecipeFeature.domain.Recipe
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.cookapplite.databinding.ItemRecipeBinding
import kotlin.properties.Delegates


class RecipeAdapter (): RecyclerView.Adapter<RecipeAdapter.RecipeHolder>(){

    var items: List<Recipe> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }
    lateinit var clickLister : ()-> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeAdapter.RecipeHolder {
        val binding = ItemRecipeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return RecipeHolder(binding)
    }

    fun setData (data:MutableList<Recipe>){
        this.items = data
    }

    fun setItemClickListener (f : ()-> Unit){
        clickLister = f
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecipeAdapter.RecipeHolder, position: Int) {

        with(holder){
            setTitle(items[position].title!!)
            setDescription(items[position].description!!)
            setAuthor(items[position].author!!)
//            getCardLayout().setOnClickListener {
//                clickLister()
//            }
        }

    }

    class RecipeHolder (binding: ItemRecipeBinding ) : RecyclerView.ViewHolder(binding.cardRecipe) {

        val binding : ItemRecipeBinding = binding

        fun setTitle(name : String) {
            binding.cardTitle.text = name
        }

        fun setDescription (text : String){
            binding.cardDescription.text = text
        }

        fun setAuthor (text : String){
            binding.cardAuthor.text = text
        }

        fun getCardLayout () : CardView {
            return binding.cardRecipe
        }

    }


}