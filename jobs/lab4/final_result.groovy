def desc = "LAB04 - Artifact a fingerprint"
def fldr_name = "_LABS/LAB4"


folder(fldr_name)

job("$fldr_name/final") {
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
