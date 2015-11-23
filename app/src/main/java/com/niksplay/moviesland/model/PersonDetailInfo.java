package com.niksplay.moviesland.model;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;
import com.niksplay.moviesland.model.response.CreditsResponse;

/**
 * Created by nikita on 23.11.15.
 */
public class PersonDetailInfo extends Person {

    @SerializedName("combined_credits")
    public CreditsResponse combinedCredits;

    public PersonDetailInfo(Parcel parcel) {
        super(parcel);
    }


}
