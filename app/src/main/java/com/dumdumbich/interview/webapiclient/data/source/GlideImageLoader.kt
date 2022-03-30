package com.dumdumbich.interview.webapiclient.data.source

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.dumdumbich.interview.webapiclient.domain.usecase.ImageLoaderUsecase


class GlideImageLoader: ImageLoaderUsecase<ImageView> {

    override fun loadImage(imageUrl: String, container: ImageView) {
        Glide.with(container.context)
            .load(imageUrl)
            .into(container)
    }

}