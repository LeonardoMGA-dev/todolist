package com.sample.android.todolistapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sample.android.todolistapp.R
import com.sample.android.todolistapp.domain.entity.TodoEntity
import com.sample.android.todolistapp.presentation.viewmodel.TodoListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    viewModel: TodoListViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsState()
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Row {
                        Text(
                            text = stringResource(id = R.string.top_bar_title),
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.spacing_small)))
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_check_circle_24),
                            contentDescription = null,
                            tint = Color.Green
                        )
                    }
                }
            )
        }
    ) {
        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(id = R.dimen.spacing_medium))
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            return@Scaffold
        }
        if (uiState.error.isNotBlank()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(id = R.dimen.spacing_medium))
            ) {
                Text(
                    text = uiState.error,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            return@Scaffold
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(
                    horizontal = dimensionResource(id = R.dimen.spacing_medium)
                )
        ) {
            LazyColumn {
                items(uiState.todoList) { todo ->
                    TodoItem(todo)
                }
            }
        }
    }
}

@Composable
private fun TodoItem(todoEntity: TodoEntity) {
    Row(
        Modifier
            .shadow(20.dp, MaterialTheme.shapes.medium)
            .padding(vertical = dimensionResource(id = R.dimen.spacing_small))
            .fillMaxWidth()
            .background(color = Color.White, shape = MaterialTheme.shapes.medium)
            .padding(dimensionResource(id = R.dimen.spacing_medium))
    ) {
        val tint = if (todoEntity.completed) {
            Color.Green
        } else {
            Color.Gray
        }
        Icon(
            painter = painterResource(id = R.drawable.baseline_check_circle_24),
            contentDescription = null,
            tint = tint
        )
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.spacing_small)))
        Text(
            text = todoEntity.title
        )
    }
}