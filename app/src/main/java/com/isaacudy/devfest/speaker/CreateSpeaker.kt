package com.isaacudy.devfest.speaker

import com.isaacudy.devfest.Dependencies
import com.isaacudy.devfest.shared.RequestStringDestination
import com.isaacudy.devfest.speaker.data.Speaker
import dev.enro.annotations.ExperimentalEnroApi
import dev.enro.annotations.NavigationDestination
import dev.enro.core.NavigationKey
import dev.enro.core.closeWithResult
import dev.enro.destination.flow.managedFlowDestination
import kotlinx.parcelize.Parcelize

@Parcelize
data object CreateSpeakerDestination : NavigationKey.SupportsPush.WithResult<Speaker.Id>

@OptIn(ExperimentalEnroApi::class)
@NavigationDestination(CreateSpeakerDestination::class)
val createSpeakerFlow = managedFlowDestination<CreateSpeakerDestination>()
    .flow {
        val name = push {
            RequestStringDestination(
                title = "Create Speaker",
                description = "Please enter the name of the speaker",
                label = "Name",
            )
        }
        val headline = push {
            RequestStringDestination(
                title = "Create Speaker",
                description = "Please enter a short headline about the speaker",
                label = "Headline",
            )
        }
        val biography = push {
            RequestStringDestination(
                title = "Create Speaker",
                description = "Please enter a biography for the speaker",
                label = "Biography",
                multiline = true,
            )
        }
        val email = push {
            RequestStringDestination(
                title = "Create Speaker",
                description = "Please enter an email address for the speaker",
                label = "Email",
            )
        }
        val phone = push {
            RequestStringDestination(
                title = "Create Speaker",
                description = "Please enter a phone number for the speaker",
                label = "Phone",
            )
        }

        Speaker(
            id = Speaker.Id.new(),
            name = name,
            headline = headline,
            biography = biography,
            email = email,
            phone = phone,
        )
    }
    .onComplete { speaker ->
        Dependencies.speakerRepository.saveSpeaker(speaker)
        navigation.closeWithResult(speaker.id)
    }
