/*
 * ImageToolbox is an image editor for android
 * Copyright (c) 2025 T8RIN (Malik Mukhametzyanov)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * You should have received a copy of the Apache License
 * along with this program.  If not, see <http://www.apache.org/licenses/LICENSE-2.0>.
 */

package ru.tech.imageresizershrinker.core.crash.presentation.components

import android.content.Intent
import android.os.Build.BRAND
import android.os.Build.DEVICE
import android.os.Build.MODEL
import android.os.Build.VERSION.RELEASE
import android.os.Build.VERSION.SDK_INT
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import ru.tech.imageresizershrinker.core.domain.ISSUE_TRACKER
import ru.tech.imageresizershrinker.core.resources.BuildConfig.FLAVOR
import ru.tech.imageresizershrinker.core.resources.BuildConfig.VERSION_CODE
import ru.tech.imageresizershrinker.core.ui.utils.helper.AppVersion
import ru.tech.imageresizershrinker.core.ui.utils.helper.ContextUtils.getDisplayName

interface CrashHandler {

    fun getIntent(): Intent

    private val intent: Intent get() = getIntent()

    private fun getCrashReason(): String = intent.getStringExtra(EXCEPTION_EXTRA) ?: ""

    fun getCrashInfo(): CrashInfo {
        val crashReason = getCrashReason()
        val splitData = crashReason.split(DELIMITER)
        val exceptionName = splitData.first().trim()
        val stackTrace = splitData.drop(1).joinToString(DELIMITER)

        val title = "[Bug] App Crash: $exceptionName"

        val device = "$MODEL ($BRAND - $DEVICE)"

        val sdk = "$SDK_INT (Android $RELEASE)"

        val appVersion = "$AppVersion ($VERSION_CODE)"

        val flavor = FLAVOR

        val locale = AppCompatDelegate.getApplicationLocales().getDisplayName()

        val deviceInfo = listOf(
            "Device: $device",
            "SDK: $sdk",
            "App Version: $appVersion",
            "Flavor: $flavor",
            "Locale: $locale"
        ).joinToString("\n")

        val body = listOf(deviceInfo, stackTrace).joinToString(DELIMITER)

        return CrashInfo(
            title = title,
            body = body,
            exceptionName = exceptionName,
            stackTrace = stackTrace
        )
    }

    companion object {
        internal const val EXCEPTION_EXTRA = "EXCEPTION_EXTRA"

        fun getCrashInfoAsExtra(
            throwable: Throwable
        ): String {
            val exceptionName = throwable::class.java.simpleName
            val stackTrace = Log.getStackTraceString(throwable)

            return listOf(exceptionName, stackTrace).joinToString(DELIMITER)
        }
    }
}

data class CrashInfo(
    val title: String,
    val body: String,
    val exceptionName: String,
    val stackTrace: String
) {
    val textToSend = listOf(title, body).joinToString(DELIMITER)
    val githubLink = "$ISSUE_TRACKER/new?title=$title&body=$body"
}

private const val DELIMITER = "\n\n"