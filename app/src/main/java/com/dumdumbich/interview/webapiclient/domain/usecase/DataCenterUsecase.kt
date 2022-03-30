package com.dumdumbich.interview.webapiclient.domain.usecase

import android.widget.ImageView


interface DataCenterUsecase :
    GetUserUsecase,
    GetRepositoryUsecase,
    PutUserUsecase,
    PutRepositoryUsecase,
    ImageLoaderUsecase<ImageView>