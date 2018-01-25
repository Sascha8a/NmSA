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
            sh 'java -jar ./build/libs/NmSA-all-1.3.4.jar'
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