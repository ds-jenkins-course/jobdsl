folder("_RESENI")

job('_RESENI/bootstrap') {
  scm {
    git {
      remote {
        github("ds-jenkins-course/jobdsl.git")
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
