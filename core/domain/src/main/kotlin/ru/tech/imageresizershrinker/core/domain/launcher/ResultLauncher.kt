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

package ru.tech.imageresizershrinker.core.domain.launcher

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface ResultLauncher<I, O> {

    fun launch(input: I)

    fun getResult(): Flow<O?>

}

inline fun <I, O, R> ResultLauncher<I, O?>.map(
    crossinline transform: (O?) -> R?
) = object : ResultLauncher<I, R?> {

    override fun launch(input: I) = this@map.launch(input)

    override fun getResult(): Flow<R?> = this@map.getResult().map { transform(it) }

}