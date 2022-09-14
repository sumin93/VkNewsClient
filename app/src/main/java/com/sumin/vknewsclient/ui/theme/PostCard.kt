package com.sumin.vknewsclient.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sumin.vknewsclient.R

@Composable
fun PostCard() {
    Card {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            PostHeader()
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(R.string.template_text))
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.post_content_image),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.height(8.dp))
            Statistics()
        }
    }
}

@Composable
private fun PostHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.post_comunity_thumbnail),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "/dev/null",
                color = MaterialTheme.colors.onPrimary
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "14:00",
                color = MaterialTheme.colors.onSecondary
            )
        }
        Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = null,
            tint = MaterialTheme.colors.onSecondary
        )
    }
}

@Composable
private fun Statistics() {
    Row {
        Row(
            modifier = Modifier.weight(1f)
        ) {
            IconWithText(iconResId = R.drawable.ic_views_count, text = "966")
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconWithText(iconResId = R.drawable.ic_share, text = "7")
            IconWithText(iconResId = R.drawable.ic_comment, text = "8")
            IconWithText(iconResId = R.drawable.ic_like, text = "27")
        }
    }
}

@Composable
private fun IconWithText(
    iconResId: Int,
    text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = MaterialTheme.colors.onSecondary
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            color = MaterialTheme.colors.onSecondary
        )
    }
}

@Preview
@Composable
private fun PreviewLight() {
    VkNewsClientTheme(darkTheme = false) {
        PostCard()
    }
}

@Preview
@Composable
private fun PreviewDark() {
    VkNewsClientTheme(darkTheme = true) {
        PostCard()
    }
}