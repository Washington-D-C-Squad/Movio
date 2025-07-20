package com.madrid.presentation.detailsScreen.componant
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
import com.madrid.designSystem.theme.Theme
import com.madrid.designSystem.R
import com.madrid.designSystem.component.MovioBottomSheet
import com.madrid.designSystem.component.MovioIcon
import com.madrid.designSystem.component.MovioText
import com.madrid.designSystem.theme.MovioTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShareBottomSheet(
    show: Boolean,
    onDismiss: () -> Unit,
) {
    MovioBottomSheet(
        show = show,
        onDismiss = onDismiss,
        containerColor = Theme.color.surfaces.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MovioText(
                text = "Share via",
                textStyle = Theme.textStyle.label.mediumMedium16,
                color = Theme.color.surfaces.onSurface,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
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
                .background(Theme.color.surfaces.onSurfaceVariant.copy(alpha = 0.2f))
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            MovioIcon(
                painter = painterResource(id = icon),
                contentDescription = label,
                tint = Theme.color.surfaces.onSurface,
                modifier = Modifier.size(28.dp)
            )
        }
        MovioText(
            text = label,
            textStyle = Theme.textStyle.label.mediumMedium14,
            color = Theme.color.surfaces.onSurface,
            modifier = Modifier.padding(top = 6.dp)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun ShareBottomSheetPreview() {
    MovioTheme {
        ShareBottomSheet(
            show = true,
            onDismiss = {}
        )
    }
}
