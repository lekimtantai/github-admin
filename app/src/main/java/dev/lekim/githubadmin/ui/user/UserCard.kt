package dev.lekim.githubadmin.ui.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.lekim.githubadmin.R
import dev.lekim.githubadmin.ui.shared.hor16

@Composable
fun UserCard(
    modifier: Modifier = Modifier,
    title: @Composable ColumnScope.() -> Unit,
    content: @Composable ColumnScope.() -> Unit,
    imgUrl: String,
    onNavigate: ((String) -> Unit)? = null,
) {
    ElevatedCard(modifier = modifier,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 6.dp),
        onClick = { onNavigate?.let { it(imgUrl) } }) {
        Row(modifier = Modifier.padding(12.dp)) {
            Box(
                modifier = Modifier
                    .size(144.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = MaterialTheme.colorScheme.surfaceVariant)
                    .padding(8.dp),
            ) {
                UserAvatar(
                    imgUrl = imgUrl,
                    contentDescription = stringResource(id = R.string.user_avatar),
                )
            }
            Spacer(modifier = hor16)
            Column {
                title()
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                content()
            }
        }
    }
}

@Preview
@Composable
private fun UserCardPreview() {
    UserCard(
        title = {
            Text(text = "lekimtantai")
        },
        content = {
            Text("https://github.com/lekimtantai")
        },
        imgUrl = "https://avatars.githubusercontent.com/u/111620171?v=4",
        onNavigate = {},
    )
}