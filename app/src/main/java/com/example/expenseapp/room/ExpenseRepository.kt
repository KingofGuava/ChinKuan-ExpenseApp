package com.example.expenseapp.room

import androidx.lifecycle.LiveData

class ExpenseRepository(private val expenseDao: ExpenseDao) {

    val allExpenses: LiveData<List<Expense>> = expenseDao.getAllExpenses()

    suspend fun insert(expense: Expense) {
        expenseDao.insert(expense)
    }

    suspend fun update(expense: Expense) {
        expenseDao.update(expense)
    }

    fun getExpenseByCategory(category: String): LiveData<List<Expense>> {
        return expenseDao.getExpensesByCategory(category)
    }

    // 根据需要添加其他数据操作，例如删除和更新
}
