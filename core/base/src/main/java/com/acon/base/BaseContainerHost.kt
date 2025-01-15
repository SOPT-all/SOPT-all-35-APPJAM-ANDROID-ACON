package com.acon.base

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.Syntax

abstract class BaseContainerHost<STATE : Any, SIDE_EFFECT : Any>(

): ContainerHost<STATE, SIDE_EFFECT>, ViewModel() {

    protected suspend inline fun<T> Result<T>.reduceResult(
        syntax: Syntax<STATE, SIDE_EFFECT>,
        crossinline onSuccess: (T) -> STATE,
        crossinline onFailure: (Throwable) -> STATE
    ) : Result<T> {
        return with(syntax) {
            this@reduceResult.onSuccess {
                reduce { onSuccess(it) }
            }.onFailure {
                reduce { onFailure(it) }
            }
        }
    }
}