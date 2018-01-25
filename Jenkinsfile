pipeline {
  agent any
  stages {
    stage('Clean') {
      steps {
        sh './gradlew clean'
      }
    }
    stage('Build') {
      steps {
        sh './gradlew assemble'
      }
    }
    stage('Finish') {
      steps {
        echo 'We finished building'
      }
    }
  }
}