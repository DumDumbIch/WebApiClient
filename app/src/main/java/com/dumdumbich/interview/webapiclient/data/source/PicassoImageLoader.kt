package com.dumdumbich.interview.webapiclient.data.source

import android.widget.ImageView
import com.dumdumbich.interview.webapiclient.domain.usecase.ImageLoaderUsecase
import com.squareup.picasso.Picasso


class PicassoImageLoader: ImageLoaderUsecase<ImageView> {

    override fun loadImage(imageUrl: String, container: ImageView) {
        Picasso.get()
            .load(imageUrl)
            .into(container)
    }

}