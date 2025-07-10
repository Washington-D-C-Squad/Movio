package com.example.movio.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.designsystem.AppTheme
import com.example.designsystem.component.MovioButton
import com.example.designsystem.component.MovioIcon
import com.example.designsystem.R as DesignSystemR

@Composable
fun SearchHeader(
    searchQuery: String,
    onSearchChange: (String) -> Unit,
    onClear: () -> Unit,
    onSubmit: () -> Unit,
    onBack: () -> Unit = {},
    onFocus: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .padding(horizontal = AppTheme.spacing.large, vertical = AppTheme.spacing.medium)
    ) {
        Text(
            text = "Search",
            style = AppTheme.textStyle.headLine.largeBold18,
            color = AppTheme.colors.surfaceColor.onSurface,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    AppTheme.colors.surfaceColor.surfaceVariant,
                    RoundedCornerShape(AppTheme.radius.xLarge)
                )
                .padding(horizontal = AppTheme.spacing.medium, vertical = AppTheme.spacing.small)
        ) {
            MovioIcon(
                painter = painterResource(id = com.example.movio.R.drawable.clock), // Replace with your search icon drawable
                contentDescription = stringResource(id = DesignSystemR.string.search_content_description),
                tint = AppTheme.colors.surfaceColor.onSurfaceVariant,
                modifier = Modifier.size(AppTheme.spacing.large)
            )
            Spacer(modifier = Modifier.width(AppTheme.spacing.small))
            TextField(
                value = searchQuery,
                onValueChange = onSearchChange,
                placeholder = { Text("Search", color = AppTheme.colors.surfaceColor.onSurfaceVariant) },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedTextColor = AppTheme.colors.surfaceColor.onSurface,
                    unfocusedTextColor = AppTheme.colors.surfaceColor.onSurface
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp),
                textStyle = AppTheme.textStyle.body.medium14,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = { onSubmit() }
                )
            )
            MovioButton(
                onClick = onClear,
                modifier = Modifier.size(AppTheme.spacing.xLarge),
                color = AppTheme.colors.surfaceColor.surfaceVariant
            ) {
                MovioIcon(
                    painter = painterResource(id = com.example.movio.R.drawable.delete), // Replace with your close icon drawable
                    contentDescription = stringResource(id = DesignSystemR.string.clear_content_description),
                    tint = AppTheme.colors.surfaceColor.onSurfaceVariant,
                    modifier = Modifier.size(AppTheme.spacing.large)
                )
            }
        }
    }
}