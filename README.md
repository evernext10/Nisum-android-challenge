# Nisum Challenge - Android Clean Architecture with MVVM using Jetpack component


## 💡 What is Clean Architecture?

Clean architecture is a category of software design pattern for software architecture that follows the concepts of clean code and implements [SOLID principles](https://www.jbsolutions.in/download/downloads/SOLID_PRINCIPLE_WITH_KOTLIN.pdf)

It’s essentially a collection of best practice design principles that help you keep business logic, or domain logic, together and minimize the dependencies within the system.

Clean architecture is a method of software development in which you should be able to identify what a program performs merely by looking at its source code. Robert C. Martin, also known as Uncle Bob, came up with the Clean Architecture concept in the year 2012.

![alt tag](https://github.com/evernext10/Meli-android-challenge/blob/main/resources/clean_architecture_software.jpg)

## 💡 Why Clean Architecture?

Separation of Concerns — Separation of code in different modules or sections with specific responsibilities making it easier for maintenance and further modification.
Loose coupling — flexible code anything can be easily be changed without changing the system
Easily Testable


## 💡 Layers of clean architecture

- **Presentation or UI:**
  A layer that interacts with the UI, mainly Android Stuff like Activities, Fragments, ViewModel, etc. It is dependent on Use Cases.
- **Domain:**
  Contains the business logic of the application. It is the individual and innermost module.
- **Data:**
  It includes the domain layer. It would implement the interface exposed by domain layer and dispenses data to app

![alt tag](https://github.com/evernext10/Meli-android-challenge/blob/main/resources/clean_arch.png)

## 💡 Advantages of Using Clean Architecture
- Easily testable.
- Scalable.
- Your team can add new features even more quickly.
- The project is even easier to maintain.

## 💡 Tech stack & Modern Library Tools

- [Kotlin, Coroutines + Flow for asynchronous](https://developer.android.com/kotlin/coroutines)
- [Dependency injection with Koin](https://developer.android.com/training/dependency-injection)
- [Jetpack Navigation](https://developer.android.com/guide/navigation/)
- [ViewModel - UI related data holder, lifecycle aware](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [MVVM Architecture (View - DataBinding - ViewModel - Model)](https://developer.android.com/topic/libraries/view-binding)
- [Glide - loading images](https://github.com/bumptech/glide)
- [Retrofit2 & OkHttp3 - construct the REST APIs and paging network data](https://square.github.io/retrofit/)
- [Gson - JSON representation](https://github.com/google/gson)
- [Material-Components - Material design components](https://material.io/design)
- [Leak Canary - memory leak detection library for Android](https://github.com/square/leakcanary)
- [AndroidJUnitRunner](https://developer.android.com/training/testing/junit-runner)
- [Truth](https://truth.dev/)
- [Turbine](https://github.com/cashapp/turbine)
- [CI/CD - GitActions](https://proandroiddev.com/android-ci-cd-pipeline-with-github-actions-demystifying-github-actions-83258e76a18f)


## 💡 Project: Nisum Challenge

This is a test presentation of Clean Architecture with MVVM in Android to show products from Mercado Libre API.


##  💡 Modules

**Authentication**

- core
- features
  - marketplace-pockemon-list
    - data
    - domain
    - presentation
  - marketplace-pockemon-detail
    - data
    - domain
    - presentation
- navigation
- app
- DSL

# Under devlopement

# Thanks for reading! 
