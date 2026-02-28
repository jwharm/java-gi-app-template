# Java-GI flatpak application template

This project can be used as a template for a GNOME application, 
developed with Java-GI. The project is setup to be built and
installed as a flatpak application.

The application is built with Gradle. All additional resources 
(icons, translations, desktop file, etc) are installed with
Meson. The Flatpak manifest will run Meson, and Meson runs Gradle.

Features:

* GNOME 49 & Adwaita example application, written in Java (OpenJDK 25)
* [Blueprint](https://gnome.pages.gitlab.gnome.org/blueprint-compiler)
  user interface definition
* Compilation and installation of settings schema and gresource bundle
* Translated with GNU Gettext
* Template icons, desktop file, metainfo file
* Build and install as a Flatpak application

The template application is called "example" and uses the 
application id "org.domain.Example". This isn't dynamically
configurable, so you'll have to search and replace this manually.
You'll also want to replace the `COPYING` and `README.md` files
with your own versions.

## Installation instructions

1. Clone this repository into a local folder.

2. Install the GNOME 49 Sdk and Platform runtime:

    ```
    flatpak --user install org.gnome.Platform//49 org.gnome.Sdk//49
    ```

3. Install the OpenJDK flatpak extension:

    ```
    flatpak --user install org.freedesktop.Sdk.Extension.openjdk25//25.08
    ```

4. Build and install the application:

    ```
    flatpak-builder --force-clean \
                    --user \
                    --install \
                    --state-dir=/tmp/flatpak-builder \
                    /tmp/builddir \
                    org.domain.Example.yaml
    ```

The `--force-clean` option will delete previous build files,
and `--user` will install the app for the current user
instead of a system-wide installation.

The `--state-dir` option will change the state directory of
flatpak-builder from `.flatpak-builder` to a directory in `/tmp`,
to prevent IntelliJ IDEA from indexing its contents, which can
significantly slow down or even freeze the IDE.

The `/tmp/builddir` location can be set to any other directory
of your choosing, as long as it's on the same filesystem as
the flatpak-builder state directory.

5. Run the application:

    ```
    flatpak run org.domain.Example
    ```

## Developing the application

You can open the project folder in IntelliJ IDEA and it will
automatically load the Gradle project. To run the application
from IntelliJ, compile the blueprint files (see below) and then
start the app with the gradle `run` task. The app will have a
striped headerbar to indicate it as a developer build.

Be aware that a locally running application cannot load settings,
icons and translations.

### Blueprint files

Blueprint (.blp) files are located in `src/main/blueprint`. When
you change them, they must be compiled to XML (.ui) format with
`blueprint-compiler`. The output .ui files are then compiled into
a gresource bundle with `glib-compile-resources`.

When you create a new Blueprint file, add the filename to the
`meson.build` file in the same directory, and to `po/POTFILES.in`
if the file contains translatable strings.

To run the blueprint compiler and build the gresource bundle
manually, you need to have both the tools mentioned above
installed locally.

The Gradle build script contains a `"compileResources"` task that
calls `blueprint-compiler` and `glib-compile-resources`. It will
create a compiled gresource bundle in the `build/generated` folder.
The app will automatically look for the gresource bundle in that
location when running it with the Gradle `run` task.

### Translations

The application is translated with GNU gettext. Gettext is
initialized in the `main` method in `Example.java`.

With gettext, all translatable strings in Java source code,
blueprint files and other resources are translated in the same
way. The only requirement is that all files that contain
translatable strings, are added to `po/POTFILES.in`.

To create translatable strings in Java, add a static import of
`org.javagi.util.Intl.i18n` and use `i18n("text to translate")`.
Comments that start with `TRANSLATORS:` will be included in the
po-files.

More information about gettext and meson is available on the
[meson website](https://mesonbuild.com/Localisation.html).

To recompile the `example.pot` file, run:

```
meson setup ../builddir
meson compile example-pot -C ../builddir
```

Regenerate the po-files by running:

```
meson compile example-update-po -C ../builddir
```

Add more languages in `po/LINGUAS`. The template application
includes one translation (nl):

![Screenshot of the application template](screenshot.png)

## Adding dependencies

When you change any of the dependencies in the Gradle build
file, delete and regenerate the sources file that is used by
flatpak-builder in the offline build:

```
rm data/maven-dependencies.json
./gradlew flatpak-gradle-generator --no-configuration-cache
```

## Uninstalling

Run `flatpak uninstall org.domain.Example` to remove the
application.
