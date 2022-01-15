job('_RESENI/go') {
  scm {
    git {
      remote {
        github("ds-jenkins-course/jobdsl")
      }
    } 
  }
  steps {
    dsl(
      [
        'Jenkins-pro-Vyvojare/01_lab_folders.groovy', 
        'Jenkins-pro-Vyvojare/02_lab03.groovy', 
      ], 
      'DELETE')
  }
}
