package com.madrid.presentation.screens.refreshScreenHolder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun  RefreshScreenHolder(
    refreshState : Boolean,
    onRefresh : ()->Unit,
    content : @Composable ()->Unit
){
    val pullRefreshState = rememberPullRefreshState(
        refreshing = refreshState,
        onRefresh = onRefresh
    )
    Box(
        Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ){

        content()

        PullRefreshIndicator(
            refreshing = refreshState,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Preview
@Composable
fun RefreshScreenHolderPreview(

){
    val refreshState = remember { mutableStateOf(false) }
    LaunchedEffect(refreshState.value) {
        if (refreshState.value) {
            delay(2000L) // delay for 2 seconds
            refreshState.value = false
        }
    }
    RefreshScreenHolder(
        refreshState =refreshState.value,
        onRefresh = {
            refreshState.value = true
        }
    ){
        Column(
            Modifier.fillMaxSize(),
            Arrangement.Center,
            Alignment.CenterHorizontally
        ) {
            Text(
                "hallo"
            )
        }
    }
}