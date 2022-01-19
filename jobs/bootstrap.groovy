job('_LABS/create_lab_dsljobs') {
  parameters {
    stringParam('REPO_JMENO')
    stringParam('REPO_PRIJMENI')
  }
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
        'jobs/lab3/final_result.groovy',
      ], 
      'DELETE')
  }
}
