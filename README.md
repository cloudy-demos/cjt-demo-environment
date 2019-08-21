# CloudBees Jenkins Distribution (CJD) Demo

## Features
* [CloudBees Jenkins Distribution](https://www.cloudbees.com/products/cloudbees-jenkins-distribution) Master and Agent(s) run as Docker containers on your local machine
* Agents can be spawned and destroyed dynamically from the command line
* The Master is fully configured out of the box using [Jenkins Configuration as Code](https://jenkins.io/projects/jcasc/), with the following built-in CI/CD examples:
  * [Sample Rest Server](https://github.com/cloudy-demos/sample-rest-server) ([Jenkinsfile](https://github.com/cloudy-demos/sample-rest-server/blob/master/Jenkinsfile))
  * [ASP.Net Core App](https://github.com/cloudy-demos/aspnet-app) ([Jenkinsfile](https://github.com/cloudy-demos/aspnet-app/blob/master/Jenkinsfile))
* Container agents have [Maven](https://maven.apache.org/), [Docker](https://www.docker.com/products/container-runtime), and [Git](https://git-scm.com/) installed on them. A Maven cache is available to persist downloaded artifacts and speed up builds.

## Initial Setup
This demo requires that you have [Docker](https://www.docker.com/get-docker) installed.

Open a terminal at this repo location and run `cp .sample.env .env` to create local file called `.env` based on the sample. Then modify `.env` to make it specific to your environment. You should only need to edit ``USER_M2`` and the DockerHub API Key (from http://cloud.docker.com), all others settings may remain as-is.

When the `.env` file is ready, run ``make`` to let Docker do its thing. This may take several minutes.

NOTE: If any configuration files are modified, like ``jenkins.yaml``, ``plugins.txt``, or ``/init.groovy.d/*.groovy``, then run ``make`` again to build new images.

## Usage
To start CJD, run ``docker-compose up``. Logs will be streamed to the console, and CJD can be stopped with `^c`. Alternatively you can run CJD detached using ``docker-compose up -d``, access logs with ``docker logs cjt``, and stop using ``docker-compose down``.

You may then access CJD at http://localhost:9090 and complete the Getting Started wizard.

To start Agent(s), run ``make agent``. To stop all Agents, run ``make stop``. To build a new agent image after making changes, run ``make agent-build``.

To clean up images, run ``make clean``.

NOTE: [Swarm](https://wiki.jenkins.io/display/JENKINS/Swarm+Plugin) Agents require a user with appropriate permissions to connect to CJD. The default is admin/admin and you can change it in your ``.env`` if necessary.

## CI/CD Demos
A [Sample Rest Server](https://github.com/cloudy-demos/sample-rest-server) project is configured out of the box, which uses [Pipeline Shared Libraries](https://github.com/cloudy-demos/pipeline-libraries) as a form of templating.

NOTE: Sample Rest Server requires a Sonar server to function properly. Run ``make sonar`` to launch Sonar, then go to http://localhost:9000 to verify that it's running. Run ``docker stop sonar`` to stop the Sonar server.

An [ASP.Net Core App](https://github.com/cloudy-demos/aspnet-app) project is also configured out of the box. Note that this pipeline has an "input" step which pauses the Pipeline and waits for a human to approve. It will time out automatically without approval.
