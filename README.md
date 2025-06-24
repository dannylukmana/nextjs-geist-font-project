# Virtual Tour Android App

This Android application shows nearby virtual tour locations, calculates distances using GPS, and allows viewing the tours in a WebView.

## Features
- GPS location tracking
- Distance calculation using Haversine formula
- Sorted list of virtual tours by proximity
- Material Design UI
- WebView integration for tour viewing

## Setup Instructions
1. Open Android Studio
2. Select "Open an existing Android Studio project"
3. Navigate to the downloaded project folder and click "OK"
4. Wait for Gradle sync to complete
5. Run the app on an emulator or physical device

## Requirements
- Android Studio Arctic Fox or newer
- Minimum SDK: 24 (Android 7.0)
- Target SDK: 34 (Android 14)
- Kotlin version: 1.8+

## Dependencies
- AndroidX Core KTX
- Google Play Services Location
- Glide for image loading
- Material Design Components
- ConstraintLayout

## Project Structure
```
app/
├── build.gradle
├── src/
│   └── main/
│       ├── AndroidManifest.xml
│       ├── java/
│       │   └── com/
│       │       └── example/
│       │           └── virtualtour/
│       │               ├── MainActivity.kt
│       │               ├── VirtualTour.kt
│       │               ├── VirtualTourAdapter.kt
│       │               ├── VirtualTourWebViewActivity.kt
│       │               └── Utils.kt
│       └── res/
│           └── layout/
│               ├── activity_main.xml
│               ├── activity_virtual_tour_webview.xml
│               └── item_virtual_tour.xml
```

## Usage
1. Launch the app
2. Grant location permissions when prompted
3. View nearby virtual tours sorted by distance
4. Tap on any tour to open it in the WebView

## Customization
To add your own virtual tours, modify the `loadVirtualTours()` method in `MainActivity.kt` with your tour data:

```kotlin
virtualTourList.add(
    VirtualTour(
        title = "Your Tour Name",
        latitude = YOUR_LATITUDE,
        longitude = YOUR_LONGITUDE,
        url = "YOUR_VIRTUAL_TOUR_URL",
        thumbnailUrl = "YOUR_THUMBNAIL_IMAGE_URL"
    )
)
