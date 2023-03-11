package com.sumin.vknewsclient.presentation.main

import androidx.compose.foundation.clickable
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sumin.vknewsclient.navigation.AppNavGraph
import com.sumin.vknewsclient.navigation.rememberNavigationState
import com.sumin.vknewsclient.presentation.ViewModelFactory
import com.sumin.vknewsclient.presentation.comments.CommentsScreen
import com.sumin.vknewsclient.presentation.news.NewsFeedScreen

@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()

                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favourite,
                    NavigationItem.Profile
                )
                items.forEach { item ->

                    val selected = navBackStackEntry?.destination?.hierarchy?.any {
                        it.route == item.screen.route
                    } ?: false

                    BottomNavigationItem(
                        selected = selected,
                        onClick = {
                            if (!selected) {
                                navigationState.navigateTo(item.screen.route)
                            }
                        },
                        icon = {
                            Icon(item.icon, contentDescription = null)
                        },
                        label = {
                            Text(text = stringResource(id = item.titleResId))
                        },
                        selectedContentColor = MaterialTheme.colors.onPrimary,
                        unselectedContentColor = MaterialTheme.colors.onSecondary
                    )
                }
            }
        }
    ) { paddingValues ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            newsFeedScreenContent = {
                NewsFeedScreen(
                    paddingValues = paddingValues,
                    onCommentClickListener = {
                        navigationState.navigateToComments(it)
                    }
                )
            },
            commentsScreenContent = { feedPost ->
                CommentsScreen(
                    onBackPressed = {
                        navigationState.navHostController.popBackStack()
                    },
                    feedPost = feedPost
                )
            },
            favouriteScreenContent = { TextCounter(name = "Favourite") },
            profileScreenContent = { TextCounter(name = "Profile") }
        )
    }
}

@Composable
private fun TextCounter(name: String) {
    var count by rememberSaveable {
        mutableStateOf(0)
    }

    Text(
        modifier = Modifier.clickable { count++ },
        text = "$name Count: $count",
        color = Color.Black
    )
}

