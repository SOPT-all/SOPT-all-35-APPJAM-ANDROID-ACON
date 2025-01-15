package com.acon.feature.onboarding.screen.UnlikeFoodSelectScreen

import com.acon.base.BaseContainerHost
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

const val ONBOARDING_TOTAL_PAGES = 5;

@HiltViewModel
class UnlikeFoodScreenViewModel @Inject constructor(
) : BaseContainerHost<UnlikeFoodScreenState, UnlikeFoodScreenSideEffect>() {

    override val container: Container<UnlikeFoodScreenState, UnlikeFoodScreenSideEffect> =
        container(
            initialState = UnlikeFoodScreenState(  )
        )

    fun onCardClicked(text: String) = intent {

        if (text == "없음") {
            reduce {
                state.copy(
                    isNothingClicked = !state.isNothingClicked,
                    selectedCard = if (!state.isNothingClicked) setOf(text) else state.selectedCard
                )
            }
        } else {
            val updatedSelectedCard = if (state.selectedCard.contains(text)) {
                state.selectedCard - text
            } else {
                state.selectedCard + text
            }

            reduce {
                state.copy(
                    selectedCard = updatedSelectedCard,
                    isNothingClicked = false
                )
            }
        }
    }

    fun showDialog() = intent {
        reduce {
            state.copy(openCloseDialog = true)
        }
    }

    fun hideDialog() = intent {
        reduce {
            state.copy(openCloseDialog = false)
        }
    }

    fun navigateToNextPage() = intent {
        postSideEffect(UnlikeFoodScreenSideEffect.NavigateToNextPage)
    }
}

data class UnlikeFoodScreenState(
    val isNothingClicked: Boolean = false,
    val selectedCard: Set<String> = emptySet(),
    val totalPages: Int = ONBOARDING_TOTAL_PAGES,
    val currentPage: Int = 0,
    val openCloseDialog: Boolean = false,
)

sealed interface UnlikeFoodScreenSideEffect {
    data object NavigateToNextPage: UnlikeFoodScreenSideEffect
}