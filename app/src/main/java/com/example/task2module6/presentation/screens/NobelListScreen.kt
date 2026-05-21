package com.example.task2module6.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.task2module6.domain.model.NobelPrize
import com.example.task2module6.presentation.viewmodel.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NobelListScreen(
    onPrizeClick: (String, String) -> Unit,
    viewModel: NobelViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val selectedYear by viewModel.selectedYear.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()

    var yearExpanded by remember { mutableStateOf(false) }
    var categoryExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Нобелевские лауреаты") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            // Filters
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Year dropdown
                ExposedDropdownMenuBox(
                    expanded = yearExpanded,
                    onExpandedChange = { yearExpanded = it },
                    modifier = Modifier.weight(1f)
                ) {
                    OutlinedTextField(
                        value = selectedYear?.toString() ?: "Год",
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Год") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(yearExpanded) },
                        modifier = Modifier.menuAnchor().fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = yearExpanded,
                        onDismissRequest = { yearExpanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Все") },
                            onClick = { viewModel.setYear(null); yearExpanded = false }
                        )
                        viewModel.years.forEach { year ->
                            DropdownMenuItem(
                                text = { Text(year.toString()) },
                                onClick = { viewModel.setYear(year); yearExpanded = false }
                            )
                        }
                    }
                }

                // Category dropdown
                ExposedDropdownMenuBox(
                    expanded = categoryExpanded,
                    onExpandedChange = { categoryExpanded = it },
                    modifier = Modifier.weight(1f)
                ) {
                    val catIdx = CATEGORIES.indexOf(selectedCategory).coerceAtLeast(0)
                    OutlinedTextField(
                        value = CATEGORY_LABELS[catIdx],
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Категория") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(categoryExpanded) },
                        modifier = Modifier.menuAnchor().fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = categoryExpanded,
                        onDismissRequest = { categoryExpanded = false }
                    ) {
                        CATEGORIES.forEachIndexed { idx, cat ->
                            DropdownMenuItem(
                                text = { Text(CATEGORY_LABELS[idx]) },
                                onClick = { viewModel.setCategory(cat); categoryExpanded = false }
                            )
                        }
                    }
                }
            }

            when (val state = uiState) {
                is NobelUiState.Loading -> Box(Modifier.fillMaxSize(), Alignment.Center) {
                    CircularProgressIndicator()
                }
                is NobelUiState.Error -> Column(
                    Modifier.fillMaxSize(),
                    Arrangement.Center,
                    Alignment.CenterHorizontally
                ) {
                    Text("Ошибка: ${state.message}", color = MaterialTheme.colorScheme.error)
                    Spacer(Modifier.height(16.dp))
                    Button(onClick = { viewModel.loadPrizes() }) { Text("Повторить") }
                }
                is NobelUiState.Success -> LazyColumn(
                    contentPadding = PaddingValues(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.prizes) { prize ->
                        PrizeCard(prize = prize, onClick = {
                            onPrizeClick(prize.awardYear, prize.category)
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun PrizeCard(prize: NobelPrize, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = prize.awardYear,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                AssistChip(
                    onClick = {},
                    label = { Text(prize.category, style = MaterialTheme.typography.labelSmall) }
                )
            }
            if (prize.laureates.isNotEmpty()) {
                Text(
                    text = prize.laureates.joinToString(", ") { it.knownName },
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            if (prize.topMotivation.isNotEmpty()) {
                Text(
                    text = prize.topMotivation.take(100) + if (prize.topMotivation.length > 100) "…" else "",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
