package com.tuneinc.multitune.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tuneinc.multitune.ui.components.AlbumRow
import com.tuneinc.multitune.ui.components.ArtistRow
import com.tuneinc.multitune.ui.components.PlaylistRow
import com.tuneinc.multitune.ui.components.RecentlyPlayedRow
import com.tuneinc.multitune.viewmodels.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("MultiTune") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            RecentlyPlayedRow(
                tracks = uiState.recentlyPlayed,
                onTrackClick = { viewModel.playTrack(it) }
            )
            
            PlaylistRow(
                playlists = uiState.recommendedPlaylists,
                onPlaylistClick = { navController.navigate("playlist/${it.id}") }
            )
            
            AlbumRow(
                albums = uiState.topAlbums,
                onAlbumClick = { navController.navigate("album/${it.id}") }
            )
            
            ArtistRow(
                artists = uiState.recommendedArtists,
                onArtistClick = { navController.navigate("artist/${it.id}") }
            )
        }
    }
}