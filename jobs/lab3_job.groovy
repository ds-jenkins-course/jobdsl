job('example') {
    parameters {
        stringParam('JMENO')
        stringParam('PRIJMENI')
    }
    scm {
        github("ds-jenkins-course/jobdsl")
    }
    steps {
        dsl {
            def a = readFileFromWorkspace('jobs/lab3/final_result.groovy')
            def b = "\n\ncreateLab3('xxx', 'yyy')"
            text(a + b)
        }
    }
}
