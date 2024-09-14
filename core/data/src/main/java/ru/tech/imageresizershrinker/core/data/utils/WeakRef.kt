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

package ru.tech.imageresizershrinker.core.data.utils

import java.lang.ref.WeakReference
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal class WeakRef<T>(obj: T? = null) : ReadWriteProperty<Any?, T?> {

    private var wref: WeakReference<T>?

    init {
        this.wref = obj?.let { WeakReference(it) }
    }

    override fun getValue(
        thisRef: Any?,
        property: KProperty<*>
    ): T? {
        return wref?.get()
    }

    override fun setValue(
        thisRef: Any?,
        property: KProperty<*>,
        value: T?
    ) {
        wref = value?.let { WeakReference(it) }
    }
}

internal fun <T> weak(obj: T? = null) = WeakRef(obj)