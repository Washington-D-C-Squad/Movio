package com.example.designsystem.component
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.designsystem.AppTheme



@Composable
fun FilterbBar(
    items : List<String>,
    selectedItem : String,
    onItemClick : (String) -> Unit,
    scrollable: Boolean = true,
    modifier: Modifier = Modifier) {
    if (scrollable){
        LazyRow(
            modifier=modifier,
            horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.small),
            contentPadding = PaddingValues(horizontal = AppTheme.spacing.medium),

        ){

            items(items){
                item ->
                FilterChip(
                    text = item,
                    isSelected = item == selectedItem,
                    onClick = { onItemClick(item) },

                )
            }
        }
    }
    else{

        Row (modifier = modifier
            .padding(horizontal = AppTheme.spacing.medium)){
            items.forEach { item ->
                FilterChip(
                    text = item,
                    isSelected = item == selectedItem,
                    onClick = { onItemClick(item) },
                )
            }
        }
    }


}
@Composable
fun FilterChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (isSelected) AppTheme.colors.brandColors.primary else AppTheme.colors.surfaceColor.onSurfaceContainer
    val textColor = if (isSelected) AppTheme.colors.surfaceColor.onSurface else AppTheme.colors.surfaceColor.onSurface_1
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(AppTheme.radius.medium))
            .clickable(onClick = onClick)
            .background(backgroundColor)
            .padding(horizontal = AppTheme.spacing.medium, vertical = AppTheme.spacing.small)
    ){
        Text(text= text,
            color= textColor,

           // style = AppTheme.textStyle.label
        )

    }
}

