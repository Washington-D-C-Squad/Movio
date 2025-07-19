package com.madrid.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.madrid.designsystem.AppTheme

@Composable
fun LoadingSearchCard(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier.background(AppTheme.colors.surfaceColor.surface),
    ) {

        Box(
            modifier = modifier
                .width(101.dp)
                .height(136.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(AppTheme.colors.surfaceColor.surfaceContainer),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = modifier
                .width(101.dp)
                .height(15.dp)
                .clip(RoundedCornerShape(32.dp))
                .background(AppTheme.colors.surfaceColor.surfaceContainer),
        )
    }
}
