package com.codewithsean.instagramclone;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    //Initializes parseSDK as soon as app is created
    @Override
    public void onCreate() {
        super.onCreate();


        ParseObject.registerSubclass(Post.class);

        //register parse models
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("WbLnXtqMvD1qWaUTSj204dlJe5gruWpLNjy5bbYC")
                .clientKey("PdhIZ2dbBoSMQHoTLPoruSAO993VRDjLI29XfZQ5")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
