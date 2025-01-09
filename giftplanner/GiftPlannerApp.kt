package org.minhduc.giftplanner

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by Minh Duc on 09/01/2025.
 * Copyright (c) 2024 Minh Duc. All rights reserved.
 */

@HiltAndroidApp
class GiftPlannerApp: Application() {
    override fun onCreate() { super.onCreate() }
}