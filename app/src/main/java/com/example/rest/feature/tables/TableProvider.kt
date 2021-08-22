package com.example.rest.feature.tables

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class TableProvider : PreviewParameterProvider<String> {

    override val values: Sequence<String>
        get() = listOf("12").asSequence()

    override val count: Int
        get() = values.count()
}