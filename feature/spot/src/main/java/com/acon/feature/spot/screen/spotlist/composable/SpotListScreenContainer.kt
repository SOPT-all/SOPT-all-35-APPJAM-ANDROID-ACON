package com.acon.feature.spot.screen.spotlist.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.acon.core.map.ProceedWithLocation
import com.acon.feature.spot.screen.spotlist.SpotListViewModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun SpotListScreenContainer(
    modifier: Modifier = Modifier,
    onNavigateToSpotDetailScreen: (id: Int) -> Unit = {},
    viewModel: SpotListViewModel = hiltViewModel()
) {

    val state by viewModel.collectAsState()

    ProceedWithLocation {
        viewModel.onRefresh(it.latitude, it.longitude)
    }

    SpotListScreen(
        state = state,
        modifier = modifier.fillMaxSize()
    )
}