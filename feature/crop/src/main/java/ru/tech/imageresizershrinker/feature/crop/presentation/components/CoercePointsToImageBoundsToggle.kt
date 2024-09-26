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

package ru.tech.imageresizershrinker.feature.crop.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Rectangle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.tech.imageresizershrinker.core.resources.R
import ru.tech.imageresizershrinker.core.ui.widget.preferences.PreferenceRowSwitch

@Composable
fun CoercePointsToImageBoundsToggle(
    value: Boolean,
    onValueChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    PreferenceRowSwitch(
        checked = value,
        onClick = onValueChange,
        title = stringResource(R.string.coerce_points_to_image_bounds),
        subtitle = stringResource(R.string.coerce_points_to_image_bounds_sub),
        startIcon = Icons.Outlined.Rectangle,
        modifier = modifier,
        shape = RoundedCornerShape(24.dp)
    )
}