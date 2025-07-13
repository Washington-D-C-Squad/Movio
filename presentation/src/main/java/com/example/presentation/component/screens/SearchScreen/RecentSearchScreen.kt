package com.example.presentation.component.screens.SearchScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.designsystem.component.search.RecentSearchHeader
import com.example.designsystem.component.search.RecentSearchList
import com.example.designsystem.component.textInputField.SearchTextInputField
import com.example.designsystem.AppTheme

@Composable
fun RecentSearchScreen() {
    val (searchText, setSearchText) = remember { mutableStateOf("") }
    val recentSearches = listOf(
        "Ana de Armas", "Ana de Armas", "Ana de Armas", "Ana de Armas",
        "Ana de Armas", "Ana de Armas", "Ana de Armas", "Ana de Armas"
    )
    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(Color.White, RoundedCornerShape(24.dp))
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        SearchTextInputField(
            value = searchText,
            onValueChange = setSearchText,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        RecentSearchHeader(
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(4.dp))
        RecentSearchList(
            items = recentSearches,
            modifier = Modifier.fillMaxWidth()
        )
    }
}