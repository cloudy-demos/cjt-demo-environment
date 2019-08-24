//run at http://localhost:9090/script to print all installed plugins
//then update plugins.txt
List<String> jenkinsPlugins = new ArrayList<String>(Jenkins.instance.pluginManager.plugins);
jenkinsPlugins.sort { it.displayName }
              .each { plugin ->
                   println ("${plugin.shortName}:${plugin.version}")
              }