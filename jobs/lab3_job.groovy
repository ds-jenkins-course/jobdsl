job('example') {
    parameters {
        stringParam('JMENO')
        stringParam('PRIJMENI')
    }
    scm {
        github("ds-jenkins-course/jobdsl")
    }
    steps {
        groovyCommand(readFileFromWorkspace('jobs/lab3/final_result.groovy'))
    }
}
