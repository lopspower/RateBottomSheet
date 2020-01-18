<p align="center"><img src="/preview/header.png"></p>

RateBottomSheet
=================

<img src="/preview/preview.gif" alt="sample" title="sample" width="250" height="509" align="right" />

[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16)
<br>
[![Twitter](https://img.shields.io/badge/Twitter-@LopezMikhael-blue.svg?style=flat)](http://twitter.com/lopezmikhael)

This an Android library to help to promote your Android App by prompting users to **rate** your app in the Google Play Store with a material design friendly **BottomSheet**.

<a href="https://play.google.com/store/apps/details?id=com.mikhaellopez.lopspower">
  <img alt="Android app on Google Play" src="https://developer.android.com/images/brand/en_app_rgb_wo_45.png" />
</a>

USAGE
-----

To used this usefull library you can grab it via Gradle:

```groovy
implementation 'com.mikhaellopez:ratebottomsheet:1.0.0'
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
    .setDebugLogEnable(true) // False by default
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

SUPPORT ❤️
-----

Find this library useful? Support it by joining [**stargazers**](https://github.com/lopspower/RateBottomSheet/stargazers) for this repository ⭐️
<br/>
And [**follow me**](https://github.com/lopspower?tab=followers) for my next creations 👍

LICENCE
-----

RateBottomSheet by [Lopez Mikhael](http://mikhaellopez.com/) is licensed under a [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).
