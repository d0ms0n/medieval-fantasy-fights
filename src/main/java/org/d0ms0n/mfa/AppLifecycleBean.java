package org.d0ms0n.mfa;

import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class AppLifecycleBean {

    void onStart(@Observes StartupEvent event) {

    }
}
