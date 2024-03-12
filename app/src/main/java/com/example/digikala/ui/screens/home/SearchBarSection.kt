package com.example.digikala.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.digikala.R
import com.example.digikala.ui.theme.localElevation
import com.example.digikala.ui.theme.localShape
import com.example.digikala.ui.theme.localSpacing
import com.example.digikala.ui.theme.searchBarBg
import com.example.digikala.ui.theme.unselectBottom
import com.example.digikala.util.Constants.ENGLISH_LNG
import com.example.digikala.util.Constants.USER_LANGUAGE

@Composable
fun SearchBarSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp),
        elevation = localElevation.current.extraSmall
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(localSpacing.current.small)
                .clip(localShape.current.bigSmall)
                .background(MaterialTheme.colors.searchBarBg)
        ) {
            SearchContent()
        }
    }
}

@Composable
private fun SearchContent() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            modifier = Modifier
                .height(24.dp),
            painter = painterResource(id = R.drawable.search),
            contentDescription = ""
        )
        Text(
            modifier = Modifier
                .padding(start = 20.dp),
            text = stringResource(id = R.string.search_in),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.unselectBottom,
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.h2
        )
        Image(
            modifier = Modifier
                .width(80.dp)
                .padding(5.dp),
            painter = digikalaLogoByLang(), contentDescription = ""
        )

    }
}

@Composable
private fun digikalaLogoByLang(): Painter {
    return if (USER_LANGUAGE == ENGLISH_LNG) {
        painterResource(id = R.drawable.digi_red_english)
    } else {
        painterResource(id = R.drawable.digi_red_persian)
    }
}