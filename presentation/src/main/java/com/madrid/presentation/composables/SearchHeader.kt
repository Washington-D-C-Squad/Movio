package com.madrid.presentation.composables

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.madrid.designSystem.AppTheme
import com.madrid.designSystem.component.MovioButton
import com.madrid.designSystem.component.MovioIcon
import com.madrid.designSystem.component.MovioText
import androidx.compose.foundation.text.BasicTextField
import com.madrid.designSystem.R as DesignSystemR

@Composable
fun SearchHeader(
    searchQuery: String,
    onSearchChange: (String) -> Unit,
    onClear: () -> Unit,
    onSubmit: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        MovioText(
            text = "Search",
            textStyle = AppTheme.textStyle.headLine.largeBold18,
            color = AppTheme.colors.surfaceColor.onSurface,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    AppTheme.colors.surfaceColor.surfaceVariant,
                    RoundedCornerShape(24.dp)
                )
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            MovioIcon(
                painter = painterResource(id = DesignSystemR.drawable.outline_clock_circle),
                contentDescription ="search content description",// stringResource(id = DesignSystemR.string.search_content_description),
                tint = AppTheme.colors.surfaceColor.onSurfaceVariant,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            BasicTextField(
                value = searchQuery,
                onValueChange = onSearchChange,
                singleLine = true,
                textStyle = AppTheme.textStyle.body.medium14.copy(color = AppTheme.colors.surfaceColor.onSurface),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = { onSubmit() }
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp),
                decorationBox = { innerTextField ->
                    if (searchQuery.isEmpty()) {
                        MovioText(
                            text = "Search",
                            textStyle = AppTheme.textStyle.body.medium14,
                            color = AppTheme.colors.surfaceColor.onSurfaceVariant
                        )
                    }
                    innerTextField()
                }
            )
            MovioButton(
                onClick = onClear,
                modifier = Modifier.size(24.dp),
                color = AppTheme.colors.surfaceColor.surfaceVariant
            ) {
                MovioIcon(
                    painter = painterResource(id = DesignSystemR.drawable.trash),
                    contentDescription = "clear content description ",//stringResource(id = DesignSystemR.string.clear_content_description),
                    tint = AppTheme.colors.surfaceColor.onSurfaceVariant,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}