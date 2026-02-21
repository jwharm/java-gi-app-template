package org.domain;

import org.javagi.gtk.annotations.GtkChild;
import org.javagi.gtk.annotations.GtkTemplate;
import org.gnome.adw.ApplicationWindow;
import org.gnome.gtk.Application;
import org.gnome.gtk.Label;

/**
 * The {@code @GtkTemplate} annotation marks ExampleWindow as a Gtk composite
 * template class. The user interface is defined in the ui file, and the
 * application logic is implemented in Java.
 */
@GtkTemplate(name="ExampleWindow", ui="/org/domain/example-window.ui")
public class ExampleWindow extends ApplicationWindow {
    /**
     * This field is set to the GtkLabel instance defined in the ui file.
     */
    @GtkChild
    public Label label;

    /**
     * Construct a new ExampleWindow instance.
     * @param app the application instance
     */
    public ExampleWindow (Application app) {
        setApplication(app);

        if (Example.DEVEL_PROFILE)
            addCssClass("devel");
    }
}

