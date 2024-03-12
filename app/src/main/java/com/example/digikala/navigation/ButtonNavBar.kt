package com.example.digikala.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.digikala.R
import com.example.digikala.ui.screens.basket.IconWithBadge
import com.example.digikala.ui.theme.bottombar
import com.example.digikala.ui.theme.selectBottom
import com.example.digikala.ui.theme.unselectBottom
import com.example.digikala.util.Constants
import com.example.digikala.util.LocalUtil
import com.example.digikala.viweModels.BasketViewModel

@Composable
fun ButtonNavBar(
    navController: NavController,
    onItemClick: (ButtonNavigation) -> Unit,
    viewModel: BasketViewModel = hiltViewModel()
) {
    LocalUtil.setLocale(LocalContext.current, Constants.USER_LANGUAGE)
    val items = listOf(
        ButtonNavigation(
            name = stringResource(id = R.string.home),
            route = ScreenPage.Home.route,
            selectIcon = painterResource(id = R.drawable.home_fill),
            unselectIcon = painterResource(id = R.drawable.home_outline)
        ),
        ButtonNavigation(
            name = stringResource(id = R.string.category),
            route = ScreenPage.Category.route,
            selectIcon = painterResource(id = R.drawable.category_fill),
            unselectIcon = painterResource(id = R.drawable.category_outline)
        ),
        ButtonNavigation(
            name = stringResource(id = R.string.basket),
            route = ScreenPage.Basket.route,
            selectIcon = painterResource(id = R.drawable.cart_fill),
            unselectIcon = painterResource(id = R.drawable.cart_outline)
        ),
        ButtonNavigation(
            name = stringResource(id = R.string.profile),
            route = ScreenPage.Profile.route,
            selectIcon = painterResource(id = R.drawable.user_fill),
            unselectIcon = painterResource(id = R.drawable.user_outline)
        )
    )
    val backStackEntry = navController.currentBackStackEntryAsState()
    val showBottomBar = backStackEntry.value?.destination?.route in items.map { it.route }
    if (showBottomBar) {
        BottomNavigation(
            modifier = Modifier.height(60.dp),
            backgroundColor = MaterialTheme.colors.bottombar,
            elevation = 5.dp,
        ) {
            val currentCartItemsCount = viewModel.currentCartItemsCount.collectAsState(initial = 0)
            items.forEachIndexed { index, buttonNavigation ->
                val select = buttonNavigation.route == backStackEntry.value?.destination?.route
                BottomNavigationItem(
                    selected = select,
                    onClick = { onItemClick(buttonNavigation) },
                    icon = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            if (select) {
                                if (index == 2 && currentCartItemsCount.value > 0) {
                                    IconWithBadge(
                                        currentCartItemsCount.value,
                                        buttonNavigation.selectIcon
                                    )
                                } else {
                                    Icon(
                                        modifier = Modifier.height(24.dp),
                                        painter = buttonNavigation.selectIcon,
                                        contentDescription = buttonNavigation.name
                                    )
                                }

                            } else {
                                if (index == 2 && currentCartItemsCount.value > 0) {
                                    IconWithBadge(
                                        currentCartItemsCount.value,
                                        buttonNavigation.unselectIcon
                                    )
                                } else {
                                    Icon(
                                        modifier = Modifier.height(24.dp),
                                        painter = buttonNavigation.unselectIcon,
                                        contentDescription = buttonNavigation.name
                                    )
                                }

                            }
                            Text(
                                text = buttonNavigation.name,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.h6,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 5.dp)

                            )
                        }

                    },
                    selectedContentColor = MaterialTheme.colors.selectBottom,
                    unselectedContentColor = MaterialTheme.colors.unselectBottom
                )
            }
        }
    }
}