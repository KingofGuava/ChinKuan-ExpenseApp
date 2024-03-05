package com.example.expenseapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
@Dao
interface ExpenseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(expense: Expense)

    @Update
    suspend fun update(expense: Expense)

    @Query("SELECT * FROM expense_table ORDER BY date DESC")
    fun getAllExpenses(): LiveData<List<Expense>>

    @Query("SELECT * FROM expense_table WHERE category = :category ORDER BY date DESC")
    fun getExpensesByCategory(category: String): LiveData<List<Expense>>

}
