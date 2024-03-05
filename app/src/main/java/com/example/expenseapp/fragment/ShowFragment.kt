package com.example.expenseapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expenseapp.Constants.Companion.TYPE_SHOW
import com.example.expenseapp.ExpenseListAdapter
import com.example.expenseapp.ExpenseListViewModel
import com.example.expenseapp.R
import com.example.expenseapp.databinding.FragmentShowBinding


private const val TAG = "ShowFragment"


class ShowFragment : Fragment() {

    private var _binding: FragmentShowBinding? = null

    private val binding

        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }
    private lateinit var expenseListViewModel: ExpenseListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate( savedInstanceState)
        //Log.d(TAG, "Total articles: ${articleListViewModel.articles.size()}")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        expenseListViewModel = ViewModelProvider(this).get(ExpenseListViewModel::class.java)


        // Inflate the layout for this fragment
        _binding = FragmentShowBinding.inflate(inflater, container, false)
        binding.buttonShowToAdd.setOnClickListener {
            it.findNavController().navigate(R.id.action_showFragment_to_addFragment)
        }



        binding.expenseRecyclerView.layoutManager = LinearLayoutManager(context)
        val expenses = expenseListViewModel.expenses
        //val  adapter = CrimeListAdapter(crimes)
        val  adapter = ExpenseListAdapter()
        expenseListViewModel.expenses.observe(viewLifecycleOwner, Observer { expenses ->
            // Update the cached copy of the expenses in the adapter.
            expenses?.let {
                adapter.setExpenses(it)
            }
        })
        adapter.setCurrentFragmentType(TYPE_SHOW)
        binding.expenseRecyclerView.adapter = adapter

//        articleListViewModel.articles.observe(viewLifecycleOwner, Observer { articles ->
//            adapter.setArticles(articles)
//        })


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}