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
        sh './gradlew fatJar'
      }
    }
    stage('Finish') {
      parallel {
        stage('Finish') {
          steps {
            echo 'We finished building'
          }
        }
        stage('Deployment') {
          steps {
            archiveArtifacts './build/**/*.jar'
          }
        }
        stage('ListDir') {
          steps {
            sh 'ls -R'
          }
        }
      }
    }
  }
}