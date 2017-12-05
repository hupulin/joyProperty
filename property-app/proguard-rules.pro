# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/warrior/sdk/android/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-keep class com.jinyi.home.**{*;}
-keep public class com.jinyi.home.R$*{
      public static int * ;
}
 -keep class lombok.**{*;}
 -dontwarn lombok.**
 -keep class retrofit.**{*;}
 -dontwarn retrofit.**
 -keep class com.fasterxml.jackson.databind.**{*;}
 -dontwarn com.fasterxml.jackson.databind.**
 -keep class com.squareup.okhttp.**{*;}
 -dontwarn com.squareup.okhttp.**
 -keep class okio.**{*;}
 -dontwarn okio.**
 -keep class okio.**{*;}
 -dontwarn okio.**
 -keep class com.umeng.analytics.**{*;}
 -dontwarn com.umeng.analytics.**
 -keep class com.lidroid.xutils.**{*;}
 -dontwarn com.lidroid.xutils.**
