job('_RESENI/bootstrap') {
    git("https://github.com/ds-jenkins-course/jobdsl.git")
    steps {
        dsl([
            'Jenkins-pro-Vyvojare/lab_folders.groovy', 
            'Jenkins-pro-Vyvojare/01_bootstrap.groovy'
            ], 'DELETE')
    }
}
