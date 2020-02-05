pipeline {
    agent any
    options {
        disableConcurrentBuilds()
        timeout(time: 30, unit: "MINUTES")
    }
    stages {
        stage("Verify Tools") {
            steps {
                parallel (
                    java: {
                        sh "java -version"
                        sh "which java"
                    },
                    maven: {
                        sh "mvn -version"
                        sh "which mvn"
                    },
                    docker: {
                        sh "docker --version"
                        sh "which docker"
                    },
                    dockercompose: {
                        sh "docker-compose --version"
                        sh "which docker-compose"
                    }
                )
            }
        }
        stage("Build") {
            agent {
                docker { image 'maven:3.6.3-jdk-11-openj9' }
            }
            steps {
                sh "mvn clean install -B -DskipTests"
            }
        }
        stage("Test") {
            agent {
                docker { image 'maven:3.6.3-jdk-11-openj9' }
            }
            steps {
                sh "mvn test"
                sh "mvn sonar:sonar -Dsonar.host.url=https://sonarqube.davydehaas.nl -Dsonar.login=532ee2f4408a94e20f667fc0835dc18a257d31a5 || true"
            }
            post {
                always {
                    junit "**/target/surefire-reports/*.xml"
                }
            }
        }
        stage("Deploy production") {
            when {
                branch "master"
            }
            steps {
                sh "docker-compose -f docker-compose.production.yml up -d --build --force-recreate"
            }
        }
        stage("Deploy development") {
            when {
                branch "development"
            }
            steps {
                sh "docker-compose -f docker-compose.development.yml up -d --build --force-recreate"
            }
        }
    }
    post {
        always {
            cleanWs()
        }
    }
}