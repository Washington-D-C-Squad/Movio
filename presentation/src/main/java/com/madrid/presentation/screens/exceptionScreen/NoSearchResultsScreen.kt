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
fun NoSearchResultsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.color.surfaces.surfaceContainer),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MovioIcon(
            painter = painterResource(id = com.madrid.designSystem.R.drawable.no_internet),
            contentDescription = null,
            modifier = Modifier.size(width = 180.dp, height = 150.dp),
            tint = Theme.color.brand.primary
        )
        Spacer(modifier = Modifier.height(32.dp))
        MovioText(
            text = "No results found",
            color = Theme.color.surfaces.onSurface,
            textStyle = Theme.textStyle.title.mediumMedium16
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(modifier = Modifier.width(328.dp)) {
            MovioText(
                text = "We couldn't find anything matching your search. Try checking the spelling or explore something else!",
                color = Theme.color.surfaces.onSurfaceContainer,
                textStyle = Theme.textStyle.label.smallRegular12,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun lk(){
    NoSearchResultsScreen()
}