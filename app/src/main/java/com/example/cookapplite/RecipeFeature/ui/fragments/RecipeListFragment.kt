package com.example.cookapplite.RecipeFeature.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.helper.widget.Carousel
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cookapplite.R
import com.example.cookapplite.RecipeFeature.domain.Recipe
import com.example.cookapplite.RecipeFeature.ui.NavigatorStates.RecipeListNavigatorStates
import com.example.cookapplite.RecipeFeature.ui.adapters.RecipeAdapter
import com.example.cookapplite.RecipeFeature.ui.viewmodel.RecipeListViewModel
import com.example.cookapplite.databinding.RecipeListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception
import kotlin.concurrent.thread

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    private val viewModel : RecipeListViewModel by viewModels()
    private lateinit var binding : RecipeListFragmentBinding
    private var recipeAdapter = RecipeAdapter()

    companion object {
        fun newInstance() = RecipeListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RecipeListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        setObservers()
        setupRecyclerView()
        viewModel.getRecipes()

        binding.addRecipeBtn.setOnClickListener {
            viewModel.goToAddRecipe()
        }
    }

    private fun setupRecyclerView(){
        recipeAdapter.clickLister = {recipe -> itemClickListener(recipe)}
        recipeAdapter.context = requireContext()
        with(binding){
            recipesRecycler.setHasFixedSize(true)
            recipesRecycler.layoutManager  = LinearLayoutManager(context)
            recipesRecycler.adapter = recipeAdapter
        }
    }

    private fun setObservers(){
        viewModel.navigation.observe(this) { handleNavigation(it) }
        viewModel.recipeList.observe(viewLifecycleOwner, Observer{ list ->
            recipeAdapter.setData(list)
        })
    }

    private fun handleNavigation(navigation: RecipeListNavigatorStates) {
        when (navigation) {
            is RecipeListNavigatorStates.toAddRecipeFragment -> {
                val action = RecipeListFragmentDirections.actionRecipeListFragmentToAddRecipeFragment(
                    Recipe()
                )
                findNavController().navigate(action)
            }
        }
    }

    private fun itemClickListener(recipe : Recipe){
        val action = RecipeListFragmentDirections.actionRecipeListFragmentToDetailRecipeFragment(recipe)
        findNavController().navigate(action)
    }

}