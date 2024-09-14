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
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContracts
import dagger.hilt.android.qualifiers.ActivityContext
import ru.tech.imageresizershrinker.core.domain.dispatchers.DispatchersHolder
import ru.tech.imageresizershrinker.core.domain.launcher.CreateDocumentLauncher
import javax.inject.Inject

internal class AndroidCreateDocumentLauncher @Inject constructor(
    @ActivityContext private val activityContext: Context,
    dispatchersHolder: DispatchersHolder
) : AndroidResultLauncher<String, Uri?>(
    contract = ActivityResultContracts.CreateDocument("*/*"),
    activityContext = activityContext,
    dispatchersHolder = dispatchersHolder
), CreateDocumentLauncher<String, Uri?>