# Revolut Rates

This project was built as a skill test for my job application at Revolut.

In this README I will explain some of the decision I made in regards to the project and architecture.

## Architecture

This app follows the standard android Model-View-ViewModel as per Google's guidelines.
As much as clean architecture is desirable, for a small project such as this one it's not as necessary.
Also, due to the short development time, it would become increasingly difficult to design layers that
hides all implementation details.

However, it follows some key aspects of the android clean architecture:
- Business layer is in the ViewModel, and not spread through the app
- Views are dumb. Only taking care of showing the data that are given to it.
- Repository layer that hides networking and database implementations.
- Dependency inversion.

To help build this app, I opted to use Android Jetpack libraries as they would give me faster development.

### Libraries

#### Kotlin

### Android
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture)
  - [Data Binding](https://developer.android.com/topic/libraries/data-binding)
  - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle)
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [ConstraintLayout](https://developer.android.com/training/constraint-layout/)

### Third-party
- [Dagger](https://dagger.dev/)
- [Timber](https://github.com/JakeWharton/timber)
- [Glide](https://github.com/bumptech/glide)
- [Retrofit](https://square.github.io/retrofit/)
- [Moshi](https://github.com/square/moshi)

#### Quality check
- [klint](https://github.com/shyiko/ktlint)
- [detekt](https://github.com/arturbosch/detekt)
- [lint](https://developer.android.com/studio/write/lint):

#### Tests
- [Mockk](https://github.com/mockk/mockk)

### Decisions on the libraries

For testing, I'd rather use MockK instead of Mockito for the same reason: it's built for Kotlin and therefore it's better to use.
Also, it provides coroutine support which is something that Mockito doesn't.

The quality tools are good for checking code style (Ktlint), and also finding potential bugs and performance problems (detekt).
They're important to maintain a consistent code base even with different developers on the team.

## Modularization

I opted to modularise the app as much as I thought was necessary due to the short development time.
The modularization allows shorter build times as a change in one module doesn't require the other modules to compile as well.
It gives more flexibility during development as well since it modules could be distributed to the developers, avoiding conflicts.
Also, it's more organized which as a good thing to be.

Apart from the core module, the "features" could be almost plug-and-play. Allowing easier and faster development and maintenance.

## What else I would do

I would definitely implement instrumented tests. However this app is so small that it doesn't make sense to do it.

I would add JaCoCo for test coverage and break builds if the coverage is under a threshold.

I would add some form of Crashlytics (such as Firebase's) to ensure crashes in production are known and taken care of.

I would split each feature module in more modules, such as data, view and business.