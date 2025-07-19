package com.madrid.presentation.composables

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.component.MovioText
import com.madrid.detectImageContent.FilteredImage

data class CastMember(
    val id: String,
    val name: String,
    val imageUrl: String
)

@Composable
fun TopCastSection(
    castMembers: List<CastMember>,
    onSeeAllClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MovioText(
                text = "Top Cast",
                color = AppTheme.colors.surfaceColor.onSurface,
                textStyle = AppTheme.textStyle.headLine.medium18
            )
            
            MovioText(
                text = "See all >",
                color = AppTheme.colors.surfaceColor.onSurfaceVariant,
                textStyle = AppTheme.textStyle.label.smallRegular14,
                modifier = Modifier.clickable { onSeeAllClick() }
            )
        }
        
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(castMembers) { castMember ->
                CastMemberItem(castMember = castMember)
            }
        }
    }
}

@Composable
private fun CastMemberItem(
    castMember: CastMember,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        ) {
            FilteredImage(
                imageUrl = castMember.imageUrl,
                modifier = Modifier.fillMaxWidth(),
                contentDescription = castMember.name,
                contentScale = ContentScale.Crop
            )
        }
        
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        
        MovioText(
            text = castMember.name,
            color = AppTheme.colors.surfaceColor.onSurface,
            textStyle = AppTheme.textStyle.label.smallRegular14
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopCastSectionPreview() {
    AppTheme {
        TopCastSection(
            castMembers = listOf(
                CastMember(
                    id = "1",
                    name = "Ana de Armas",
                    imageUrl = "https://image.tmdb.org/t/p/w500/3vxvsmYLTf4jnr163SUlBIWX8qx.jpg"
                ),
                CastMember(
                    id = "2",
                    name = "Keanu Reeves",
                    imageUrl = "https://image.tmdb.org/t/p/w500/4D0PpNI0km5B9Gk7SZOo6hJxJ9P.jpg"
                ),
                CastMember(
                    id = "3",
                    name = "Ian McShane",
                    imageUrl = "https://image.tmdb.org/t/p/w500/9H7oVx4b6Z0j3EjLZN9mzcqcJjU.jpg"
                )
            )
        )
    }
} 