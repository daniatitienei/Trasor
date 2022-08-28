![Trasor banner](https://user-images.githubusercontent.com/62098466/187082347-7c129962-4a94-4760-8ba1-a064f1de362e.jpg)

This is a **Game score tracker app** built with Jetpack Compose.

The purpose of this repository is to demonstrate below:

- Implementing entire UI elements with Jetpack Compose.
- Implementation of Android architecture components with Jetpack libraries.

## 📷 Previews

---

[]()

## 🛠 Tech Sacks & Open Source Libraries

---

- Minimum SDK level 28.
- 100% [Jetpack Compose](https://developer.android.com/jetpack/compose) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- Jetpack
    - Compose: Android’s modern toolkit for building native UI.
    - ViewModel: UI related data holder and lifecycle aware..
    - Navigation: For navigating screens and [Hilt Navigation Compose](https://developer.android.com/jetpack/compose/libraries#hilt) for injecting dependencies.
    - [Room](https://developer.android.com/jetpack/androidx/releases/room): Constructs Database by providing an abstraction layer over SQLite to allow fluent database access.
    - [Hilt](https://dagger.dev/hilt/): Dependency Injection
    - [DataStore](https://developer.android.com/topic/libraries/architecture/datastore): Data storage solution that allows you to store key-value pairs or typed objects with protocol buffers
- [accompanist](https://github.com/google/accompanist): A collection of extension libraries for Jetpack Compose.
- [moshi](https://github.com/square/moshi.git): A modern JSON library for Kotlin and Java

 
## 🏛️ Architecture

---

**Trasor** was built with [Guide to app architecture](https://developer.android.com/topic/architecture), so it would be a great sample to show how the architecture works in real-world projects.

The overall architecture is composed of two layers; UI Layer and the data layer. Each layer has dedicated components and they each have different responsibilities. The arrow means the component has a dependency on the target component following its direction.


## Modularization

---

![Modularization](https://user-images.githubusercontent.com/62098466/187082357-5e2c6885-869e-4d98-8337-d9bc4911a76b.jpg)


**Trasor** adopted modularization strategies below:

- **Reusability**: Modulizing reusable codes properly enable opportunities for code sharing and limits code accessibility in other modules at the same time.
- **Parallel Building**: Each module can be run in parallel and it reduces the build time.
- **Decentralized focusing**: Each developer team can assign their dedicated module and they can focus on their own modules.

## ****💯 MAD Score****

---

![summary](https://user-images.githubusercontent.com/62098466/187082370-154d6b78-1e96-4219-a846-0cac6e2e9c79.png)
