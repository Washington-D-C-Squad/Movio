package com.example.movio.component


import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movio.R

@Composable
fun RecentSearchList(
    modifier: Modifier = Modifier,
    searchHistory: List<String> = emptyList(),
    onSearchItemClick: (String) -> Unit = {},
    onRemoveItem: (String) -> Unit = {},
    onClearAll: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(Color.White, RoundedCornerShape(12.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Recent Search",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
                Text(
                    text = "Clear all",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.clickable { onClearAll() }
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(searchHistory) { searchItem ->
                    RecentSearchItem(
                        searchText = searchItem,
                        onItemClick = { onSearchItemClick(searchItem) },
                        onRemoveClick = { onRemoveItem(searchItem) }
                    )
                }
            }
        }
    }
}

@Composable
private fun RecentSearchItem(
    searchText: String,
    onItemClick: () -> Unit,
    onRemoveClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = painterResource(id = R.drawable.clock),
            contentDescription = "Clock",
            tint = Color.Gray,
            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = searchText,
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.weight(1f)
        )

        IconButton(
            onClick = onRemoveClick,
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.delete),
                contentDescription = "Delete",
                tint = Color.Gray,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}