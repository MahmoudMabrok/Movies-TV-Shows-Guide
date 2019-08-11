
package com.test.ahmedorabi.movieapp.model.appModels.ActorImages;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ActorImages {

    @SerializedName("id")
    private Long mId;
    @SerializedName("profiles")
    private List<Profile> mProfiles;

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public List<Profile> getProfiles() {
        return mProfiles;
    }

    public void setProfiles(List<Profile> profiles) {
        mProfiles = profiles;
    }

}
