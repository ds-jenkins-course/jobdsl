job('_LABS/create_lab_dsljobs') {
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
        'jobs/lab3_job.groovy',
      ], 
      'DELETE')
  }
}
