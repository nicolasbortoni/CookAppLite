package com.example.cookapplite.RecipeFeature.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cookapplite.R
import com.example.cookapplite.RecipeFeature.ui.adapters.RecipeAdapter
import com.example.cookapplite.RecipeFeature.ui.viewmodel.RecipeListViewModel
import com.example.cookapplite.databinding.RecipeListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    private lateinit var viewModel: RecipeListViewModel
    private lateinit var binding : RecipeListFragmentBinding

    private lateinit var recipesRecyclerView : RecyclerView

    companion object {
        fun newInstance() = RecipeListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RecipeListFragmentBinding.inflate(inflater, container, false)
        recipesRecyclerView = binding.recipesRecycler
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        recipesRecyclerView.setHasFixedSize(true)
        recipesRecyclerView.layoutManager  = LinearLayoutManager(context)
        //recipesRecyclerView.adapter = RecipeAdapter()

        binding.addRecipeBtn.setOnClickListener {
            goToAddRecipe()
        }

    }

    private fun goToAddRecipe(){
        val action = RecipeListFragmentDirections.actionRecipeListFragmentToAddRecipeFragment()
        findNavController().navigate(action)
    }

}