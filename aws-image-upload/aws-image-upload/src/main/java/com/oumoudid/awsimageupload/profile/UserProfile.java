package com.oumoudid.awsimageupload.profile;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class UserProfile {

    private UUID userId;
    private String userName;
    private String imageLink; // s3 key

    public UserProfile(UUID userId, String userName, String imageLink) {
        this.userId = userId;
        this.userName = userName;
        this.imageLink = imageLink;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public Optional<String> getImageLink() {
        return Optional.ofNullable(imageLink);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return Objects.equals(userId, that.userId) && Objects.equals(userName, that.userName) && Objects.equals(imageLink, that.imageLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, imageLink);
    }
}
