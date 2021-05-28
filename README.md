<p align="center">
  <a href="https://github.com/rosariopfernandes/MiniBrotherEye/actions"><img alt="Build Status" src="https://github.com/rosariopfernandes/MiniBrotherEye/workflows/Android%20CI%20(Push)/badge.svg"/></a>
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=19"><img alt="API" src="https://img.shields.io/badge/API-19%2B-brightgreen.svg?style=flat"/></a>
</p>
<h1 align="center">Mini Brother Eye</h1>

Mini Brother Eye is a small demo app that tries to follow Modern Android Development best practices
 and uses the latest tools and Open Source Libraries.

It is supposed to be a smaller version of DC's [Brother Eye](https://dc.fandom.com/wiki/Brother_Eye_(New_Earth)).

<p align="center">
<img src="/art/screenshots.png" alt="Three App Screenshots"/>
</p>

## Techstack and Open Source libraries

### Code

- Minimum SDK Level 19
- [Kotlin Coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous operations.
- [Retrofit2](https://github.com/square/retrofit) to make HTTP calls to the REST API.
- [GSON](https://github.com/google/gson) to deserialize JSON requests.
- [Coil](https://github.com/coil-kt/coil) for image loading.
- [Material Components](https://github.com/material-components/material-components-android)
 to display Material Design Components.
- [Material Motion](https://material.io/develop/android/theming/motion/) - transitions for navigation.
- Android Jetpack
    - [DataBinding](https://developer.android.com/topic/libraries/data-binding)
    - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
    - [Navigation Component](https://developer.android.com/guide/navigation)
    - [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)
    - [Room](https://developer.android.com/topic/libraries/architecture/room)
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) (alpha) for
 Dependency Injection
- [RamiJ3mli/PercentageChartView](https://github.com/RamiJ3mli/PercentageChartView) to display
 progress information

### Tests

- [Robolectric](https://github.com/robolectric/robolectric) and
 [AndroidX Test libraries](https://developer.android.com/training/testing) for Unit Testing.
- [Mockito](https://github.com/mockito/mockito) to create the mocks used in the Unit Tests.
- [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver) to mock web server
 calls.


## License

```
Copyright 2020 Rosário Pereira Fernandes

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```