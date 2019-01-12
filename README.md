# Commons
Common android components, helper functions, adaptors, dialogs, logs and extentions functions that is required for almost every application at startup.

## Modules
1. [Dialogs](#Dialogs)

## Dialogs
1. [Information Dialog](#Information-Dialogs)
2. [Warning Dialog](#)
3. [Error Dialog](#)
4. [Delete Dialog](#)

### Information Dialogs
```kotlin
	infoDialog(
                this,   			// context
                "Information",  		// title
                "Some informational message",   // message
                true    			// cancelable on back press or touch anywhere on the screen
            ).show()
```
#### or
```kotlin
	infoDialog(
                this,   			// context
                "Information",  		// title
                "Some informational message",   // message
                true    			// cancelable on back press or touch anywhere on the screen
            ).setPositiveButton("OK") { dialog, _ ->
                // Ok button action
                dialog.dismiss()
            }.setNegativeButton("Close") { dialog, _ ->
                // close button action
                dialog.dismiss()
            }.show()
```

### How to use dialogs
#### add this line to project `build.gradle` file
```gradle
	allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}
```
#### add this line to app `build.gradle` file
```gradle
	dependencies {
	        implementation 'com.github.ArbazMateen.Commons:dialogs:0.1.1'
	}
```
