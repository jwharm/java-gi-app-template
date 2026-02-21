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

app_name = meson.project_name()
gradle = find_program('gradle')
gradle_dir = meson.project_source_root()
gradle_install_dir = gradle_dir / 'build' / 'install' / app_name + '-shadow'

custom_target('gradle',
    build_by_default: true,
  build_always_stale: true,
             install: true,
         install_dir: [ get_option('bindir'), get_option('libdir') ],
              output: [ app_name, 'app-all.jar' ],
             command: [ gradle, '-p', gradle_dir, 'installShadowDist',
		                '&&', 'mv', gradle_install_dir / 'bin' / app_name, '@OUTPUT0@',
		                '&&', 'mv', gradle_install_dir / 'lib' / 'app-all.jar', '@OUTPUT1@'
                      ]
)
