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

package com.t8rin.imagetoolbox.feature.draw.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.t8rin.imagetoolbox.core.resources.R
import com.t8rin.imagetoolbox.core.resources.icons.Eyedropper
import com.t8rin.imagetoolbox.core.ui.theme.mixedContainer
import com.t8rin.imagetoolbox.core.ui.theme.onMixedContainer
import com.t8rin.imagetoolbox.core.ui.widget.enhanced.hapticsClickable
import com.t8rin.imagetoolbox.core.ui.widget.modifier.ShapeDefaults
import com.t8rin.imagetoolbox.core.ui.widget.modifier.container

@Composable
fun OpenColorPickerCard(
    onOpen: () -> Unit,
    modifier: Modifier = Modifier
        .padding(horizontal = 16.dp)
) {
    Row(
        modifier = modifier
            .container(
                resultPadding = 0.dp,
                color = MaterialTheme.colorScheme.mixedContainer.copy(0.7f),
                shape = ShapeDefaults.extraLarge
            )
            .hapticsClickable(onClick = onOpen)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.pipette),
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.onMixedContainer
        )
        Icon(
            imageVector = Icons.Outlined.Eyedropper,
            contentDescription = stringResource(R.string.pipette),
            tint = MaterialTheme.colorScheme.onMixedContainer
        )
    }
}