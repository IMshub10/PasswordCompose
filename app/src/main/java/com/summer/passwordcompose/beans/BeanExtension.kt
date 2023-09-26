package com.summer.passwordcompose.beans

import com.summer.passwordcompose.data.entities.TagEntity
import com.summer.passwordcompose.data.entities.VaultEntity

fun TagEntity.toTagBean() =
    TagBean(
        id = id,
        createdAtApp = createdAtApp,
        updatedAtApp = updatedAtApp,
        name = name,
        description = description,
    )

fun TagBean.toTagEntity() =
    TagEntity(
        id = id,
        createdAtApp = createdAtApp,
        updatedAtApp = updatedAtApp,
        name = name,
        description = description,
    )

fun VaultEntity.toVaultBean() =
    VaultBean(
        id = id,
        createdAtApp = createdAtApp,
        updatedAtApp = updatedAtApp,

        entityName = entityName,
        webAddress = webAddress,
        usernameMobileCardNumber = usernameMobileCardNumber,
        password = password,
        notes = notes,
        tagId = tagId
    )

fun VaultBean.toVaultEntity() =
    VaultEntity(
        id = id,
        createdAtApp = createdAtApp,
        updatedAtApp = updatedAtApp,

        entityName = entityName,
        webAddress = webAddress,
        usernameMobileCardNumber = usernameMobileCardNumber,
        password = password,
        notes = notes,
        tagId = tagId
    )

fun List<VaultEntity>.toVaultBeans() = map { it.toVaultBean() }

fun List<TagEntity>.toTagBeans() = map { it.toTagBean() }

fun List<VaultBean>.toVaultEntities() = map { it.toVaultEntity() }

fun List<TagBean>.toTagEntities() = map { it.toTagEntity() }
