package com.example.expenseapp

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expenseapp.Constants.Companion.TYPE_ADD
import com.example.expenseapp.Constants.Companion.TYPE_EDIT
import com.example.expenseapp.Constants.Companion.TYPE_SHOW
import com.example.expenseapp.databinding.ListItemExpenseBinding
import com.example.expenseapp.room.Expense



class ShowExpenseHolder(private val binding: ListItemExpenseBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(expense: Expense) {

        Log.d("HOLDER", "Binding expense: ${expense.date}")
        binding.dateNumber.text = expense.date
        binding.amountNumber.text = expense.amount.toString()
        binding.categoryNumber.text = expense.category
    }
}

//class BusinessArticleHolder(private val binding: ListItemArticleBinding) :
//    RecyclerView.ViewHolder(binding.root) {
//    fun bind(article: Article) {
//        binding.articleTitle.text = article.title
//        binding.articleDate.text = article.publishedAt
//
//        binding.root.setOnClickListener { view ->
//            // Navigate using the action defined in the navigation graph
//
//            val action = BusinessFragmentDirections.actionBusinessFragmentToBcontentFragment(article.title, article.content)
//            view.findNavController().navigate(action)
//        }
//    }
//}




//private val articles: LiveData<List<Article>>
//private val expensess: List<Expense>
class ExpenseListAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    //var list = ArrayList<Article>()
    private var expenses: List<Expense> = emptyList()
    private var currentFragmentType: Int = TYPE_SHOW


//    fun setArticles(articles: List<Article>) {
//        Log.d("ADAPTER", "Updating articles in adapter: ${articles.size}")
//        this.articles = articles
//        notifyDataSetChanged()
//    }
    fun setCurrentFragmentType(type: Int) {
        currentFragmentType = type
        notifyDataSetChanged() // 通知数据变化，重新绑定 ViewHolder
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemExpenseBinding.inflate(inflater, parent, false)
        return when (currentFragmentType) {
            TYPE_SHOW -> {
                val binding = ListItemExpenseBinding.inflate(inflater, parent, false)
                ShowExpenseHolder(binding)
            }
            TYPE_ADD -> {
                val binding = ListItemExpenseBinding.inflate(inflater, parent, false)
                //BusinessArticleHolder(binding)
                ShowExpenseHolder(binding)
            }
            TYPE_EDIT -> {
                val binding = ListItemExpenseBinding.inflate(inflater, parent, false)
                //SportsArticleHolder(binding)
                ShowExpenseHolder(binding)
            }
            else -> throw IllegalStateException("Unsupported fragment type")
        }
        //return HeadlineArticleHolder(binding)
//        if(viewType == 1){
//            return HeadlineArticleHolder(binding)
//        }
//        else if(viewType == 2) {
//            return HeadlineArticleHolder(binding)
//            //return BusinessArticleHolder(binding)
//        }
//        else {
//            return SportsArticleHolder(binding)
//        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val expense = expenses[position]

        if(holder is ShowExpenseHolder){
            holder.bind(expense)
        }
//        else if(holder is AddExpenseHolder){
//            holder.bind(expense)
//        }
//        else if(holder is SportsArticleHolder){
//            holder.bind(expense)
//        }

    }
    fun setExpenses(expenses: List<Expense>) {
        this.expenses = expenses
        notifyDataSetChanged()
    }

//    override fun getItemViewType(position: Int): Int {
//        return articles[position].viewType
//    }

    override fun getItemCount() = expenses.size
}