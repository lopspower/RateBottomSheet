<p align="center"><img src="/preview/header.png"></p>

<img src="/preview/preview.gif" alt="sample" title="sample" width="250" height="444" align="right" />

RateBottomSheet
=================

[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16)
[![Download](https://api.bintray.com/packages/lopspower/maven/com.mikhaellopez:ratebottomsheet/images/download.svg?version=1.0.3)](https://bintray.com/lopspower/maven/com.mikhaellopez:ratebottomsheet/1.0.3/link)
<br>
[![Twitter](https://img.shields.io/badge/Twitter-@LopezMikhael-blue.svg?style=flat)](http://twitter.com/lopezmikhael)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/0225b845d594403cbf1be79efdcba93b)](https://www.codacy.com/manual/lopspower/RateBottomSheet?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=lopspower/RateBottomSheet&amp;utm_campaign=Badge_Grade)

This an Android library to help to promote your Android App by prompting users to **rate** your app in the Google Play Store with a material design friendly **BottomSheet**.

<a href="https://play.google.com/store/apps/details?id=com.mikhaellopez.lopspower">
  <img alt="Android app on Google Play" src="https://developer.android.com/images/brand/en_app_rgb_wo_45.png" />
</a>

USAGE
-----

To used this usefull library you can grab it via Gradle:

```groovy
implementation 'com.mikhaellopez:ratebottomsheet:1.0.3'
```

KOTLIN
-----

```kotlin
RateBottomSheetManager(this)
    .setInstallDays(1) // 3 by default
    .setLaunchTimes(2) // 5 by default
    .setRemindInterval(1) // 2 by default
    .setShowAskBottomSheet(false) // True by default
    .setShowLaterButton(false) // True by default
    .setShowCloseButtonIcon(false) // True by default
    .monitor()

// Show bottom sheet if meets conditions
// With AppCompatActivity or Fragment
RateBottomSheet.showRateBottomSheetIfMeetsConditions(this)
```

Override string xml resources on your application to change the texts in bottom sheet:

```xml
<resources>
    <string name="rate_popup_ask_title">Like this App?</string>
    <string name="rate_popup_ask_message">Do you like using this application?</string>
    <string name="rate_popup_ask_ok">Yes I do</string>
    <string name="rate_popup_ask_no">Not really</string>

    <string name="rate_popup_title">Rate this app</string>
    <string name="rate_popup_message">Would you mind taking a moment to rate it? It won\'t take more than a minute. Thanks for your support!</string>
    <string name="rate_popup_ok">Rate it now</string>
    <string name="rate_popup_later">Remind me later</string>
    <string name="rate_popup_no">No, thanks</string>
</resources>
```

LISTENER
-----

When calling `RateBottomSheet.showRateBottomSheetIfMeetsConditions(...)` you can choose to add another parameter of type `AskRateBottomSheet.ActionListener`; this allows you to implement 3 optional callbacks.
Here is how:

```kotlin
RateBottomSheet.showRateBottomSheetIfMeetsConditions(
    this,
    listener = object : AskRateBottomSheet.ActionListener {
        override fun onDislikeClickListener() {
            // Will be called when a click on the "I don't like" button is triggered
        }

        override fun onRateClickListener() {
            // Will be called when a click on the "Rate" button is triggered
        }

        /*override fun onNoClickListener() {
            // Will be called when a click on the "No thanks" button is triggered,
            // in this example is commented,
            // but each callback is optional and it's up to you whether to implement it or not!
        }*/
    }
)
```

DEBUG
-----

Enable `debugForceOpen` to show bottom sheet without conditions check like this:

```kotlin
RateBottomSheetManager(this)
    .setDebugForceOpenEnable(true) // False by default

// Don't forget to run showRate function
RateBottomSheet.showRateBottomSheetIfMeetsConditions(this)
```

You can also enable logs with `debugLogEnable` properties:

```kotlin
RateBottomSheetManager(this)
    .setDebugLogEnable(true) // False by default
```

Clear all current data from RateBottomSheet like this:

```kotlin
RateBottomSheetManager(this)
    .clear()
```

JAVA
-----

You can call `showRateBottomSheetIfMeetsConditions` func like this:

```java
RateBottomSheet.Companion.showRateBottomSheetIfMeetsConditions(this);
```

And because this library it's write in Kotlin you need to add **kotlin-stdlib** dependency on your java project:

```groovy
implementation 'org.jetbrains.kotlin:kotlin-stdlib:1.3.71'
```

SUPPORT ❤️
-----

Find this library useful? Support it by joining [**stargazers**](https://github.com/lopspower/RateBottomSheet/stargazers) for this repository ⭐️
<br/>
And [**follow me**](https://github.com/lopspower?tab=followers) for my next creations 👍

LICENCE
-----

RateBottomSheet by [Lopez Mikhael](http://mikhaellopez.com/) is licensed under a [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).
