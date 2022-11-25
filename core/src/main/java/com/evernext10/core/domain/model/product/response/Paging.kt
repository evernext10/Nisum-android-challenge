package com.evernext10.core.domain.model.product.response

data class Paging(
    val total: Int = 0,
    val offset: Int = 0,
    val limit: Int = 0,
    val primary_results: Int = 0
)
