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

package com.t8rin.imagetoolbox.feature.filters.data.model

import android.graphics.Bitmap
import com.awxkee.aire.Aire
import com.awxkee.aire.ConvolveKernels
import com.awxkee.aire.EdgeMode
import com.awxkee.aire.KernelShape
import com.awxkee.aire.MorphOpMode
import com.awxkee.aire.Scalar
import com.t8rin.imagetoolbox.core.domain.model.IntegerSize
import com.t8rin.imagetoolbox.core.domain.transformation.Transformation
import com.t8rin.imagetoolbox.core.filters.domain.model.Filter

internal class RingBlurFilter(
    override val value: Float = 25f,
) : Transformation<Bitmap>, Filter.RingBlur {

    override val cacheKey: String
        get() = (value).hashCode().toString()

    override suspend fun transform(
        input: Bitmap,
        size: IntegerSize
    ): Bitmap = Aire.convolve2D(
        bitmap = input,
        kernel = ConvolveKernels.ring(value.toInt()),
        kernelShape = KernelShape(value.toInt(), value.toInt()),
        edgeMode = EdgeMode.REFLECT_101,
        scalar = Scalar.ZEROS,
        mode = MorphOpMode.RGBA
    )

}