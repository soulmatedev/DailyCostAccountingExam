package com.example.expenseexam

import androidx.lifecycle.ViewModel
import com.example.expenseexam.data.Expense

class ExpenseViewModel : ViewModel() {
    private val _expense = mutableListOf<Expense>()

    val expense: List<Expense>
        get() = _expense

    fun addExpense(expense: Expense) {
        _expense.add(expense)
    }

    fun removeExpense(expense: Expense) {
        _expense.remove(expense)
    }
}