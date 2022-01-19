//def name = "mira"
//def surname = "hedl"
//
def fldr_name = "_LABS/LAB4"

folder(fldr_name)

job("$fldr_name/final") {
    description("""Generated solution for LAB 03 - Freestyle build \"Hello Jenkins\" aplikace""")
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
        publishBuild {
            discardOldBuilds(-1, -1, -1, 3)
        }
        archiveArtifacts {
            pattern('target/*.jar')
            pattern('build/test-output/**/*.xml')
            fingerprint(true)
            onlyIfSuccessful()
        }
        fingerprint('**/pom.xml')
    }

    logRotator {
        artifactNumToKeep(3)
    }
}
