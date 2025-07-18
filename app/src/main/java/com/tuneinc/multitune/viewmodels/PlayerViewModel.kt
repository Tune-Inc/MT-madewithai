package com.tuneinc.multitune.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuneinc.multitune.data.models.Track
import com.tuneinc.multitune.data.repositories.MusicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PlayerState(
    val isPlaying: Boolean = false,
    val currentTrack: Track? = null,
    val currentPosition: Long = 0L,
    val duration: Long = 0L,
    val progress: Float = 0f,
    val queue: List<Track> = emptyList(),
    val shuffleEnabled: Boolean = false,
    val repeatMode: Int = 0, // 0: off, 1: repeat all, 2: repeat one
    val isFavorite: Boolean = false
)

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val musicRepository: MusicRepository
) : ViewModel() {
    
    private val _playerState = MutableStateFlow(PlayerState())
    val playerState: StateFlow<PlayerState> = _playerState.asStateFlow()
    
    init {
        viewModelScope.launch {
            // Initialize with a sample track for demonstration
            val sampleTrack = Track(
                id = "1",
                title = "Sample Track",
                artist = "Sample Artist",
                album = "Sample Album",
                duration = 180000L,
                artworkUrl = "https://example.com/artwork.jpg",
                streamUrl = "https://example.com/stream.mp3"
            )
            _playerState.value = _playerState.value.copy(
                currentTrack = sampleTrack,
                duration = sampleTrack.duration
            )
        }
    }
    
    fun playTrack(track: Track) {
        _playerState.value = _playerState.value.copy(
            currentTrack = track,
            isPlaying = true,
            currentPosition = 0L,
            progress = 0f,
            duration = track.duration
        )
    }
    
    fun playPause() {
        _playerState.value = _playerState.value.copy(
            isPlaying = !_playerState.value.isPlaying
        )
    }
    
    fun seekTo(progress: Float) {
        val newPosition = (_playerState.value.duration * progress).toLong()
        _playerState.value = _playerState.value.copy(
            currentPosition = newPosition,
            progress = progress
        )
    }
    
    fun next() {
        // In a real app, you would move to the next track in the queue
    }
    
    fun previous() {
        // In a real app, you would move to the previous track or restart the current one
    }
    
    fun toggleShuffle() {
        _playerState.value = _playerState.value.copy(
            shuffleEnabled = !_playerState.value.shuffleEnabled
        )
    }
    
    fun toggleRepeat() {
        _playerState.value = _playerState.value.copy(
            repeatMode = (_playerState.value.repeatMode + 1) % 3
        )
    }
    
    fun toggleFavorite() {
        _playerState.value = _playerState.value.copy(
            isFavorite = !_playerState.value.isFavorite
        )
    }
}