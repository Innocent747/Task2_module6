package com.example.task2module6.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.task2module6.domain.model.NobelPrize
import com.example.task2module6.presentation.viewmodel.NobelUiState
import com.example.task2module6.presentation.viewmodel.NobelViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NobelDetailScreen(
    year: String,
    category: String,
    onBack: () -> Unit,
    viewModel: NobelViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(year, category) {
        viewModel.setYear(year.toIntOrNull())
        viewModel.setCategory(category)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Детали премии") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Назад")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            when (val state = uiState) {
                is NobelUiState.Loading -> CircularProgressIndicator(Modifier.align(Alignment.Center))
                is NobelUiState.Error -> Text(
                    "Ошибка: ${state.message}",
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.error
                )
                is NobelUiState.Success -> {
                    val prize = state.prizes.find { it.awardYear == year && it.category == category }
                        ?: state.prizes.firstOrNull()
                    if (prize != null) {
                        PrizeDetail(prize = prize)
                    } else {
                        Text("Данные не найдены", modifier = Modifier.align(Alignment.Center))
                    }
                }
            }
        }
    }
}

@Composable
fun PrizeDetail(prize: NobelPrize) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            AssistChip(onClick = {}, label = { Text(prize.awardYear) })
            AssistChip(onClick = {}, label = { Text(prize.categoryFullName) })
        }

        if (prize.topMotivation.isNotEmpty()) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text("Обоснование", style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary)
                    Spacer(Modifier.height(4.dp))
                    Text(prize.topMotivation, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }

        if (prize.laureates.isNotEmpty()) {
            Text("Лауреаты", style = MaterialTheme.typography.titleMedium)
            prize.laureates.forEach { laureate ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(laureate.fullName, style = MaterialTheme.typography.bodyLarge)
                        if (laureate.motivation.isNotEmpty()) {
                            Spacer(Modifier.height(4.dp))
                            Text(
                                laureate.motivation,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        if (prize.laureates.size > 1) {
                            Text(
                                "Доля: ${laureate.portion}",
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                }
            }
        }
    }
}
