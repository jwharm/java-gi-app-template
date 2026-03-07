### This repository is now hosted on Codeberg:

Please visit https://codeberg.org/java-gi/app-template

---

This project can be used as a template for a GNOME application, 
developed with Java-GI. The project is setup to be built and
installed as a flatpak application.

The application is built with Gradle. All additional resources 
(icons, translations, desktop file, etc) are installed with
Meson. The Flatpak manifest will run Meson, and Meson runs Gradle.

Features:

* GNOME 49 & Adwaita example application, written in Java (OpenJDK 25)
* Blueprint user interface definition
* Compilation and installation of settings schema and gresource bundle
* Translated with GNU Gettext
* Template icons, desktop file, metainfo file
* Build and install as a Flatpak application

The template application is called "example" and uses the 
application id "org.domain.Example". This isn't dynamically
configurable, so you'll have to search and replace this manually.
You'll also want to replace the `COPYING` and `README.md` files
with your own versions.
