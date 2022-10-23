package com.spaceapp.data.mappers

import com.spaceapp.data.datasource.local.glossary_data.entity.GlossaryDto
import com.spaceapp.domain.model.Glossary
import com.spaceapp.domain.model.GlossaryContent

fun GlossaryDto.toGlossary(): Glossary {
    return Glossary(
        glossary = glossary.map { content ->
            GlossaryContent(
                name = content.name,
                description = content.description
            )
        }
    )
}