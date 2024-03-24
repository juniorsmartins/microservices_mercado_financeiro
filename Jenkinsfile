pipeline {
    agent any

    stages{
        stage('1 - Build Back-end') {
            steps {
                echo 'Limpar e construir os arquivos .jar'

                script {
                    // Comando find para localizar os arquivos build.gradle e pom.xml em cada subdiretório
                    def gradleProjects = sh(script: 'find . -name build.gradle -exec dirname {} \\;', returnStdout: true).trim().split('\n')

                    // Para cada projeto encontrado, execute o comando Gradle ou Maven, dependendo do que for encontrado
                    for (def project in gradleProjects) {
                        sh "cd ${project} && ./gradlew clean build -x test"
                    }
                }
            }
        }

        stage('2 - Unit Tests') {
            steps {
                echo 'Rodar os testes automatizados'

                script {
                    def gradleProjects = sh(script: 'find . -name build.gradle -exec dirname {} \\;', returnStdout: true).trim().split('\n')

                    for (def project in gradleProjects) {
                        sh "cd ${project} && ./gradlew test"
                    }
                }
            }
        }

        stage('3 - Sonar Analysis') {
            steps {
                echo 'Sonarqube roda análise estática do código'

                script {
                    def gradleProjects = sh(script: 'find . -name build.gradle -exec dirname {} \\;', returnStdout: true).trim().split('\n')

                    for (def project in gradleProjects) {

                        withSonarQubeEnv() {
                            sh "cd ${project} && ./gradlew sonar"
                        }
                    }
                }
            }
        }

        stage('4 - Quality Gate') {
            steps {
                echo 'Sonarqube verifica qualidade de código'

                script {
                    def gradleProjects = sh(script: 'find . -name build.gradle -exec dirname {} \\;', returnStdout: true).trim().split('\n')

                    for (def project in gradleProjects) {

                        timeout(time: 1, unit: 'MINUTES') {
                            waitForQualityGate abortPipeline: true
                        }
                    }
                }
            }
        }

        stage('5 - Deploy Back-end') {
            steps {
                echo 'deploying the application...'
            }
        }

        stage('6 - API Tests') {
            steps {
                echo '...'
            }
        }

        stage('7 - Deploy Front-end') {
            steps {
                echo '...'
            }
        }

        stage('8 - Functional Tests') {
            steps {
                echo '...'
            }
        }

        stage('9 - Deploy Prod') {
            steps {
                echo '...'
            }
        }

        stage('10 - Health Check') {
            steps {
                echo '...'
            }
        }
    }
}

