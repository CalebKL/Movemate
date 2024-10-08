## MoniePoint Android Engineer Interview Solution

*Summary*
A translation design application that precisely replicates the animations, scroll behavior, and page layouts from a mock-up design provided by a Sample Dribble Design. The application ensures an exact match in user interface and experience, mirroring the original design's look and feel.

The final app looks like this:👀

## Screenshots
| Home Screen                       | Calculate Up                           | Shipment Screen                      | Pricing Screen                        |
|-----------------------------------|----------------------------------------|--------------------------------------|---------------------------------------|
| <img src="screenshots/home.png"/> | <img src="screenshots/calculate.png"/> | <img src="screenshots/pricing.png"/> | <img src="screenshots/pricing.png"/>

*Screens*
- **Home Screen**:
  - This is the first screen which displays the User Profile, Tracking Numbers and available Vehicles
  - A User can also search a receipt number and a list of shipment details will be displayed
  - The User can also navigate to Calculate, Shipment and Profile Pages

- **Shipment Screen**:
  - On this Screen, the user can see a list different shipment History
  - The User will be able to scroll up and down on the different shipments
  - The user can be able to filter different shipments like ("All, Completed, Pending, Cancelled, In-Progress)
  - Proper State-handling has been implemented, when the data is loading, empty and if the data has been retrieved. 
  
- **Calculate Screen**:
  - A user can enter inputs for the Sender's Location, Receiver Location  and Approx. Weight.
  - The User can also select different categories from the FlowRow eg. (Electronics).
  - Finally, the calculate button navigates to the Pricing Screen which displays the Total Amount

- **Pricing Screen**:
  - The Pricing screen pops up when the user has pressed the calculation button 
  - The page shows the total estimated price for the shipment. 

*Environment*
- Built on A.S Ladybug | 2024.2.1
- JDK 17

# Design/Architectural decisions 📐

- The project makes use of common android patterns in modern android codebases. The Architecture pattern used is MVVM and the Viewmodel and Repository Patterns have been used.
- The modularization approach focuses on maximizing cohesion and minimizing coupling. The primary feature module is 'Shipments,' and the main app listens to this module while managing navigation. This pattern simplifies the process of adding new features by isolating functionality within individual modules, reducing potential conflicts between teams. When implementing a new feature, it's as straightforward as adding a new module to the root package, ensuring scalability and seamless integration across the project.


**Project Structure**

The folders are split in two main modules: Feature Module and App Module:

- **Feature Module**:
  The Feature module is Shipments. This module handles the logic to the different screens for the shipments app from shipment history, profile page, calculation and the Home page.

- **App Module**:
  This app module is the main entry point to the application and handles the navigation logic to navigate from the Home Screen to Calculate or the Shipments page and back.
  
**Testing**

Compose tests have been added on the shipments module(AndroidTests) where the the Home Screen, Shipments Screen, Calculate Screens and Pricing Screen has been tested. Navigating from one screen to another has also been covered from the tests. 

## Tech-stack

* Tech-stack
  * [Kotlin](https://kotlinlang.org/) - a cross-platform, statically typed, general-purpose programming language with type inference.
  * [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - perform background operations.
  * [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html) - handle the stream of data asynchronously that executes sequentially.
  * [Jetpack](https://developer.android.com/jetpack)
    * [Flows](https://developer.android.com/kotlin/flow) - is an observable data holder.
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related data in a lifecycle conscious way.

* Architecture
  * MVVM - Model View Viewmodel
  
* Tests
  * [Unit Tests](https://en.wikipedia.org/wiki/Unit_testing) ([JUnit](https://junit.org/junit4/)) - a simple framework to write repeatable tests.
  * [MockK](https://github.com/mockk) - mocking library for Kotlin
  * [ComposeTests](https://developer.android.com/jetpack/compose/testing) - Jetpack Compose Testing
  * [Roboelectric](https://robolectric.org/) - Android Unit Testing Framework without need of flakiness of an emulator
  * [Turbine](https://github.com/cashapp/turbine) - A small testing library for kotlinx.coroutines Flow

* Gradle
  * [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) - Alternative syntax from the traditional Groovy
  * Plugins
    * [Ktlint](https://github.com/JLLeitschuh/ktlint-gradle) - creates convenient tasks in your Gradle project that run ktlint checks or do code auto format.
    * [Spotless](https://github.com/diffplug/spotless) - Adds Licence header.
  
* CI/CD
  * Github Actions

# LICENSE

```
   Copyright 2024 Caleb langat

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

```
