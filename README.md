# Codelabs-Playground

## Purpose

This repository contains the code for Architecture component best practice. Android Architecture Components are a set of libraries to help with various challenges in dealing with Android architecture. Room handles database persistence. Lifecycle helps you create components that are aware of the current Android lifecycle state. ViewModel holds data and survives configuration changes.

## Getting Started

### Prerequisites

Make sure your Android device has API level 24 or higher.

### Clone Project

To get started with the project, clone it using the following command:

```
git clone "https://github.com/ichwansh03/Codelabs-Playground.git"
```

## Libraries Used

The following libraries are used in the project:
* [AndroidX Core-KTX](https://developer.android.com/kotlin/ktx) - AndroidX Core library with Kotlin extensions for writing concise, idiomatic Kotlin code.
* [AndroidX AppCompat](https://developer.android.com/jetpack/androidx/releases/appcompat) - AndroidX AppCompat library for providing backward compatibility for newer Android features on older devices.
* [Google Material Components](https://material.io/develop/android) - Material Design components by Google for creating a visually appealing and consistent user interface.
* [ConstraintLayout](https://developer.android.com/training/constraint-layout) - Android's ConstraintLayout for creating flexible and responsive layouts.
* [AndroidX Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle) - AndroidX Lifecycle components for handling lifecycle-aware data in the ViewModel.
* [AndroidX Legacy Support](https://developer.android.com/jetpack/androidx/releases/legacy) - AndroidX Legacy Support library to support older Android features.
* [RecyclerView](https://developer.android.com/jetpack/androidx/releases/recyclerview) - AndroidX RecyclerView for displaying large datasets efficiently.
* [DataStore Preferences](https://developer.android.com/topic/libraries/architecture/datastore) - Android DataStore library for storing preferences asynchronously and consistently.
* [WorkManager](https://developer.android.com/jetpack/androidx/releases/work) - AndroidX WorkManager for managing background tasks efficiently.
* [Google Play Services Maps](https://developers.google.com/maps/documentation/android-sdk/overview) - Google Play Services library for integrating Google Maps into the app.
* [Retrofit](https://square.github.io/retrofit/) - Retrofit library for handling network requests and responses with a type-safe approach.
* [OkHttp Logging Interceptor](https://square.github.io/okhttp/interceptors) - OkHttp Logging Interceptor for logging HTTP interactions.
* [GSON Converter](https://github.com/square/retrofit/tree/master/retrofit-converters/gson) - Retrofit GSON Converter for parsing JSON responses using GSON.
* [Koin](https://insert-koin.io/) - Koin for implementing dependency injection in the app using a lightweight and pragmatic approach.
* [Glide](https://github.com/bumptech/glide) - Glide library for loading images efficiently and smoothly.

## Project Architecture and Techniques

The Disaster App follows the MVVM (Model-View-ViewModel) architectural pattern to clearly separate concerns. The ViewModel serves as a bridge between the UI (View) and the data (Model). Data operations are handled using a Repository pattern, which abstracts the data source from the ViewModel. Dependency Injection is implemented using Koin, allowing for easy management of dependencies and enabling better testability of classes. View Binding is used to bind UI components in layout files, providing type-safe access to these components. Coroutines Flow and State Flow are employed to handle asynchronous operations and data streams efficiently. This approach simplifies handling background tasks and data updates while providing a more reactive and responsive user experience.


---
Feel free to reach out if you have any questions or need further assistance with the project! Happy coding! 🚀
