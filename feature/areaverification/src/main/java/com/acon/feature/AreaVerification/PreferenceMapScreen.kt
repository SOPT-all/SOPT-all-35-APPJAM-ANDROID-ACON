package com.acon.feature.areaverification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.acon.core.designsystem.component.button.AconFilledLargeButton
import com.acon.core.designsystem.theme.AconTheme
import com.acon.feature.areaverification.component.DottoriSelectionBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreferenceMapScreen(
    onConfirmClick: () -> Unit,
    onNavigateToNext: () -> Unit,
    latitude: Double,
    longitude: Double,
    modifier: Modifier = Modifier
) {
    var showBottomSheet by remember { mutableStateOf(false) }

    if (showBottomSheet) {
        val sheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = true,
        )

        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            containerColor = Color.Transparent,
            dragHandle = null,
            sheetState = sheetState,
        ) {
            DottoriSelectionBottomSheet(
                onDismiss = { showBottomSheet = false },
                onNavigateToNext = onNavigateToNext
            )
        }
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AconTheme.color.Gray9)
    ) {
        Text(
            text = "지도에서 위치 확인하기",
            style = AconTheme.typography.head6_20_sb,
            color = AconTheme.color.Gray1,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            LocationMapScreen(
                onLocationObtained = { _, _ -> },
                initialLatitude = latitude,
                initialLongitude = longitude,
                modifier = Modifier.fillMaxSize()
            )
        }

        AconFilledLargeButton(
            text = "인증완료",
            textStyle = AconTheme.typography.head8_16_sb,
            enabledBackgroundColor = AconTheme.color.Gray5,
            disabledBackgroundColor = AconTheme.color.Gray8,
            enabledTextColor = AconTheme.color.White,
            onClick = { showBottomSheet = true },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        )
    }
}
