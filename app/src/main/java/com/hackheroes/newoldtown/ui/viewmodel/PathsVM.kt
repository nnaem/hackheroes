package com.hackheroes.newoldtown.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.hackheroes.newoldtown.provider.makeTileStreamProvider
import kotlinx.coroutines.launch
import ovh.plrapps.mapcompose.api.*
import ovh.plrapps.mapcompose.ui.paths.PathData
import ovh.plrapps.mapcompose.ui.paths.PathDataBuilder
import ovh.plrapps.mapcompose.ui.state.MapState

/**
 * In this sample, we add "tracks" to the map. The tracks are rendered as paths using MapCompose.
 */
class PathsVM(application: Application) : AndroidViewModel(application) {
    private val tileStreamProvider = makeTileStreamProvider(application.applicationContext)

    val state: MapState by mutableStateOf(
        MapState(4, 4096, 4096).apply {
            addLayer(tileStreamProvider)
            shouldLoopScale = true
            enableRotation()
            viewModelScope.launch {
                scrollTo(0.72, 0.3)
            }
        }
    )

    init {
        /* Add tracks */
        addTrack("track1", Color(0xFF448AFF))
        addTrack("track2", Color(0xFFFFFF00))
        addTrack("track3") // 0xFF448AFF is the default color
    }

    /**
     * In this sample, we retrieve track points from text files in the assets.
     * To add a path, follow these steps:
     *
     * 1. Retrieve a [PathDataBuilder] from the [MapState] instance, using [makePathDataBuilder]
     * 2. Add each point using [PathDataBuilder.addPoint]
     * 3. Build a [PathData] using [PathDataBuilder.build]
     * 4. Add the path to the map using [addPath]
     */
    private fun addTrack(trackName: String, color: Color? = null) {
        with(state) {
            val lines = getApplication<Application>().applicationContext.assets?.open(
                "tracks/$trackName.txt"
            )?.bufferedReader()?.lineSequence()
                ?: return@with
            val builder = makePathDataBuilder()
            for (line in lines) {
                val values = line.split(',')
                builder.addPoint(values[0].toDouble(), values[1].toDouble())
            }
            val pathData = builder.build() ?: return@with
            addPath(trackName, pathData, color = color, width = 12.dp)
        }
    }
}