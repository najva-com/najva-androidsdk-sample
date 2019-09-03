# najva Android Studio Application

This is an application that implemented by Android Studio and `NajvaSDK` has been used in it.

### Test Najva Service 
If you want test `Najva Push Notification Service` in `android` application you should:
1.  Clone this project and rename package name of it.
this [link](https://stackoverflow.com/questions/16804093/rename-package-in-android-studio) can be useful.

2.  Register this app after login in [najva panel](https://app.najva.com/accounts/login/?next=/).(to register any app, its package name must be unique!)

3.  After register najva panel gives you `websiteId`,`apiKey` which is specific to your app

4.  Go to `AndroidManifest.xml` and put this parameters in `application` tag that looks like the following:
```
<meta-data
    android:name="com.najva.sdk.metadata.API_KEY"
    android:value="[YOUR_API_KEY_GOES_HERE]" />
<meta-data
    android:name="com.najva.sdk.metadata.WEBSITE_ID"
    android:value="[YOUR_WEBSITE_ID_GOES_HERE]" />
```

5.  Now you can run application and send notification from your panel to it!