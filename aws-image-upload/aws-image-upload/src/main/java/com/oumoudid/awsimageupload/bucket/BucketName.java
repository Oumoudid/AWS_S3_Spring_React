package com.oumoudid.awsimageupload.bucket;

public enum BucketName {

    PROFILE_IMAGE("oumoudid-upload-images");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
