package com.oumoudid.awsimageupload.fakeDatastorefortest;

import com.oumoudid.awsimageupload.profile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Repository
public class FakeUserProfileDataStore {

    private static final List<UserProfile> USER_PROFILE = new ArrayList<>();

    static {
        USER_PROFILE.add(new UserProfile(UUID.randomUUID(), "othmane", null));
        USER_PROFILE.add(new UserProfile(UUID.randomUUID(), "oumoudid", null));
    }

    public  List<UserProfile> getUserProfiles() {
        return USER_PROFILE;
    }
}
