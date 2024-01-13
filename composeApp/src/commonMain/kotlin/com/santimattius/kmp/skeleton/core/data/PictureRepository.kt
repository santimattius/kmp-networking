package com.santimattius.kmp.skeleton.core.data

import com.santimattius.kmp.skeleton.core.network.PictureService
import com.santimattius.kmp.skeleton.core.domain.Picture as DomainPicture

private fun Picture.asDomain(): DomainPicture {
    return DomainPicture(this.id, this.author, this.downloadURL)
}

class PictureRepository(
    private val service: PictureService,
) {
    suspend fun random() = runCatching {
        service.random().asDomain()
    }
}