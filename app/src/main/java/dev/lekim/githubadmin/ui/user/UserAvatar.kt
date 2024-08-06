package dev.lekim.githubadmin.ui.user

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import dev.lekim.githubadmin.R

@Composable
fun UserAvatar(
    modifier: Modifier = Modifier,
    imgUrl: String,
    contentDescription: String?
) {

    AsyncImage(
        modifier = modifier.fillMaxSize().clip(CircleShape),
        model = imgUrl,
        contentDescription = contentDescription,
        placeholder = painterResource(id = R.drawable.avatar_holder),
    )

}

@Preview
@Composable
private fun UserAvatarPreview() {
    UserAvatar(
        imgUrl = "https://avatars.githubusercontent.com/u/111620171?v=4",
        contentDescription = "lekimtantai's user avatar.",
    )
}