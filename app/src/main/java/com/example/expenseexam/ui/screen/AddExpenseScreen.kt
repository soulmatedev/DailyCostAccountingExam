package com.example.expenseexam.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.expenseexam.CategoryViewModel
import com.example.expenseexam.ExpenseViewModel
import com.example.expenseexam.data.Expense

private const val NAVIGATE_TO_EXPENSE_SCREEN = "/expense-list"
private const val NAVIGATE_TO_ADD_CATEGORY_SCREEN = "/add-category"

private const val ADD_EXPENSE_BUTTON_TEXT = "Добавить"
private const val TEXT_FIELD_AMOUNT_LABEL = "Введите сумму"

private const val TEXT_BUTTON_ADD_CATEGORY_TEXT = "Добавить категорию"
private const val TEXT_BUTTON_SELECT_CATEGORY_TEXT = "Выберите категорию"


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExpenseScreen(
    categoryViewModel: CategoryViewModel,
    expenseViewModel: ExpenseViewModel,
    navController: NavController
) {

    var amount by remember {
        mutableStateOf("")
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    var selectedCategory by remember {
        mutableStateOf("")
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(60.dp, 180.dp)
    ) {
        TextField(
            value = amount,
            onValueChange = { amount = it },
            placeholder = {
                Text(
                    TEXT_FIELD_AMOUNT_LABEL,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .fillMaxWidth()
                ) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Box (
            modifier = Modifier
                .padding(top = 20.dp)
        ) {
            TextButton(
                onClick = { expanded = true },
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
            ) {
                Text(
                    selectedCategory.ifEmpty { TEXT_BUTTON_SELECT_CATEGORY_TEXT },
                    color = Color.Gray,
                    fontSize = 15.sp
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {expanded = false},
                modifier = Modifier
                    .fillMaxWidth(0.699f)) {
                categoryViewModel.category.forEach{
                        category ->
                    DropdownMenuItem(text = {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp)
                        )
                        {
                            Text(category.name, modifier = Modifier.weight(1f))
                            IconButton(
                                onClick = {
                                    categoryViewModel.removeCategory(category)
                                })
                            {
                                Icon(
                                    Icons.Rounded.Delete,
                                    contentDescription = null
                                )
                            }
                        }
                    }, onClick = {
                        selectedCategory = category.name
                        expanded = false
                    })
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    TextButton(
                        onClick = {
                            expanded = false
                            navController.navigate(NAVIGATE_TO_ADD_CATEGORY_SCREEN)
                        }) {
                        Text(TEXT_BUTTON_ADD_CATEGORY_TEXT)
                    }
                }
            }
        }
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
            enabled = amount.isNotEmpty() && selectedCategory.isNotEmpty(),
            onClick = {
                expenseViewModel.addExpense(Expense(amount, selectedCategory))
                navController.navigate(NAVIGATE_TO_EXPENSE_SCREEN)
            }
        ) {
            Text(ADD_EXPENSE_BUTTON_TEXT)
        }
    }
}