package com.oumoudid.awsimageupload;

import com.oumoudid.awsimageupload.profile.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@SpringBootApplication
public class AwsImageUploadApplication implements CommandLineRunner {
    @Autowired
    private UserProfileService userProfileService;

    public static void main(String[] args) {
        SpringApplication.run(AwsImageUploadApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        File file = new File("C:\\Programing\\AWS_S3/image.PNG");
        byte [] content = null;
        try {
            content = Files.readAllBytes(Path.of(file.getAbsolutePath()));
        } catch (Exception e){
            throw new IllegalStateException("NULL");
        }
        MultipartFile result = new MockMultipartFile(file.getName(),file.getName(), "text/plain", content );
        userProfileService.uploadUserProfileImage(UUID.randomUUID(), result);
    }
}
