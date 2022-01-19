//def name = "mira"
//def surname = "hedl"
//
def fldr_name = "_LABS/LAB3"

folder(fldr_name)

job("$fldr_name/final") {
    description("Generated solution for LAB3 - Building from GitHub")
    keepDependencies(false)
    jdk("OpenJDK 8")
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

    concurrentBuild(true)
    steps {
        maven {
            goals('clean package')
            mavenInstallation('Maven 3.3.9')
            properties(skipTests: true)
        }
        shell("java -jar target/moje-apka-*.jar")
    }
    wrappers {
        timestamps()
        colorizeOutput()
    }
}
