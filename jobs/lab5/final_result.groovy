def desc ="LAB05 - Folders, Views, Credentials"


folder("_LABS/LAB5")
folder("_LABS/LAB5/TEAMS")
folder("_LABS/LAB5/TEAMS/MY_TEAM") {
    buildMonitorView("TV") {
        recurse()
        jobs {
            regex('.*')
        }
    }
}
folder("_LABS/LAB5/TEAMS/MY_TEAM/01-HELLO-CLI")
folder("_LABS/LAB5/TEAMS/MY_TEAM/02-HELLO-WEB")

job("_LABS/LAB5/TEAMS/MY_TEAM/01-HELLO-CLI/vsechny-branche") {
    description("""<hr/ ><i>Generated solution for: <b>${desc}</b</i>><hr/ >""")
    concurrentBuild(true)

    jdk("OpenJDK 8")

    wrappers {
        timestamps()
        colorizeOutput()
    }

    scm {
        git {
            remote {
                github("jenkins-for-developers/$REPO_JMENO-$REPO_PRIJMENI-java", "https")
            }
            branch("**")
        }
    }

    triggers {
        githubPush()
        scm("@daily") {
        }
    }

    steps {
        maven {
            goals('clean package')
            mavenInstallation('Maven 3.3.9')
            properties(skipTests: true)
        }
        shell """\
                set +x
                echo "\\n\\n\\n"

                java -jar target/moje-apka-*.jar

                echo "\\n\\n\\n"
                """.stripIndent()
    }

    publishers {
        archiveArtifacts {
            pattern('target/*.jar')
            fingerprint(true)
            onlyIfSuccessful()
        }
        fingerprint('**/pom.xml')
    }

    logRotator {
        artifactNumToKeep(3)
    }
}


job("_LABS/LAB5/TEAMS/MY_TEAM/02-HELLO-WEB/vsechny-branche") {
    description("""<hr/ ><i>Generated solution for: <b>${desc}</b</i>><hr/ >""")
    concurrentBuild(true)

    jdk("OpenJDK 8")

    wrappers {
        timestamps()
        colorizeOutput()
    }

    scm {
        git {
            remote {
                github("jenkins-for-developers/$REPO_JMENO-$REPO_PRIJMENI-dropwizard", "https")
            }
            branch("**")
        }
    }

    triggers {
        githubPush()
        scm("@daily") {
        }
    }

    steps {
        maven {
            goals('clean package')
            mavenInstallation('Maven 3.3.9')
            properties(skipTests: true)
        }
            shell """\
                set +x
                echo "\\n\\n\\n"

                java -jar target/demoapp.jar --version

                echo "\\n\\n\\n"
                """.stripIndent()
    }

    publishers {
        archiveArtifacts {
            pattern('target/*.jar')
            fingerprint(true)
            onlyIfSuccessful()
        }
        fingerprint('**/pom.xml')
    }

    logRotator {
        artifactNumToKeep(3)
    }
}
