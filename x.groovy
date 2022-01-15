folder("_RESENI")

job('_RESENI/bootstrap') {
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
        'Jenkins-pro-Vyvojare/00_bootstrap.groovy'
      ], 
      'DELETE'
      )
  }
}
