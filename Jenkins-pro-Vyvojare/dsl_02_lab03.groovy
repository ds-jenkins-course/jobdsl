job("LAB03/final_solution") {
    scm {
        git("")
    }
}


job('_LABS/create_lab_dsljobs') {
  scm {
    git {
      remote {
        github("ds-jenkins-course/idea")
      }
    } 
  }
  steps {
    dsl(
      [
        'Jenkins-pro-Vyvojare/lab03/solution.groovy', 
      ], 
      'DELETE')
  }
}
