package com.hackheroes.newoldtown.provider

import android.content.Context
import ovh.plrapps.mapcompose.core.TileStreamProvider

fun makeTileStreamProvider(appContext: Context) =
    TileStreamProvider { row, col, zoomLvl ->
        try {
            appContext.assets?.open("tiles/mont_blanc/$zoomLvl/$row/$col.jpg")
        } catch (e: Exception) {
            null
        }
    }