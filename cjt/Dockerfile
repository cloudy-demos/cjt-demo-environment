#Credit to source: https://github.com/cloudbees-oss/cjd-jcasc-demo/blob/master/Dockerfile
FROM jenkins/jenkins:2.190 as install_scripts_source
FROM cloudbees/cloudbees-jenkins-distribution:2.176.2.3
COPY --from=install_scripts_source /usr/local/bin/jenkins-support /usr/local/bin/jenkins-support
COPY --from=install_scripts_source /usr/local/bin/install-plugins.sh /usr/local/bin/install-plugins.sh

LABEL app-name="cjt-demo-environment"

# Set the war
ENV JENKINS_WAR /usr/share/cloudbees-jenkins-distribution/cloudbees-jenkins-distribution.war

# add groovy init scripts
COPY init.groovy.d/ /usr/share/jenkins/ref/init.groovy.d

# add environment variable to point to configuration file
ENV CASC_JENKINS_CONFIG /usr/share/jenkins/ref/jenkins.yaml

# Startup all plugins included into the CloudBees Jenkins Distribution bundle
ENV JAVA_OPTS "-Dcom.cloudbees.jenkins.cjp.installmanager.CJPPluginManager.allRequired=true"

#Install extra plugins
ENV JENKINS_UC http://jenkins-updates.cloudbees.com
ENV JENKINS_INCREMENTALS_REPO_MIRROR https://repo.jenkins-ci.org/incrementals
ENV REF=/usr/share/cloudbees-jenkins-distribution/ref
COPY plugins.txt $REF/plugins.txt
RUN bash /usr/local/bin/install-plugins.sh < $REF/plugins.txt

# apply JCasC
COPY jenkins.yaml /cfg/jenkins.yaml
ENV CASC_JENKINS_CONFIG /cfg/jenkins.yaml
