package com.example.expenseapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Expense::class], version = 1, exportSchema = false)
abstract class ExpenseDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao

    companion object {
        @Volatile
        private var INSTANCE: ExpenseDatabase? = null

        fun getDatabase(context: Context): ExpenseDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExpenseDatabase::class.java,
                    "expense_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }

        public class ExpenseDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                INSTANCE?.let { database ->
                    scope.launch {
                        populateDatabase(database.expenseDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(expenseDao: ExpenseDao) {
            // 删除所有内容
            //expenseDao.deleteAll()

            // 添加初始数据
            var expense = Expense(date = "2021-01-01", amount = 50.0, category = "Food")
            expenseDao.insert(expense)
            expense = Expense(date = "2021-01-02", amount = 20.0, category = "Fuel")
            expenseDao.insert(expense)
            // 添加更多初始数据...
        }
    }
}
