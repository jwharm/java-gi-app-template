desktop_file = i18n.merge_file(
        input: 'org.domain.Example.desktop.in',
       output: 'org.domain.Example.desktop',
         type: 'desktop',
       po_dir: '../po',
      install: true,
  install_dir: get_option('datadir') / 'applications'
)

desktop_utils = find_program('desktop-file-validate', required: false, disabler: true)
test('Validate desktop file', desktop_utils, args: [desktop_file])

appstream_file = i18n.merge_file(
        input: 'org.domain.Example.metainfo.xml.in',
       output: 'org.domain.Example.metainfo.xml',
       po_dir: '../po',
      install: true,
  install_dir: get_option('datadir') / 'metainfo'
)

appstreamcli = find_program('appstreamcli', required: false, disabler: true)
test('Validate appstream file', appstreamcli,
     args: ['validate', '--no-net', '--explain', appstream_file]
)

install_data('org.domain.Example.gschema.xml',
  install_dir: get_option('datadir') / 'glib-2.0' / 'schemas'
)

compile_schemas = find_program('glib-compile-schemas', required: false, disabler: true)
test('Validate schema file', compile_schemas,
     args: ['--strict', '--dry-run', meson.current_source_dir()]
)

resources = gnome.compile_resources(
  'org.domain.Example',
  'org.domain.Example.gresource.xml',
        source_dir: meson.project_source_root() / 'src' / 'main' / 'gresource',
  gresource_bundle: true,
       install_dir: get_option('datadir') / 'org.domain.Example',
           install: true
)

service_conf = configuration_data()
service_conf.set('bindir', get_option('prefix') / get_option('bindir'))
configure_file(
          input: 'org.domain.Example.service.in',
         output: 'org.domain.Example.service',
  configuration: service_conf,
    install_dir: get_option('datadir') / 'dbus-1' / 'services'
)

subdir('icons')
