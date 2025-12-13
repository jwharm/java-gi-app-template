package org.domain;

import org.javagi.base.GErrorException;
import org.javagi.interop.Interop;
import org.javagi.util.Intl;
import org.gnome.gio.Resource;

/**
 * The "main" class, here we initialize gettext, register the compiled
 * gresource bundle and start an Application instance.
 */
public class Example {

    public static final String APPLICATION_ID = "org.domain.Example";
    public static final String LOCALE_DIR = "/app/share/locale";
    public static final String GETTEXT_DOMAIN = "example";
    public static final String RESOURCE_DIR = "/app/share/" + APPLICATION_ID;

    /**
     * Run the application
     * @param args passed to AdwApplication.run()
     * @throws GErrorException thrown while loading and registering the
     *                         compiled resource bundle
     */
    public static void main(String[] args) throws GErrorException {
        // Initialize gettext
        Intl.bindtextdomain(GETTEXT_DOMAIN, LOCALE_DIR);
        Intl.textdomain(GETTEXT_DOMAIN);
        
        // Load gresource
        var resource = Resource.load(RESOURCE_DIR + "/org.domain.Example.gresource");
        resource.resourcesRegister();

        // Create and run the application
        var app = new ExampleApplication();
        app.run(args);
    }
}

