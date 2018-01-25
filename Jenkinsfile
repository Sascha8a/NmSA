pipeline {
  agent any
  stages {
    stage('Clean') {
      steps {
        sh '''








chmod a+x gradlew && ./gradlew clean'''
      }
    }
    stage('Build') {
      steps {
        sh './gradlew assemble'
      }
    }
    stage('Finish') {
      parallel {
        stage('Finish') {
          steps {
            echo 'We finished building'
          }
        }
        stage('') {
          steps {
            sh 'ls -R'
          }
        }
      }
    }
  }
}