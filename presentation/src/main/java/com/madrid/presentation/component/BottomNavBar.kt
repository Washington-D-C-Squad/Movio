package com.madrid.presentation.component

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.component.MovioIcon
import com.madrid.designsystem.component.MovioText
import com.madrid.presentation.R
import com.madrid.presentation.navigation.Screen


@Preview(showBackground = true)
@Composable
fun CustomBottomBarPreview() {
    AppTheme {
        CustomBottomBar(
            currentRoute = Screen.Search.route,
            navItems = navDestinations,
            onNavDestinationClicked = {},
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
        )
    }
}


data class NavBarItem(
    val route: String,
    val label: String,
    val contentDescription: String = "none",
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unSelectedIcon: Int,
)

val navDestinations = listOf(
    NavBarItem(
        route = Screen.Home.route,
        label = "Home",
        contentDescription = "Home Screen",
        selectedIcon = R.drawable.icon_home_selected,
        unSelectedIcon = R.drawable.icon_home
    ),
    NavBarItem(
        route = Screen.Search.route,
        label = "Search",
        selectedIcon = R.drawable.icon_search_selected,
        unSelectedIcon = R.drawable.icon_search
    ),
    NavBarItem(
        route = Screen.Library.route,
        label = "Library",
        selectedIcon = R.drawable.icon_library_selected,
        unSelectedIcon = R.drawable.icon_library,
    ),
    NavBarItem(
        route = Screen.More.route,
        label = "More",
        selectedIcon = R.drawable.icon_more_selected,
        unSelectedIcon = R.drawable.icon_more
    )
)

@Composable
fun CustomBottomBar(
    currentRoute: String,
    onNavDestinationClicked: (String) -> Unit,
    navItems: List<NavBarItem>,
    modifier: Modifier = Modifier,
    backgroundColor: Color = AppTheme.colors.surfaceColor.surface,
    selectedTextStyle: TextStyle = AppTheme.textStyle.label.medium12,
    unselectedTextStyle: TextStyle = AppTheme.textStyle.label.smallRegular12,
    selectedTextColor: Color = AppTheme.colors.brandColors.primary,
    unSelectedTextColor: Color = AppTheme.colors.surfaceColor.onSurfaceVariant,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(74.dp)
            .background(color = backgroundColor)
            .padding(horizontal = AppTheme.spacing.large, vertical = AppTheme.spacing.medium),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        navItems.forEach { item ->
            val isSelected = currentRoute == item.route
            val animatedIconRes =
                animateIntAsState(
                    targetValue = if (isSelected) item.selectedIcon else item.unSelectedIcon,
                )
            val animatedLabelColor =
                animateColorAsState(
                    targetValue =if (isSelected) selectedTextColor else unSelectedTextColor
                )

            CustomNavBarItem(
                isSelected = isSelected,
                onNavDestinationClicked = onNavDestinationClicked,
                item = item,
                iconRes = animatedIconRes.value,
                selectedTextStyle = selectedTextStyle,
                unselectedTextStyle = unselectedTextStyle,
                labelColor = animatedLabelColor.value
            )
        }
    }
}

@Composable
private fun CustomNavBarItem(
    isSelected: Boolean,
    onNavDestinationClicked: (String) -> Unit,
    item: NavBarItem,
    @DrawableRes iconRes: Int,
    selectedTextStyle: TextStyle,
    unselectedTextStyle: TextStyle,
    labelColor: Color,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                if (!isSelected) {
                    onNavDestinationClicked(item.route)
                }
            }
    ) {
        MovioIcon(
            painter = painterResource(id = iconRes),
            contentDescription = item.contentDescription,
            modifier = Modifier.size(AppTheme.spacing.xLarge),
            tint = Color.Unspecified
        )
        MovioText(
            text = item.label,
            textStyle = if (isSelected) selectedTextStyle else unselectedTextStyle,
            color = labelColor,
            modifier = Modifier.padding(top = AppTheme.spacing.extraSmall)
        )
    }
}