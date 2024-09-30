package com.isaacudy.devfest

import android.app.Application
import dev.enro.annotations.NavigationComponent
import dev.enro.core.controller.NavigationApplication
import dev.enro.core.controller.createNavigationController

@NavigationComponent
class DevFestApplication : Application(), NavigationApplication {
    override val navigationController = createNavigationController { }
}