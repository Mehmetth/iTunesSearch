<h1 align="center">iTunesSearch</h1>

<p align="center">
This project summarises some of the general use cases and demands on a typical production project using: <b>Functional Programming</b>, <b>MVVM</b>, <b>Kotlin Coroutines</b>, <b>Kotlin Flows</b>, <b>Navigation Compenent</b> <b>etc.</b>
</p>
</br>

<p align="center">
<img alt="1" src="https://user-images.githubusercontent.com/18207490/168911876-64fbdba8-841d-4002-be82-69f9daa620ce.png" height="200">
<img alt="2" src="https://user-images.githubusercontent.com/18207490/168911855-31b02aa3-25c5-444b-aa25-b53cf7ce5654.png" height="200">
<img alt="3" src="https://user-images.githubusercontent.com/18207490/168911859-3558d7a5-372d-41e8-bd73-163905ec1025.png" height="200">
<img alt="4" src="https://user-images.githubusercontent.com/18207490/168911835-15afc2c4-5b5f-4b37-9493-d14ecb446b81.png" height="200">
<img alt="5" src="https://user-images.githubusercontent.com/18207490/168911850-a2fb4943-6623-4db0-af64-24f61a227968.png" height="200">
<img alt="6" src="https://user-images.githubusercontent.com/18207490/168911862-62d834af-3142-4ab7-9dc5-cc0b986c9278.png" height="200">
<img alt="7" src="https://user-images.githubusercontent.com/18207490/168911865-8bf16a17-e2f3-4c44-a46f-f94e5bafe658.png" height="200">
<img alt="8" src="https://user-images.githubusercontent.com/18207490/168911871-00e4dd29-2bb5-490b-998b-926fb5d56c8a.png" height="200"> 
</p>

## Libraries Used :books:
* [Coroutines][0] Library support for Kotlin coroutines.
* [Flows][1] Stream processing API, built on top of Coroutines.
* [Compose Navigation][2] for tabs navigation using Jetpack Compose.
* [Data Binding][3] Layotes (user interface) and application logic and models are used synchronously to each other.
* [Dagger Hilt][4] Dependency injection library for Android.
* [Paging3][7] It is used to display the data coming through the API as a page.
* [Retrofit][5] Type-safe REST client for Android to consume RESTful web services.
* [Glide Compose][6] Image downloading and caching library supported by Jetpack Compose.


[0]:  https://developer.android.com/kotlin/coroutines
[1]:  https://developer.android.com/kotlin/flow
[2]:  https://developer.android.com/jetpack/compose/navigation
[3]:  https://developer.android.com/topic/libraries/data-binding
[4]:  https://developer.android.com/training/dependency-injection/hilt-android
[5]:  https://square.github.io/retrofit
[6]:  https://github.com/bumptech/glide
[7]:  https://developer.android.com/topic/libraries/architecture/paging/v3-overview

## Clean Architecture

<center><img width="200" height="200" src="https://koenig-media.raywenderlich.com/uploads/2019/06/Android-Clean-Architecture.png">

### Layers :bookmark_tabs:
- **Domain** - Would execute business logic which is independent of any layer and is just a pure kotlin/java package with no android specific dependency.
- **Data** - Would dispense the required data for the application to the domain layer by implementing interface exposed by the domain.
- **Presentation** - Would include both domain and data layer and is android specific which executes the UI logic.
