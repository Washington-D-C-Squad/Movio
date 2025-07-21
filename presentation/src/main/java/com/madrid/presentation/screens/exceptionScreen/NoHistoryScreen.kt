package com.madrid.presentation.screens.exceptionScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madrid.designSystem.component.MovioText
import com.madrid.designSystem.theme.Theme
import com.madrid.designSystem.component.MovioIcon

@Composable
fun NoHistoryScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.color.surfaces.surfaceContainer),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        MovioIcon(
            painter = painterResource(id = com.madrid.designSystem.R.drawable.not_found),
            contentDescription = null,
            modifier = Modifier.size(160.dp),
            tint = Theme.color.brand.primary
        )
        Spacer(modifier = Modifier.height(32.dp))
        MovioText(
            text = "No watch history yet",
            color = Theme.color.surfaces.onSurface,
            textStyle = Theme.textStyle.headline.mediumMedium18
        )
        Spacer(modifier = Modifier.height(8.dp))
        MovioText(
            text = "Start watching movies and shows, and we'll keep track of your viewing history here.",
            color = Theme.color.surfaces.onSurfaceVariant,
            textStyle = Theme.textStyle.body.smallRegular10
        )
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun lkdfg(){
    NoHistoryScreen()
}