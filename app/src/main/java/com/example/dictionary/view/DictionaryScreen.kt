package com.example.dictionary.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dictionary.model.DictionaryData
import com.example.dictionary.model.Meaning
import com.example.dictionary.viewmodel.DictionaryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DictionaryScreen(
    viewModel: DictionaryViewModel = viewModel()
) {
    var searchQuery by remember { mutableStateOf("") }
    var lastSearchedWord by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    
    var hasSearched by remember { mutableStateOf(false) }

    fun onSearchClicked() {
        val query = searchQuery.trim()
        if (query.isBlank()) return
        
        lastSearchedWord = query
        hasSearched = true
        focusManager.clearFocus()
        
        viewModel.searchWord(query)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "MY DICTIONARY",
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Search for a word...") },
                trailingIcon = {
                    IconButton(onClick = { onSearchClicked() }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                },
                singleLine = true,
                shape = MaterialTheme.shapes.large,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = { onSearchClicked() }),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Content Area
            Box(modifier = Modifier.fillMaxSize()) {
                if (viewModel.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                } else {
                    // this is just like viewmodel.worddata != null
                    viewModel.wordData?.let { data ->
                        WordDataContent(data)
                    }
                        // this is like else of viewmodel.worddata != null which is iff word is null.
                        ?: run {
                        if (hasSearched) {
                            NotFoundView(
                                word = lastSearchedWord,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        } else {
                            WelcomeView(modifier = Modifier.align(Alignment.Center))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WordDataContent(data: DictionaryData) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        item {
            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                Text(
                    text = data.word.lowercase().replaceFirstChar { it.uppercase() },
                    fontSize = 42.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primary
                )
                data.phonetic?.let {
                    Text(
                        text = it,
                        fontSize = 20.sp,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 2.dp, 
                color = MaterialTheme.colorScheme.outlineVariant
            )
        }

        items(data.meanings) { meaning ->
            MeaningCard(meaning)
        }
    }
}

@Composable
fun MeaningCard(meaning: Meaning) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = meaning.partOfSpeech.lowercase().replaceFirstChar { it.uppercase() },
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.tertiary
            )
            
            Spacer(modifier = Modifier.height(8.dp))

            meaning.definitions.forEachIndexed { index, definition ->
                Column(modifier = Modifier.padding(vertical = 6.dp)) {
                    Text(
                        text = "${index + 1}. ${definition.definition}",
                        style = MaterialTheme.typography.bodyLarge,
                        lineHeight = 24.sp
                    )
                    definition.example?.let {
                        Surface(
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
                            shape = MaterialTheme.shapes.small,
                            modifier = Modifier.padding(top = 4.dp, start = 8.dp)
                        ) {
                            Text(
                                text = "\"$it\"",
                                style = MaterialTheme.typography.bodyMedium,
                                fontStyle = FontStyle.Italic,
                                color = MaterialTheme.colorScheme.outline,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WelcomeView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            Icons.Default.MenuBook,
            contentDescription = null,
            modifier = Modifier.size(100.dp),
            tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Ready to Learn?",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.outline
        )
        Text(
            "Search for any word above",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.7f)
        )
    }
}

@Composable
fun NotFoundView(word: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            Icons.Default.SearchOff,
            contentDescription = null,
            modifier = Modifier.size(80.dp),
            tint = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Word Not Found",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "We couldn't find any results for \"$word\".",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.outline
        )
        Text(
            text = "Please check the spelling or try a different word.",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.7f),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
