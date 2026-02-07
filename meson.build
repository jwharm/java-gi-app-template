project('example',
          version: '0.1.0',
    meson_version: '>= 1.0.0',
  default_options: [ 'warning_level=2', 'werror=false', ],
)

i18n = import('i18n')
gnome = import('gnome')

subdir('data')
subdir('po')

gnome.post_install(
     glib_compile_schemas: true,
    gtk_update_icon_cache: true,
  update_desktop_database: true,
)

# Run Gradle as a custom command. This will run the Gradle build,
# and install the resulting application into the /app directory.
gradle = find_program('gradle')
output_file = 'gradle_build_done'
custom_target('gradle',
    build_by_default: true,
  build_always_stale: true,
              output: output_file,
             command: [
    gradle,
    '-p',
    meson.project_source_root(),
    'install',
    '&&',
    'touch',
    output_file
  ]
)
