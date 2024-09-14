/*
 * ImageToolbox is an image editor for android
 * Copyright (c) 2024 T8RIN (Malik Mukhametzyanov)
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

package ru.tech.imageresizershrinker.core.data.launcher

import android.content.Context
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.tech.imageresizershrinker.core.domain.dispatchers.DispatchersHolder
import ru.tech.imageresizershrinker.core.domain.launcher.ResultLauncher


internal abstract class AndroidResultLauncher<I, O>(
    private val contract: ActivityResultContract<I, O>,
    private val activityContext: Context,
    dispatchersHolder: DispatchersHolder
) : ResultLauncher<I, O>, DefaultLifecycleObserver, DispatchersHolder by dispatchersHolder {

    private val scope get() = CoroutineScope(defaultDispatcher)

    private var launcher: ActivityResultLauncher<I>? = null

    private var resultChannel: Channel<O?> = Channel(Channel.BUFFERED)

    private val observer = object : DefaultLifecycleObserver {
        override fun onCreate(owner: LifecycleOwner) {
            super.onCreate(owner)
            if (activityContext is AppCompatActivity) {
                launcher = activityContext.registerForActivityResult(contract) {
                    scope.launch {
                        resultChannel.send(it)
                        resultChannel.close()
                        currentCoroutineContext().cancel()
                    }
                }
            }
        }

        override fun onDestroy(owner: LifecycleOwner) {
            super.onDestroy(owner)
            if (activityContext is AppCompatActivity) {
                activityContext.lifecycle.removeObserver(this)
            }
        }
    }

    init {
        if (activityContext is AppCompatActivity) {
            activityContext.lifecycle.addObserver(observer)
        }
    }

    override fun launch(input: I) {
        resultChannel = Channel(Channel.BUFFERED)
        launcher?.launch(input)
    }

    override fun getResult(): Flow<O?> = resultChannel.receiveAsFlow()

}