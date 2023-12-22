package com.example.expenseexam

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.expenseexam.data.Category

class CategoryViewModel : ViewModel() {
    private val _category: SnapshotStateList<Category> = mutableStateListOf(
        Category("Питание"),
        Category("Транспорт"),
        Category("Путешествия"),
    )

    val category: List<Category>
        get() = _category

    fun addCategory(category: Category) {
        _category.add(category)
    }

    fun removeCategory(category: Category) {
        _category.remove(category)
    }

}