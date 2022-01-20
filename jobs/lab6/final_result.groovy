import javaposse.jobdsl.dsl.views.jobfilter.MatchType

def desc ="LAB06 - Pousteni Testu"


folder("_LABS/LAB6")
folder("_LABS/LAB6/TEAMS")
folder("_LABS/LAB6/TEAMS/MY_TEAM") {
    primaryView("Vsechno")
    views {
        buildMonitorView("TV") {
            recurse()
            jobs {
                regex('.*')
            }
        }

        listView("Vsechno") {
            recurse()
            jobs {
                regex('.*')
            }
            columns {
                name()
                buildButton()
                lastSuccess()
                lastFailure()
                lastDuration()
                weather()
                status()
            }
        }
    }
}
folder("_LABS/LAB6/TEAMS/MY_TEAM/01-HELLO-CLI")
folder("_LABS/LAB6/TEAMS/MY_TEAM/02-HELLO-WEB")

job("_LABS/LAB6/TEAMS/MY_TEAM/01-HELLO-CLI/vsechny-branche") {
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

        recordIssues {
            tools {
                mavenConsole()
                java()
                javaDoc()
                checkStyle()
            }
        }

        archiveJunit('target/*-reports/*.xml') {
            allowEmptyResults()
            retainLongStdout()
            testDataPublishers {
                publishTestStabilityData()
            }
        }

        jacocoCodeCoverage {
            sourcePattern('src/**')
        }
    }

    logRotator {
        artifactNumToKeep(3)
    }
}


job("_LABS/LAB6/TEAMS/MY_TEAM/02-HELLO-WEB/vsechny-branche") {
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
            goals('clean verify -Dmaven.test.failure.ignore=true')
            goals('checkstyle:checkstyle')
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

        recordIssues {
            tools {
                mavenConsole()
                java()
                javaDoc()
                checkStyle()
            }
        }

        archiveJunit('target/*-reports/*.xml') {
            allowEmptyResults()
            retainLongStdout()
            testDataPublishers {
                publishTestStabilityData()
            }
        }

        jacocoCodeCoverage {
            sourcePattern('src/**')
        }
    }

    logRotator {
        artifactNumToKeep(3)
    }
}
