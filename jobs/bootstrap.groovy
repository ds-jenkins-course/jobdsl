job('_LABS/create_lab3') {
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
    dsl(['jobs/lab3/final_result.groovy'], 'DELETE')
  }
}

job('_LABS/create_lab4') {
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
    dsl(['jobs/lab4/final_result.groovy'], 'DELETE')
  }
}


job('_LABS/create_lab5') {
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
    dsl(['jobs/lab5/final_result.groovy'], 'DELETE')
  }
}

