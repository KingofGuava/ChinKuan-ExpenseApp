package com.example.expenseapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.expenseapp.room.Expense
import com.example.expenseapp.room.ExpenseDatabase
import com.example.expenseapp.room.ExpenseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExpenseListViewModel(application: Application) : AndroidViewModel(application) {


    private val repository: ExpenseRepository
    // Assume you have an instance of the database already created
    val expenses: LiveData<List<Expense>>

    init {
        val expenseDao = ExpenseDatabase.getDatabase(application).expenseDao()
        repository = ExpenseRepository(expenseDao)
        expenses = repository.allExpenses
    }

    fun insert(expense: Expense) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(expense)
    }

    fun update(expense: Expense) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(expense)
    }

    fun getExpenseByCategory(category: String): LiveData<List<Expense>> {
        return repository.getExpenseByCategory(category)
    }

    //val expenses = mutableListOf<Expense>()

//    init {
//        for (i in 0 until 100) {
//            if(i % 3 == 0 || i % 7 == 0){
//                val expense = Expense(
//                    date = "Day #$i",
//                    amount = i.toDouble() * 100,
//                    category = "Food",
//                )
//                expenses += expense
//            }
//            else{
//                val expense = Expense(
//                    date = "Day #$i",
//                    amount = i.toDouble() * 100,
//                    category = "Clothes",
//                )
//                expenses += expense
//            }
//        }
//    }
}
