package com.example.cookapplite.RecipeFeature.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.cookapplite.R
import com.example.cookapplite.RecipeFeature.domain.Recipe
import android.view.View
import android.widget.TextView


class RecipeAdapter (
    var recipeList : MutableList <Recipe>
) : RecyclerView.Adapter<RecipeAdapter.RecipeHolder>() {

    class RecipeHolder (v: View) : RecyclerView.ViewHolder(v) {
        private var view: View
        init {
            this.view = v
        }
        fun setTitle (title : String){
            var txtTitle : TextView = view.findViewById(R.id.cardTitle)
            txtTitle.text = title
        }

        fun getCardView () : CardView {
            return view.findViewById(R.id.cardRecipe)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.item_recipe,parent,false)
        return (RecipeHolder(view))
    }

    override fun onBindViewHolder(holder: RecipeHolder, position: Int) {
        holder.setTitle(recipeList[position].title!!)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

}