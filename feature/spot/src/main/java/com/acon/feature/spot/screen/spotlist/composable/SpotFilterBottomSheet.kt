package com.acon.feature.spot.screen.spotlist.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import com.acon.core.designsystem.blur.defaultHazeEffect
import com.acon.core.designsystem.component.chip.AconChipFlowRow
import com.acon.core.designsystem.noRippleClickable
import com.acon.core.designsystem.theme.AconTheme
import com.acon.domain.model.spot.Condition
import com.acon.domain.type.OptionType
import com.acon.domain.type.SpotType
import com.acon.feature.spot.R
import com.acon.feature.spot.getNameResId
import dev.chrisbanes.haze.HazeState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpotFilterBottomSheet(
    hazeState: HazeState,
    condition: Condition?,
    onComplete: (Condition) -> Unit,
    onReset: () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {

    var selectedSpotType by rememberSaveable { mutableStateOf(condition?.spotType ?: SpotType.RESTAURANT) }
    var selectedRestaurantFeatureIndexes by rememberSaveable { mutableStateOf(listOf<Int>()) }
    var selectedCafeFeatureIndexes by rememberSaveable { mutableStateOf(listOf<Int>()) }
    var selectedCompanionTypeIndexes by rememberSaveable { mutableStateOf(listOf<Int>()) }
    var selectedVisitPurposeIndexes by rememberSaveable { mutableStateOf(listOf<Int>()) }

    ModalBottomSheet(
        containerColor = AconTheme.color.Dim_b_60,
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        dragHandle = null
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(AconTheme.color.Dim_b_60)
                .defaultHazeEffect(hazeState, tintColor = AconTheme.color.Dim_b_60)
        ) {
            Spacer(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 8.dp)
                    .clip(CircleShape)
                    .size(width = 36.dp, height = 5.dp)
                    .background(AconTheme.color.Gray5)
            )
            Box(
                modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.filter_detail),
                    style = AconTheme.typography.head8_16_sb,
                    color = AconTheme.color.White
                )
                Icon(
                    imageVector = ImageVector.vectorResource(com.acon.core.designsystem.R.drawable.ic_dissmiss_16),
                    contentDescription = stringResource(com.acon.core.designsystem.R.string.dismiss_content_description),
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 24.dp)
                        .noRippleClickable {
                            onDismissRequest()
                        },
                    tint = AconTheme.color.White
                )
            }
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 53.dp)
            ) {
                Text(
                    text = stringResource(R.string.visit_spot),
                    style = AconTheme.typography.subtitle2_14_med,
                    color = AconTheme.color.White,
                )
                SpotTypeTabRow(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .padding(top = 12.dp)
                        .background(AconTheme.color.Gray8)
                        .padding(4.dp),
                    selectedSpotType = selectedSpotType,
                    onSpotTypeSelected = {
                        selectedSpotType = it
                        when (selectedSpotType) {
                            SpotType.RESTAURANT -> {
                                selectedCafeFeatureIndexes = listOf()
                                selectedVisitPurposeIndexes = listOf()
                            }
                            SpotType.CAFE -> {
                                selectedRestaurantFeatureIndexes = listOf()
                                selectedCompanionTypeIndexes = listOf()
                            }
                        }
                    }
                )

                when (selectedSpotType) {
                    SpotType.RESTAURANT -> {
                        AconChipFlowRow(
                            modifier = Modifier.padding(top = 12.dp),
                            titles = OptionType.RestaurantFeatureOptionType.entries.map {
                                stringResource(
                                    it.getNameResId()
                                )
                            },
                            selectedChipIndexes = selectedRestaurantFeatureIndexes.toIntArray(),
                            onChipSelected = {
                                selectedRestaurantFeatureIndexes =
                                    if (selectedRestaurantFeatureIndexes.contains(it)) {
                                        selectedRestaurantFeatureIndexes - it
                                    } else {
                                        selectedRestaurantFeatureIndexes + it
                                    }
                            }
                        )

                        Text(
                            modifier = Modifier.padding(top = 32.dp),
                            text = stringResource(R.string.people_together),
                            style = AconTheme.typography.subtitle2_14_med,
                            color = AconTheme.color.White,
                        )
                        AconChipFlowRow(
                            modifier = Modifier.padding(top = 12.dp),
                            titles = OptionType.CompanionTypeOptionType.entries.map {
                                stringResource(
                                    it.getNameResId()
                                )
                            },
                            selectedChipIndexes = selectedCompanionTypeIndexes.toIntArray(),
                            onChipSelected = {
                                selectedCompanionTypeIndexes =
                                    if (selectedCompanionTypeIndexes.contains(it)) {
                                        selectedCompanionTypeIndexes - it
                                    } else {
                                        selectedCompanionTypeIndexes + it
                                    }
                            }
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }

                    SpotType.CAFE -> {
                        AconChipFlowRow(
                            modifier = Modifier.padding(top = 12.dp),
                            titles = OptionType.CafeFeatureOptionType.entries.map {
                                stringResource(
                                    it.getNameResId()
                                )
                            },
                            selectedChipIndexes = selectedCafeFeatureIndexes.toIntArray(),
                            onChipSelected = {
                                selectedCafeFeatureIndexes =
                                    if (selectedCafeFeatureIndexes.contains(it)) {
                                        selectedCafeFeatureIndexes - it
                                    } else {
                                        selectedCafeFeatureIndexes + it
                                    }
                            }
                        )

                        Text(
                            modifier = Modifier.padding(top = 32.dp),
                            text = stringResource(R.string.spot_purpose),
                            style = AconTheme.typography.subtitle2_14_med,
                            color = AconTheme.color.White,
                        )
                        AconChipFlowRow(
                            modifier = Modifier.padding(top = 12.dp),
                            titles = OptionType.VisitPurposeOptionType.entries.map {
                                stringResource(
                                    it.getNameResId()
                                )
                            },
                            selectedChipIndexes = selectedVisitPurposeIndexes.toIntArray(),
                            onChipSelected = {
                                selectedVisitPurposeIndexes =
                                    if (selectedVisitPurposeIndexes.contains(it)) {
                                        selectedVisitPurposeIndexes - it
                                    } else {
                                        selectedVisitPurposeIndexes + it
                                    }
                            }
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
private fun SpotTypeTabRow(
    selectedSpotType: SpotType,
    onSpotTypeSelected: (SpotType) -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier,
    ) {
        SpotType.entries.fastForEach {
            SpotTypeTab(
                spotType = it,
                modifier = Modifier
                    .weight(1f)
                    .noRippleClickable {
                        onSpotTypeSelected(it)
                    },
                isSelected = selectedSpotType == it
            )
        }
    }
}

@Composable
private fun SpotTypeTab(
    spotType: SpotType,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(if (isSelected) AconTheme.color.White else AconTheme.color.Gray8),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(vertical = 6.dp),
            text = stringResource(spotType.getNameResId()),
            style = AconTheme.typography.subtitle2_14_med,
            color = if (isSelected) AconTheme.color.Gray9 else AconTheme.color.Gray5
        )
    }
}

@Preview
@Composable
private fun SpotFilterBottomSheetPreview() {
    SpotFilterBottomSheet(
        hazeState = HazeState(),
        onDismissRequest = {},
        condition = null,
        onComplete = {},
        onReset = {}
    )
}