package com.isaacudy.devfest

import android.app.Application
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import com.isaacudy.devfest.ui.theme.DevFestComposeEnvironment
import dev.enro.animation.direction
import dev.enro.annotations.NavigationComponent
import dev.enro.core.NavigationDirection
import dev.enro.core.controller.NavigationApplication
import dev.enro.core.controller.createNavigationController

@NavigationComponent
class DevFestApplication : Application(), NavigationApplication {
    override val navigationController = createNavigationController {

        // See [DevFestComposeEnvironment] for an explanation of what this is doing
        composeEnvironment { content ->
            DevFestComposeEnvironment(content)
        }

        animations {
            direction(
                direction = NavigationDirection.Push,
                entering = slideInHorizontally { it / 3 } + fadeIn(),
                exiting = slideOutHorizontally { -it / 3 } + fadeOut(),
                returnEntering = slideInHorizontally { -it / 3 } + fadeIn(),
                returnExiting = slideOutHorizontally { it / 3 } + fadeOut(),
            )
            direction(
                direction = NavigationDirection.Present,
                entering = scaleIn() + fadeIn(),
                exiting = scaleOut() + fadeOut(),
            )
        }
    }
}