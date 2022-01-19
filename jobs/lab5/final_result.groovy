folder("_LABS/LAB5")
folder("_LABS/LAB5/TEAMS")
folder("_LABS/LAB5/TEAMS/MY_TEAM")
folder("_LABS/LAB5/TEAMS/MY_TEAM/01-HELLO-CLI")
folder("_LABS/LAB5/TEAMS/MY_TEAM/02-HELLO-WEB")


def desc ="""Generated solution for LAB 05 - Folders, Views, Credentials"""
job("_LABS/LAB5/TEAMS/MY_TEAM/01-HELLO-CLI/vsechny-branche") {
    description(desc)
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
        shell("java -jar target/moje-apka-*.jar")
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
    description(desc)
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

                java -jar target/demoapp.jar --version

                echo "\\n\\n\\n
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
