package com.madrid.presentation.screens.detailsScreen.componant

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madrid.designSystem.theme.Theme
import com.madrid.designSystem.R
import com.madrid.designSystem.component.MovioBottomSheet
import com.madrid.designSystem.component.MovioButton
import com.madrid.designSystem.component.MovioIcon
import com.madrid.designSystem.component.MovioText
import com.madrid.designSystem.theme.MovioTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaveMovieBottomSheet(
    modifier: Modifier = Modifier,
    show: Boolean,
    onDismiss: () -> Unit,

) {

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    if (show){
        MovioBottomSheet(
            show = true,
            onDismiss = onDismiss,
            containerColor = Theme.color.surfaces.surface

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp, horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MovioIcon(
                    painter = painterResource(R.drawable.library_main_icon),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.width(60.dp)
                        .height(66.dp)
                )
                MovioText(
                    text = "You don't have an account ",
                    textStyle = Theme.textStyle.title.mediumMedium16,
                    color = Theme.color.surfaces.onSurface,
                    modifier = Modifier.padding( top = 16.dp, bottom = 8.dp)

                )
                MovioText(
                    text = "Please log in or create an account to save items to your favorites and access them later.",
                    textStyle = Theme.textStyle.label.smallRegular12,
                    color = Theme.color.surfaces.onSurfaceContainer,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 40.dp),
                    textAlign = TextAlign.Center
                )
                MovioButton(
                    color = Theme.color.brand.primary,
                    content = {MovioText(text = "login",textStyle = Theme.textStyle.label.mediumMedium16,
                        color = Theme.color.brand.onPrimary,)},
                    onClick = {},
                    modifier = Modifier.fillMaxWidth()
                        .height(48.dp)
                )

            }

        }
    }

}
@Preview()
@Composable
fun  SaveMovieBottomSheetPreview(){
    MovioTheme{
        SaveMovieBottomSheet(  show = true,
            onDismiss = {})
    }

}

