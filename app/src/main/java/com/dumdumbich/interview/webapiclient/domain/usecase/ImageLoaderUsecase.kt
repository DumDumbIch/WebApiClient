package com.dumdumbich.interview.webapiclient.domain.usecase


interface ImageLoaderUsecase<T> {
    fun loadImage(imageUrl: String, container: T)
}