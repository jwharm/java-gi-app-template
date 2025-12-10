# Java-GI application template

This project can be used as a template for a GNOME application, 
developed with Java-GI. The project is setup to be built and
installed as a flatpak application.

The application is built with Gradle. All additional resources 
(icons, translations, desktop file, etc) are installed with
Meson. The Flatpak manifest will first run Gradle and then Meson.

Features:

* GNOME 48 & Adwaita example application, written in Java
* Compilation and installation of settings schema and gresource bundle
* Translated with GNU Gettext
* Template icons, desktop file, metainfo file
* Build and install as a Flatpak application

The template application is called "example" and uses the 
application id "org.domain.Example". This isn't dynamically
configurable (yet), so you'll have to search and replace this
manually. You'll also want to replace the `COPYING` and
`README.md` files with your own versions.

## Installation instructions

1. Clone this repository into a local folder.

2. Install the GNOME 48 Sdk and Platform runtime:

    ```
    flatpak --user install org.gnome.Platform//48 org.gnome.Sdk//48
    ```

3. Install the OpenJDK flatpak extension:

    ```
    flatpak --user install org.freedesktop.Sdk.Extension.openjdk//24.08
    ```

3. Build and install the application:

    ```
    flatpak-builder --force-clean --user --install build/flatpak data/org.domain.Example.json
    ```

4. Run the application:

    ```
    flatpak run org.domain.Example
    ```

## Developing the application

You can open the project folder in IntelliJ IDEA and it will
automatically load the Gradle project. To run the application
from IntelliJ, start the `application` gradle task.

Be aware that a locally running application cannot save and load settings, but you can load the gresource bundle, provided it is
compiled first. You can automate that with a custom Gradle task:

```groovy
tasks.register('compileResources', Exec) {
    workingDir 'src/main/gresource'
    commandLine 'glib-compile-resources', 'helloworld.gresource.xml'
}

tasks.named('compileJava') {
    dependsOn compileResources
}
```

This will create a compiled gresource bundle in the
`src/main/gresource` folder. Change the `RESOURCE_DIR` in
`Example.java` accordingly.

## Adding dependencies

When you change any of the dependencies in the Gradle build
file, regenerate the sources file that is used by flatpak-builder
in the offline build:

```
gradle flatpak-gradle-generator
```

## Uninstalling

Run `flatpak uninstall org.domain.Example` to remove the
application.
