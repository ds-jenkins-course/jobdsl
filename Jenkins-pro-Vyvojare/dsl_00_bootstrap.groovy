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
        'Jenkins-pro-Vyvojare/dsl_01_lab_folders.groovy', 
        'Jenkins-pro-Vyvojare/dsl_02_lab03.groovy', 
      ], 
      'DELETE')
  }
}
