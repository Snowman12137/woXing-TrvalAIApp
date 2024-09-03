package com.example.kamteamapp.componets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.kamteamapp.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit,
    title: @Composable () -> Unit,
    actions: @Composable RowScope.() -> Unit = {}
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        actions = actions,
        title = title,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
//            JetchatIcon(
//                contentDescription = stringResource(id = R.string.navigation_drawer_open),
//                modifier = Modifier
//                    .size(64.dp)
//                    .clickable(onClick = navigateUp)
//                    .padding(16.dp)
//            )
            Icon(
                imageVector = Icons.Filled.ArrowBackIosNew,
                contentDescription = null,
                modifier = Modifier
                    .padding(10.dp, 5.dp, 0.dp, 5.dp)
                    .clickable { navigateUp() }
            )
        }
    )
}

