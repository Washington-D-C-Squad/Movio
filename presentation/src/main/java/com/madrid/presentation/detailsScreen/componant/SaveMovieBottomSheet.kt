package com.madrid.presentation.detailsScreen.componant

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
import com.madrid.designSystem.AppTheme
import com.madrid.designSystem.R
import com.madrid.designSystem.component.MovioBottomSheet
import com.madrid.designSystem.component.MovioButton
import com.madrid.designSystem.component.MovioIcon
import com.madrid.designSystem.component.MovioText

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
            show = show,
            onDismiss = onDismiss,
            containerColor = AppTheme.colors.surfaceColor.surface

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
                    textStyle = AppTheme.textStyle.title.medium16,
                    color = AppTheme.colors.surfaceColor.onSurface,
                    modifier = Modifier.padding( top = 16.dp, bottom = 8.dp)

                )
                MovioText(
                    text = "Please log in or create an account to save items to your favorites and access them later.",
                    textStyle = AppTheme.textStyle.label.smallRegular12,
                    color = AppTheme.colors.surfaceColor.onSurfaceContainer,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 40.dp),
                    textAlign = TextAlign.Center
                )
                MovioButton(
                    color = AppTheme.colors.brandColors.primary,
                    content = {MovioText(text = "login",textStyle = AppTheme.textStyle.label.largeMedium16,
                        color = AppTheme.colors.brandColors.onPrimary,)},
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
    AppTheme{
        SaveMovieBottomSheet(  show = true,
            onDismiss = {})
    }

}

