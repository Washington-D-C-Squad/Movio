package com.madrid.presentation.screens.detailsScreen.componant
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.madrid.designsystem.AppTheme
import com.madrid.designsystem.R
import com.madrid.designsystem.component.MovioBottomSheet
import com.madrid.designsystem.component.MovioIcon
import com.madrid.designsystem.component.MovioText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShareBottomSheet(
    show: Boolean,
    onDismiss: () -> Unit,
) {
    MovioBottomSheet(
        show = show,
        onDismiss = onDismiss,
        containerColor = AppTheme.colors.surfaceColor.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MovioText(
                text = "Share via",
                textStyle = AppTheme.textStyle.label.largeMedium16,
                color = AppTheme.colors.surfaceColor.onSurface,
                modifier = Modifier.padding(bottom = AppTheme.spacing.medium)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = AppTheme.spacing.medium),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ShareItem(
                    icon = R.drawable.outline_link_minimalistic,
                    label = "Copy link",
                    onClick = onDismiss
                )
                ShareItem(
                    icon = R.drawable.facebook,
                    label = "Facebook",
                    onClick = onDismiss
                )
                ShareItem(
                    icon = R.drawable.social_icon,
                    label = "X",
                    onClick = onDismiss
                )
            }
        }
    }
}
@Composable
fun ShareItem(icon: Int, label: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(AppTheme.colors.surfaceColor.onSurfaceVariant.copy(alpha = 0.2f))
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            MovioIcon(
                painter = painterResource(id = icon),
                contentDescription = label,
                tint = AppTheme.colors.surfaceColor.onSurface,
                modifier = Modifier.size(28.dp)
            )
        }
        MovioText(
            text = label,
            textStyle = AppTheme.textStyle.label.medium14,
            color = AppTheme.colors.surfaceColor.onSurface,
            modifier = Modifier.padding(top = 6.dp)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun ShareBottomSheetPreview() {
    AppTheme {
        ShareBottomSheet(
            show = true,
            onDismiss = {}
        )
    }
}
