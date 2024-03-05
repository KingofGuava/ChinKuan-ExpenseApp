package com.example.expenseapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.expenseapp.room.ExpenseDatabase
import android.app.Application
import androidx.room.Room

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    val applicationScope = CoroutineScope(Dispatchers.Default)

    val database: ExpenseDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            ExpenseDatabase::class.java, "ExpenseDatabase"
        ).addCallback(ExpenseDatabase.Companion.ExpenseDatabaseCallback(applicationScope)).build()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onSupportNavigateUp(): Boolean {
        navController = findNavController(R.id.navHostFragmentContainerView)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}

