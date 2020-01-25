# Matheus Movies

This project was built as a skill test.

In this README I will explain some of the decision I made in regards to the project and architecture.

## Architecture

This app follows the standard android Model-View-ViewModel as per Google's guidelines.

Tt follows some key aspects of the android clean architecture:
- Business layer is in the UseCases, and not spread through the app
- Views are dumb. Only taking care of showing the data that is given to it.
- Repository layer that hides networking and database implementations.
- Dependency inversion.
- Modularisation, app module only depends on core and doesn't know about the other feature modules.

To help build this app, I opted to use Android Jetpack libraries as they would give me faster development.

### Libraries

#### Kotlin

### Android
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture)
  - [Data Binding](https://developer.android.com/topic/libraries/data-binding)
  - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle)
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
  - [Room](https://developer.android.com/topic/libraries/architecture/room)
  - [Navigation](https://developer.android.com/guide/navigation/navigation-getting-started)
  - [Paging](https://developer.android.com/topic/libraries/architecture/paging/)
- [ConstraintLayout](https://developer.android.com/training/constraint-layout/)

### Third-party
- [Koin](https://insert-koin.io/)
- [Timber](https://github.com/JakeWharton/timber)
- [Glide](https://github.com/bumptech/glide)
- [Retrofit](https://square.github.io/retrofit/)
- [Moshi](https://github.com/square/moshi)
- [RxJava2](https://github.com/ReactiveX/RxJava)
- [RxAndroid](https://github.com/ReactiveX/RxAndroid)
- [RxKotlin](https://github.com/ReactiveX/RxKotlin)

#### Quality check
- [klint](https://github.com/shyiko/ktlint)
- [detekt](https://github.com/arturbosch/detekt)
- [lint](https://developer.android.com/studio/write/lint):

#### Tests
- [Mockk](https://github.com/mockk/mockk)

### Decisions on the libraries

For dependency injection I decided against Dagger because of the sheer complexity that is setting up a project for it.
Since this is a small project for skill demonstration I'd rather use some other dependency injection with easier
setup and use. Therefore I opted to use Koin, a lightweight library that allowed me to use DI principles in the app.
It's not as fast as Dagger and doesn't have compile-time checks, but it's good enough for this use case. Also, it's built
in Kotlin which provides better usage with the language.

For testing, I'd rather use MockK instead of Mockito for the same reason: it's built for Kotlin and therefore it's better to use.
Also, it provides coroutine support which is something that Mockito doesn't.

The quality tools are good for checking code style (Ktlint), and also finding potential bugs and performance problems (detekt).
They're important to maintain a consistent code base even with different developers on the team.

Regarding the MovieRepository, it's fully in the movie module which wouldn't allow for other modules to use it.
If that is the case, it's possible to move the database, DAO and API to the core/app module. Allowing other modules to use it.

## Modularization

I opted to modularise the app as much as I thought was necessary due to the short development time.
The modularization allows shorter build times as a change in one module doesn't require the other modules to compile as well.
It gives more flexibility during development as well since it modules could be distributed to the developers, avoiding conflicts.
Also, it's more organized which as a good thing to be.

Apart from the core module, the "features" could be almost plug-and-play. Allowing easier and faster development and maintenance.

## What else I would do

I would definitely implement instrumented tests. However I didn't have enough time to do it. 
Even though a week is given to finish the project, I'm going to travel to another country for a few days of holiday.

I would add JaCoCo for test coverage and break builds if the coverage is under a threshold.

I would add some form of Crashlytics (such as Firebase's) to ensure crashes in production are known and taken care of.
Also using Firebase it would be useful to add analytics to verify app usage.