package com.example.digikala.ui.screens.product_detile

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikala.data.model.profile.FevItem
import com.example.digikala.viweModels.FavoriteListViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DisplayFavoriteToggleButton(
    item: FevItem,
    viewModel: FavoriteListViewModel = hiltViewModel()
) {
    var checkState by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(true) {
        viewModel.isFavoriteItemExist(item.id).collectLatest {
            checkState = it
        }
    }
    IconToggleButton(checked = checkState, onCheckedChange = {
        checkState = !checkState
        if (checkState) {
            viewModel.addFavoriteItem(item)
        } else {
            viewModel.removeFavoriteItem(item)
        }
    }) {
        val transition =
            updateTransition(targetState = checkState, label = "icon transition")
        val tint by transition.animateColor(label = "colorIcon") { isCheck ->
            if (isCheck) Color.Red else MaterialTheme.colors.onSurface
        }
        Icon(
            imageVector = if (checkState) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = "",
            tint = tint,
            modifier = Modifier.size(24.dp),
        )
    }
}