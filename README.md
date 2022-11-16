# Space App

SpaceApp is an application where you can find information about the universe. SpaceApp provides you with astronomy photos of the day, news about space, the latest photos from mars, current people in space right now and the weather of your location. SpaceApp contains an information area about space objects and a glossary of space terms.

Once you enter the application, all data retrieved from the internet is saved on the device. So you can use the application without internet. When the application has an internet connection, it automatically updates the contents. Your session remains open until you log out.

## Api 📦
* [OpenWeather Current Weather Data](https://openweathermap.org/current)
* [Where is The Iss](https://api.wheretheiss.at/v1/satellites/25544)
* [People in Space Right Now](http://api.open-notify.org/astros.json)
* [Astronomy Picture of the Day](https://github.com/nasa/apod-api)
* [Mars Rover Photo](https://github.com/chrisccerami/mars-photo-api)
* [Space News](https://rapidapi.com/ishanbagchi/api/spacefo)

🔐 You can enter your API key as specified in local.properties

<img src="https://user-images.githubusercontent.com/73544434/201613551-44ec1631-2776-4177-9fdf-b27e2a6a7ecd.PNG" />

## Tech Stack 📚

* [Navigation](https://developer.android.com/jetpack/compose/navigation)

* [Retrofit](https://square.github.io/retrofit)

* [ViewModel](https://developer.android.com/jetpack/compose/libraries#viewmodel)

* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)

* [Room](https://developer.android.com/jetpack/androidx/releases/room)

* [Accompanist](https://google.github.io/accompanist/insets/)

* [Room](https://developer.android.com/jetpack/androidx/releases/room)

* [Material 3](https://developer.android.com/jetpack/androidx/releases/compose-material3)

* [Coil](https://coil-kt.github.io/coil/image_loaders/)

* [Okhttp](https://square.github.io/okhttp/)

* [Hms Core](https://developer.huawei.com/consumer/en/hms/)

* [Hms Location Kit](https://developer.huawei.com/consumer/en/hms/huawei-locationkit/)

* [Hms Auth Service](https://developer.huawei.com/consumer/en/agconnect/auth-service/)

* [Firebase](https://firebase.google.com/)

* [Firebase Auth Service](https://firebase.google.com/docs/auth/android/start)

## Outputs 🖼

|                 | Output |                 | Output |
|-----------------|--------|-----------------|--------|
| Login           | <img src="https://user-images.githubusercontent.com/73544434/201617525-dd94a7bc-add2-407e-a804-39f496f1bb61.png" width="240" height="480"/>       | SignUp          | <img src="https://user-images.githubusercontent.com/73544434/197416769-cd1482dc-65de-4ca9-9cdf-d1875b3aa2df.png" width="240" height="480"/>       |
| VerifyEmail     | <img src="https://user-images.githubusercontent.com/73544434/197416836-6dbcb2f4-5a4c-4f43-bf5a-f70c23905c46.png" width="240" height="480"/>       | Home            | <img src="https://media.giphy.com/media/dyeiHZAd4SbIOKY4IY/giphy.gif" width="240" height="480"/>       |
| Space News      | <img src="https://user-images.githubusercontent.com/73544434/197416913-c42478e2-c2d5-4685-9fd1-7cb1a0fac614.png" width="240" height="480"/>       | SpaceNewsDetail | <img src="https://user-images.githubusercontent.com/73544434/197417575-a349afd9-f0a8-446c-860f-8945a6425f74.png" width="240" height="480"/>       |
| Explore         | <img src="https://user-images.githubusercontent.com/73544434/198551970-22a2b115-d87a-4f87-a82f-c5bd0b1991b0.png" width="240" height="480"/>       | ExploreDetail   | <img src="https://media.giphy.com/media/L3ENgs7LiliMrKTmDy/giphy.gif" width="240" height="480"/>       |
| Glossary        | <img src="https://user-images.githubusercontent.com/73544434/201622086-5e83f05e-b897-4603-88f4-c083a55135ba.gif" width="240" height="480"/>       | Profile         | <img src="https://media.giphy.com/media/PWjXXdpAv8YnWSsyzA/giphy.gif" width="240" height="480"/>       |
| Forgot Password | <img src="https://user-images.githubusercontent.com/73544434/201616182-cbc2c9d3-3550-42e9-b2ad-d33cb2f81bcf.gif" width="240" height="480"/>        |                 |        |

## Architecture 🏗
The app uses MVVM [Model-View-ViewModel] architecture to have a unidirectional flow of data, separation of concern, testability, and a lot more.


![mvvm](https://user-images.githubusercontent.com/73544434/197416569-d42a6bbe-126e-4776-9c8f-2791925f738c.png)





