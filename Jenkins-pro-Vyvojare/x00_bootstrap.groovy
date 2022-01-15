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
        'Jenkins-pro-Vyvojare/x01_lab_folders.groovy', 
        'Jenkins-pro-Vyvojare/x02_lab03.groovy', 
      ], 
      'DELETE')
  }
}
