package com.example.expenseapp.room
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "expense_table")
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val date: String,
    val amount: Double,
    val category: String
)
