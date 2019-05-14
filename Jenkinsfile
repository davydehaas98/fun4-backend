pipeline {
  agent any
  options {
    disableConcurrentBuilds()
    timeout(time: 30, unit: 'MINUTES')
  }
  stages {
    stage('Verify Tools') {
      steps {
        parallel (
          java: {
            sh 'java -version'
            sh 'which java'
          },
          maven: {
            sh 'mvn -version'
            sh 'which mvn'
          },
          docker: {
            sh 'docker --version'
            sh 'which docker'
          },
          dockercompose: {
            sh 'docker-compose --version'
            sh 'which docker-compose'
          }
        )
      }
    }
    stage('Build') {
      steps {
        sh 'mvn clean install -B -DskipTests'
      }
    }
    stage('Test') {
      steps {
        sh 'mvn test || true'
        sh 'mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=admin -Dsonar.password=admin || true'
      }
      post {
        always {
          junit '**/target/surefire-reports/*.xml'
        }
      }
    }
    stage('Deploy development') {
       when {
         branch 'master'
       }
       steps {
         sh 'docker-compose -f docker-compose.development.yml up -d --build --force-recreate'
       }
    }
    stage('Deploy production') {
      when {
        branch 'production'
      }
      steps {
        sh 'docker-compose -f docker-compose.production.yml up -d --build --force-recreate'
      }
    }
  }
  post {
    always {
      cleanWs()
    }
  }
}