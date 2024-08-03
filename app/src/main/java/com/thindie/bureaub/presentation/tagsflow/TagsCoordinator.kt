package com.thindie.bureaub.presentation.tagsflow

import androidx.navigation.NavGraphBuilder
import com.thindie.bureaub.presentation.FlowCoordinator
import com.thindie.bureaub.presentation.tagsflow.createtag.TagCreate
import com.thindie.bureaub.presentation.tagsflow.tags.Tags
import com.thindie.bureaub.routing.Destination

class TagsCoordinator(
    override val flow: String = Destination.TagsFlow::class.java.name,
    override val routes: List<NavGraphBuilder.() -> Unit> = listOf({ TagCreate() }, { Tags() }),
    override val startDestination: String = Destination.Tags::class.java.name,
    override val popDestination: Destination = Destination.TagsOnBack,
    override val finishDestination: Destination,
    override val destinations: List<Destination> = listOf(
        Destination.Tags, Destination.TagAdd, Destination.TagsFinish
    ),
) : FlowCoordinator