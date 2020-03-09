package com.abli.namelearn.service;

import com.abli.namelearn.domain.GetImage;

public interface ImageDownloader {
    byte[] downloadImage(GetImage request);
}
