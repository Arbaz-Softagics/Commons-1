# Commons
Common android components, helper functions, adaptors, dialogs, logs and extentions functions that is required for almost every application at startup.

## Modules
1. [Dialogs](#Dialogs)

## Dialogs
1. [Information Dialog](#Information-Dialog)
2. [Warning Dialog](#Warning-Dialog)
3. [Error Dialog](#Error-Dialog)
4. [Delete Dialog](#Delete-Dialog)

### Information Dialog
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

### Warning Dialog
```kotlin
	warningDialog(
                this,   			// context
                "Warning",  			// title
                "Some warning message",   	// message
                true    			// cancelable on back press or touch anywhere on the screen
            ).show()
```
#### or
```kotlin
	warningDialog(
                this,   			// context
                "Warning",  			// title
                "Some warning message",   	// message
                true    			// cancelable on back press or touch anywhere on the screen
            ).setPositiveButton("OK") { dialog, _ ->
                // Ok button action
                dialog.dismiss()
            }.setNegativeButton("Close") { dialog, _ ->
                // close button action
                dialog.dismiss()
            }.show()
```

### Error Dialog
```kotlin
	errorDialog(
                this,   			// context
                "Error",  			// title
                "Some error message",   	// message
                true    			// cancelable on back press or touch anywhere on the screen
            ).show()
```
#### or
```kotlin
	errorDialog(
                this,   			// context
                "Error",  			// title
                "Some error message",   	// message
                true    			// cancelable on back press or touch anywhere on the screen
            ).setPositiveButton("OK") { dialog, _ ->
                // Ok button action
                dialog.dismiss()
            }.setNegativeButton("Close") { dialog, _ ->
                // close button action
                dialog.dismiss()
            }.show()
```

### Delete Dialog
```kotlin
	confirmDelete(
                this,   			// context
                "Confirm Delete",  		// title
                "Some confirmation message",   	// message
                true    			// cancelable on back press or touch anywhere on the screen
            ).setPositiveButton("Delete") { dialog, _ ->
                // Ok button action
                dialog.dismiss()
            }.setNegativeButton("Close") { dialog, _ ->
                // close button action
                dialog.dismiss()
            }.show()
            .getButton(AlertDialog.BUTTON_POSITIVE)
                .setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_light))
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
