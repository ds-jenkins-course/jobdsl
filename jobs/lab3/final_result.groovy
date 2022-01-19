def name = "mira"
def surname = "hedl"

def fldr_name = "_LABS/LAB3/"

folder(fldr_name)

job("$fldr_name/final") {
    description("Generated solution for LAB3 - Building from GitHub")
    keepDependencies(false)
    scm {
        git {
            remote {
                github("jenkins-for-developers/$name-$surname-java", "https")
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
            goals('clean package -Dmaven.test.skip=True')
            mavenInstallation('Maven 3.3.9')
            properties(skipTests: true)
            goals('clean')
        }
        shell("java -jar target/moje-apka-*.jar")
    }
    wrappers {
        timestamps()
    }
}
