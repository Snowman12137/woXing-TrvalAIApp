package com.example.kamteamapp.ui.item.LazyPart

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.core.content.PackageManagerCompat.LOG_TAG

@Composable
fun rememberLazyLayoutState(): LazyLayoutState {
    return remember { LazyLayoutState() }
}

@Stable
class LazyLayoutState {

    private val _offsetState = mutableStateOf(IntOffset(0, 0))
    val offsetState = _offsetState

    fun onDrag(offset: IntOffset) {
        val x = (_offsetState.value.x - offset.x).coerceAtLeast(0)
        val y = (_offsetState.value.y - offset.y).coerceAtLeast(0)
        _offsetState.value = IntOffset(x, y)
    }

    @SuppressLint("RestrictedApi")
    fun getBoundaries(
        constraints: Constraints,
        threshold: Int = 500
    ): ViewBoundaries {
        //------------------------------------------
        val fromX = offsetState.value.x - threshold
        val toX = constraints.maxWidth + offsetState.value.x + threshold
        val fromY = offsetState.value.y - threshold
        val toY = constraints.maxHeight + offsetState.value.y + threshold
        val logMessage = "Range: X [$fromX, $toX], Y [$fromY, $toY]"
        Log.d(LOG_TAG, logMessage)
        //-----------------------------------------
        return ViewBoundaries(
            fromX = offsetState.value.x - threshold,
            toX = constraints.maxWidth + offsetState.value.x + threshold,
            fromY = offsetState.value.y - threshold,
            toY = constraints.maxHeight + offsetState.value.y + threshold
        )
    }
}