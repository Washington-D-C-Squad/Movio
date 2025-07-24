package com.madrid.presentation.screens.searchScreen.features.recentSearchLayout

import android.provider.ContactsContract.Contacts.Data
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madrid.designSystem.R
import com.madrid.designSystem.component.CustomTextTitel
import com.madrid.designSystem.component.MovioIcon
import com.madrid.designSystem.component.MovioText
import com.madrid.designSystem.theme.MovioTheme
import com.madrid.designSystem.theme.Theme
import com.madrid.presentation.component.movioCards.MovioVerticalCard
import com.madrid.presentation.viewModel.dataclassinterface.MediaUiState
import com.madrid.presentation.viewModel.searchViewModel.SearchScreenState

@Composable
fun CustomHorizontalCard(
    primaryTextForCustomTextTitel: String,
    ListOfMedia : List<MediaUiState>,
    modifier: Modifier = Modifier,
    secondaryTextForCustomTextTitel: String? = null,
    endIconForCustomTextTitel: Painter? = null,
    onSeeAllClick: (() -> Unit)? = null,
    onMediaClick: (MediaUiState) -> Unit = {},
    ) {
    Column(modifier = modifier) {
        CustomTextTitel(
            primaryText = primaryTextForCustomTextTitel,
            secondaryText = secondaryTextForCustomTextTitel,
            endIcon = endIconForCustomTextTitel,
            onSeeAllClick = onSeeAllClick
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.height(200.dp)
        ) {
            items(ListOfMedia) { media ->
                MovioVerticalCard(
                    description = media.title,
                    movieImage = media.imageUrl,
                    rate = media.rating,
                    width = 124.dp,
                    height = 160.dp,
                    paddingValue = 8.dp,
                    onClick = { onMediaClick(media) }
                )
            }
        }
    }
}