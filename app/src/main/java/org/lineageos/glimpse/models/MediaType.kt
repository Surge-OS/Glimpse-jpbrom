/*
 * SPDX-FileCopyrightText: 2023 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.lineageos.glimpse.models

import android.net.Uri
import android.provider.MediaStore

enum class MediaType(
    val externalContentUri: Uri,
    val mediaStoreValue: Int,
) {
    IMAGE(
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE,
    ),
    VIDEO(
        MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
        MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO,
    );

    companion object {
        private val dashMimeTypes = listOf(
            "application/dash+xml",
        )

        private val hlsMimeTypes = listOf(
            "application/vnd.apple.mpegurl",
            "application/x-mpegurl",
            "audio/mpegurl",
            "audio/x-mpegurl",
        )

        fun fromMediaStoreValue(value: Int) = values().first {
            value == it.mediaStoreValue
        }

        fun fromMimeType(mimeType: String) = when {
            mimeType.startsWith("image/") -> IMAGE
            mimeType.startsWith("video/") -> VIDEO
            mimeType in dashMimeTypes -> VIDEO
            mimeType in hlsMimeTypes -> VIDEO
            else -> null
        }
    }
}
