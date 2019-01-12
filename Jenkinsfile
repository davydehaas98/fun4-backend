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
        sh 'mvn test'
        sh 'mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=admin -Dsonar.password=admin || true'
      }
      post {
        always {
          junit '**/target/surefire-reports/*.xml'
        }
      }
    }
    stage('Deploy') {
      steps {
        sh 'docker build -t fun4-backend .'
        sh 'docker rm -f fun4-backend || true'
        sh 'docker run -d -p 4041:4041 -e profile=development -e TZ=Europe/Amsterdam --restart=always --name fun4-backend fun4-backend'
        sh 'docker image prune -f'
      }
    }
  }
  post {
    always {
      cleanWs()
    }
  }
}