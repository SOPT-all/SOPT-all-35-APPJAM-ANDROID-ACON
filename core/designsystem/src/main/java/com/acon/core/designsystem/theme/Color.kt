package com.acon.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

internal val AconColors = AconColor(

    //Main
    Main_org0_deep = Color(0xFFF24B00),
    Main_org0 = Color(0xFFFF5402),
    Main_org1 = Color(0xFFFF6928),
    Main_org2 = Color(0xFFFF8950),
    Main_org3 = Color(0xFFFFAA7C),
    Main_org4 = Color(0xFFFFCCAD),
    Main_org5 = Color(0xFFFFEEE3),
    Main_org50 = Color(0x80FF6928),
    Main_org35 = Color(0x59FF8950),

    //Gray Scale
    White = Color(0xFFFFFFFF),
    Gray0 = Color(0xFFF7F7FB),
    Gray1 = Color(0xFFEFF0F4),
    Gray2 = Color(0xFFD9DADD),
    Gray3 = Color(0xFFC5C6CB),
    Gray4 = Color(0xFF93959D),
    Gray5 = Color(0xFF5E6068),
    Gray6 = Color(0xFF37383E),
    Gray7 = Color(0xFF323339),
    Gray8 = Color(0xFF2B2C31),
    Gray9 = Color(0xFF1A1B1E),
    Black = Color(0xFF000000),
    Dim_b_60 = Color(0x99000000),
    Dim_b_30 = Color(0x4D000000),
    Dim_g_30 = Color(0xFF93959D),
    Gla_w_30 = Color(0x4DFFFFFF),
    Gla_w_20 = Color(0x33FFFFFF),
    Gla_w_10 = Color(0x1AFFFFFF),

    Fab_shaodw_1 = Color(0x1A000000),

    Dim_gra_1 = Brush.horizontalGradient(
        colors = listOf(Color(0x0D000000), Color(0x00000000), Color(0x0D000000))
    ),
    Dim_gra_2 = Brush.verticalGradient(
        colors = listOf(Color(0x80111111), Color(0x00111111), Color(0xCC111111))
    ),

    //Error Case
    Error_red1 = Color(0xFFFF3434),
    Error_red2 = Color(0xFFFFD9D9),
    Success_blue1 = Color(0xFF4375FF),
    Error_blue2 = Color(0xFFD2DEFF)
)

@Immutable
data class AconColor(

    val Main_org0_deep: Color,
    val Main_org0: Color,
    val Main_org1: Color,
    val Main_org2: Color,
    val Main_org3: Color,
    val Main_org4: Color,
    val Main_org5: Color,
    val Main_org50: Color,
    val Main_org35: Color,

    val White: Color,
    val Gray0: Color,
    val Gray1: Color,
    val Gray2: Color,
    val Gray3: Color,
    val Gray4: Color,
    val Gray5: Color,
    val Gray6: Color,
    val Gray7: Color,
    val Gray8: Color,
    val Gray9: Color,
    val Black: Color,
    val Dim_b_60: Color,
    val Dim_b_30: Color,
    val Dim_g_30: Color,
    val Gla_w_30: Color,
    val Gla_w_20: Color,
    val Gla_w_10: Color,

    val Fab_shaodw_1: Color,

    val Dim_gra_1: Brush,
    val Dim_gra_2: Brush,

    val Error_red1: Color,
    val Error_red2: Color,
    val Success_blue1: Color,
    val Error_blue2: Color,

)

internal val LocalAconColor = staticCompositionLocalOf {
    AconColors
}