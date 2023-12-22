package com.example.expenseexam.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.expenseexam.CategoryViewModel
import com.example.expenseexam.ExpenseViewModel

private const val NAVIGATE_TO_EXPENSE_LIST_SCREEN = "/expense-list"

private const val BUTTON_COMEBACK_TEXT = "Вернуться обратно"
private const val CARD_RUB_EXPENSE_TEXT = " ₽ "


@Composable
fun ExpenseStatisticScreen(
    categoryViewModel: CategoryViewModel,
    expenseViewModel: ExpenseViewModel,
    navController: NavController
) {
    val category = categoryViewModel.category
    val expense = expenseViewModel.expense

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.DarkGray)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp, 100.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .background(Color.Transparent)
            ) {
                items(category) { category ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                    ) {
                        Column (
                            modifier = Modifier.padding(4.dp)
                        ) {
                            Text(text = category.name)
                            Text(text = expense.filter { it.category == category.name }
                                .sumOf { it.amount.toInt() }.toString() + CARD_RUB_EXPENSE_TEXT)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(horizontalArrangement = Arrangement.Center) {
                Button(modifier = Modifier
                    .fillMaxWidth(),
                    onClick = { navController.navigate(NAVIGATE_TO_EXPENSE_LIST_SCREEN)
                    }) {
                    Text(BUTTON_COMEBACK_TEXT)
                }
            }
        }
    }
}