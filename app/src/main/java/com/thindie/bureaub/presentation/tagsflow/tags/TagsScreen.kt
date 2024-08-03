package com.thindie.bureaub.presentation.tagsflow.tags

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import com.thindie.bureaub.R
import com.thindie.bureaub.presentation.Screen
import com.thindie.bureaub.routing.Destination
import com.thindie.bureaub.uiKit.theme.components.BurTopBar


fun NavGraphBuilder.Tags() = Screen<TagsViewModel>(
    destination = Destination.Tags
) {
    Column(modifier = Modifier.fillMaxSize()) {
        BurTopBar(title = stringResource(R.string.all_tags)) { it.onBack() }
    }
}