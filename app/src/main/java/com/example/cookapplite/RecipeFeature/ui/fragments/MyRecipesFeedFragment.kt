package com.example.cookapplite.RecipeFeature.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookapplite.R
import com.example.cookapplite.RecipeFeature.domain.Recipe
import com.example.cookapplite.RecipeFeature.ui.NavigatorStates.RecipeListNavigatorStates
import com.example.cookapplite.RecipeFeature.ui.viewmodel.MyRecipesFeedViewModel
import com.example.cookapplite.databinding.ActivityLoginBinding
import com.example.cookapplite.databinding.FragmentMyRecipesFeedBinding
import com.mana.template.core.ui.viewstates.BaseViewState

class MyRecipesFeedFragment : Fragment() {

    private val viewModel: MyRecipesFeedViewModel by viewModels()
    private lateinit var binding : FragmentMyRecipesFeedBinding

    companion object {
        fun newInstance() = MyRecipesFeedFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyRecipesFeedBinding.inflate(inflater,container,false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        setObservers()

        

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.columnView -> {

                true
            }
            R.id.listView -> {

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setObservers(){
        with(viewModel){
            navigation.observe(viewLifecycleOwner, Observer { handleNavigation(it) })
            viewState.observe(viewLifecycleOwner, Observer { handleViewState(it) })
            recipeList.observe(viewLifecycleOwner, Observer {  })
        }
    }

    private fun handleNavigation(navigation: RecipeListNavigatorStates) {
        when (navigation) {
        }
    }

    private fun handleViewState(viewState : BaseViewState){
        when(viewState){
            is BaseViewState.Loading -> {

            }
            is BaseViewState.Idle -> {

            }
        }
    }

}