package com.example.expenseexam.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.expenseexam.ExpenseViewModel

private const val NAVIGATE_TO_ADD_EXPENSE_SCREEN = "/add-expense"
private const val NAVIGATE_TO_EXPENSE_STATISTIC_SCREEN = "/expense-statistic"
private const val NAVIGATE_TO_EXPENSE_LIST_SCREEN = "/expense-list"

private const val BUTTON_ADD_EXPENSE_TEXT = "Добавить расход"
private const val BUTTON_EXPENSE_STATISTIC_TEXT = "Статистика рассходов"
private const val CARD_SUM_EXPENSE_TEXT = "Сумма: "
private const val CARD_CATEGORY_EXPENSE_TEXT = "Категория: "
private const val CARD_RUB_EXPENSE_TEXT = " ₽ "

@Composable
fun ExpenseListScreen(
    expenseViewModel: ExpenseViewModel,
    navController: NavController
) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        )
        {
            JokeScreen()

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize(0.9f)
                    .verticalScroll(rememberScrollState())
            ) {
                expenseViewModel.expense.forEach { expense ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(4.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp, 0.dp, 0.dp, 0.dp)
                        )
                        {
                            Text(CARD_SUM_EXPENSE_TEXT + expense.amount + CARD_RUB_EXPENSE_TEXT)
                            Text(CARD_CATEGORY_EXPENSE_TEXT + expense.category)
                            IconButton(onClick = {
                                expenseViewModel.removeExpense(expense)
                                navController.navigate(NAVIGATE_TO_EXPENSE_LIST_SCREEN)
                            }
                            ) {
                                Icon(
                                    Icons.Rounded.Delete,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier
                        .fillMaxWidth(),
                        onClick = { navController.navigate(NAVIGATE_TO_ADD_EXPENSE_SCREEN) }
                    ) {
                        Text(BUTTON_ADD_EXPENSE_TEXT)
                    }
                }

                Row(horizontalArrangement = Arrangement.Center) {
                    Button(modifier = Modifier
                        .fillMaxWidth(),
                        onClick = { navController.navigate(NAVIGATE_TO_EXPENSE_STATISTIC_SCREEN) }) {
                        Text(BUTTON_EXPENSE_STATISTIC_TEXT)
                    }
                }

            }
        }
    }