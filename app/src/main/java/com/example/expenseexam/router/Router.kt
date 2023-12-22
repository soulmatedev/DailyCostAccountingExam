package com.example.expenseexam.router

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.expenseexam.CategoryViewModel
import com.example.expenseexam.ExpenseViewModel
import com.example.expenseexam.ui.screen.AddCategoryScreen
import com.example.expenseexam.ui.screen.AddExpenseScreen
import com.example.expenseexam.ui.screen.ExpenseListScreen
import com.example.expenseexam.ui.screen.ExpenseStatisticScreen

private const val NAV_HOST_START_DESTINATION = "/expense-list"

private const val NAV_HOST_NAVIGATE_TO_EXPENSE_LIST = "/expense-list"
private const val NAV_HOST_NAVIGATE_TO_ADD_EXPENSE = "/add-expense"
private const val NAV_HOST_NAVIGATE_TO_ADD_CATEGORY = "/add-category"
private const val NAV_HOST_NAVIGATE_TO_EXPENSE_STATISTIC = "/expense-statistic"

@Composable
fun Router () {
    val expenseViewModel = viewModel<ExpenseViewModel>()
    val categoryViewModel = viewModel<CategoryViewModel>()
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NAV_HOST_START_DESTINATION) {
        composable(NAV_HOST_NAVIGATE_TO_EXPENSE_LIST) {
            ExpenseListScreen(expenseViewModel, navController)
        }
        composable(NAV_HOST_NAVIGATE_TO_ADD_EXPENSE) {
            AddExpenseScreen(categoryViewModel, expenseViewModel, navController)
        }
        composable(NAV_HOST_NAVIGATE_TO_ADD_CATEGORY) {
            AddCategoryScreen(categoryViewModel, navController)
        }
        composable(NAV_HOST_NAVIGATE_TO_EXPENSE_STATISTIC) {
            ExpenseStatisticScreen(categoryViewModel, expenseViewModel ,navController)
        }
    }
}