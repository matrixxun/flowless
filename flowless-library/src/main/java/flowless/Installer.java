/*
 * Copyright 2016 Square Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package flowless;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public /*final*/ class Installer {
    private final Context baseContext;
    private final Activity activity;
    private KeyParceler parceler;
    private Object defaultKey;
    private Dispatcher dispatcher;
    private Object globalKey;

    Installer(Context baseContext, Activity activity) {
        this.baseContext = baseContext;
        this.activity = activity;
    }

    @NonNull
    public Installer keyParceler(@Nullable KeyParceler parceler) {
        this.parceler = parceler;
        return this;
    }

    @NonNull
    public Installer dispatcher(@Nullable Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
        return this;
    }

    @NonNull
    public Installer defaultKey(@Nullable Object defaultKey) {
        this.defaultKey = defaultKey;
        return this;
    }

    public Installer globalKey(@NonNull Object globalKey) {
        this.globalKey = globalKey;
        return this;
    }

    @NonNull
    public Context install() {
        if(InternalLifecycleIntegration.find(activity) != null) {
            throw new IllegalStateException("Flow is already installed in this Activity.");
        }
        if(dispatcher == null) {
            throw new IllegalStateException("Dispatcher must be defined, but is missing.");
        }
        if(defaultKey == null) {
            throw new IllegalStateException("Default Key must be defined, but is missing.");
        }
        if(parceler == null) {
            parceler = Flow.DEFAULT_KEY_PARCELER;
        }
        final Object defState = defaultKey;

        final History defaultHistory = History.single(defState);
        final Application app = (Application) baseContext.getApplicationContext();
        final KeyManager keyManager = new KeyManager(globalKey);
        final ServiceProvider serviceProvider = new ServiceProvider();
        InternalLifecycleIntegration.install(app, activity, parceler, defaultHistory, dispatcher, serviceProvider, keyManager);

        return new InternalContextWrapper(baseContext, activity, globalKey);
    }
}
