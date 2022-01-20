def desc = "LAB03 - Freestyle build Hello World aplikace a global tools"
def fldr_name = "_LABS/LAB3"


folder(fldr_name)

job("$fldr_name/final") {
    description("""<hr/ ><i>Generated solution for: <b>${desc}</b</i>><hr/ >""")
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
