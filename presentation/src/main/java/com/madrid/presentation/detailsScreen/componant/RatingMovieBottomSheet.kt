package com.madrid.presentation.detailsScreen.componant

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madrid.designSystem.R
import com.madrid.designSystem.component.MovioBottomSheet
import com.madrid.designSystem.component.MovioButton
import com.madrid.designSystem.component.MovioIcon
import com.madrid.designSystem.component.MovioText
import com.madrid.designSystem.theme.MovioTheme
import com.madrid.designSystem.theme.Theme
import com.madrid.presentation.composables.movioCards.BasicImageCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RatingMovieBottomSheet(
    modifier: Modifier = Modifier,
    show: Boolean,
    onDismiss: () -> Unit,
    imageUrl: String,
    nameMovie: String,) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var rating by remember { mutableIntStateOf(0) }
    if (show){
        MovioBottomSheet(
            show = true,
            onDismiss = onDismiss,
            containerColor = Theme.color.surfaces.surface) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp, horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BasicImageCard(
                    imageUrl = imageUrl,
                    radius = 100.dp,
                    modifier = Modifier.size(88.dp)
                )
                MovioText(
                    text = nameMovie,
                    textStyle = Theme.textStyle.title.mediumMedium16,
                    color = Theme.color.surfaces.onSurface,
                    modifier = Modifier.padding( top = 8.dp, bottom = 24.dp)

                )
                MovioText(
                    text = "Add your overall rating for this movie",
                    textStyle = Theme.textStyle.label.smallRegular12,
                    color = Theme.color.surfaces.onSurfaceContainer,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 40.dp),
                    textAlign = TextAlign.Center
                )
                Row(
                    modifier = Modifier.padding(bottom = 40.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    for (i in 1..5) {
                        MovioIcon(
                            painter = painterResource(id = R.drawable.bold_star),
                            contentDescription = "Star $i",
                            modifier = Modifier
                                .size(32.dp)
                                .clickable { rating = i },
                            tint = if (i <= rating) Theme.color.system.warning else Theme.color.surfaces.onSurfaceVariant
                        )
                        if (i < 5) Spacer(modifier = Modifier.width(8.dp))
                    }
                }
                MovioButton(
                    color = Theme.color.brand.primary,
                    content = {MovioText(text = "Submit",textStyle = Theme.textStyle.label.mediumMedium16,
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
fun  RatingMovieBottomSheetPreview(){
    MovioTheme{
        RatingMovieBottomSheet(  show = true,
            imageUrl = "",
            nameMovie = "mohammed",
            onDismiss = {})
    }

}
