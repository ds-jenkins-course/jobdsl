job('_RESENI/go') {
    git("https://github.com/ds-jenkins-course/jobdsl.git")
    steps {
        dsl([
            'Jenkins-pro-Vyvojare/01_lab_folders.groovy', 
            ], 'DELETE')
    }
}
