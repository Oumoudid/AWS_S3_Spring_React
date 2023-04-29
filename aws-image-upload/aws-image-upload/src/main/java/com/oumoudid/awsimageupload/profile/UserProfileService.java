package com.oumoudid.awsimageupload.profile;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.oumoudid.awsimageupload.StoreFile.FileStore;
import com.oumoudid.awsimageupload.bucket.BucketName;
import jakarta.activation.MimetypesFileTypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

@Service
public class UserProfileService {

    private final UserProfileDataAccessService userProfileDataAccessService;
    private final FileStore fileStore;
    @Autowired
    public UserProfileService(UserProfileDataAccessService userProfileDataAccessService, FileStore fileStore) {
        this.userProfileDataAccessService = userProfileDataAccessService;
        this.fileStore = fileStore;
    }

    List<UserProfile> getUserProfiles() {
        return userProfileDataAccessService.getUserProfiles();
    }

    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
        isFileEmpty(file);
        isImage(file);
        isUserExist(userProfileId);

        Map<String, String> metadata = extractMetadata(file);

        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), userProfileId);
        String fileName = String.format("%s-%s", file.getName(), UUID.randomUUID());

        try{
            fileStore.save(path, fileName, Optional.of(metadata), file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private static Map<String, String> extractMetadata(MultipartFile file) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        return metadata;
    }

    private void isUserExist(UUID userProfileId) {
        if (userProfileDataAccessService.getUserProfiles().stream().anyMatch(user ->
                user.getUserId().equals(userProfileId)
        )) {
            throw new IllegalStateException("User with id = " + userProfileId + " does not exist");
        }
    }

    private static void isImage(MultipartFile file) {
        List<String> imageTypes = Arrays.asList("PNG", "JPEG", "JPG");
        int index = file.getOriginalFilename().lastIndexOf('.');
        if (!imageTypes.contains(file.getOriginalFilename().substring(index + 1))) {
            throw new IllegalStateException("Please choose a image file to upload");
        }
    }

    private static void isFileEmpty(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
    }
}
