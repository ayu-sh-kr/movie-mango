@ApplicationModule(
    type = ApplicationModule.Type.CLOSED,
    allowedDependencies = {
        "user :: domain :: AccountDeleteEvent",
        "application"
    }
)
package dev.archimedes.moviemango.user;

import org.springframework.modulith.ApplicationModule;