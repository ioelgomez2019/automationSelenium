pipeline {
    agent any

    tools {
            maven 'Maven3'
            jdk   'JDK17'
    }

    triggers {
        githubPush()
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/ioelgomez2019/automationSelenium.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean -DskipTests test-compile'
            }
        }

        stage('Tests') {
            parallel {

                stage('Login') {
                    steps {
                        bat 'mvn test -Dtest=LoginRunner'
                    }
                    post {
                        always {
                            publishHTML(target: [
                                allowMissing:          false,
                                alwaysLinkToLastBuild: true,
                                keepAll:               true,
                                reportDir:             'target/reports',
                                reportFiles:           'login-report.html',
                                reportName:            'Login Report'
                            ])
                        }
                    }
                }

                stage('Product') {
                    steps {
                        bat 'mvn test -Dtest=ProductRunner'
                    }
                    post {
                        always {
                            publishHTML(target: [
                                allowMissing:          false,
                                alwaysLinkToLastBuild: true,
                                keepAll:               true,
                                reportDir:             'target/reports',
                                reportFiles:           'product-report.html',
                                reportName:            'Product Report'
                            ])
                        }
                    }
                }

                stage('E2E') {
                    steps {
                        bat 'mvn test -Dtest=E2ERunner'
                    }
                    post {
                        always {
                            publishHTML(target: [
                                allowMissing:          false,
                                alwaysLinkToLastBuild: true,
                                keepAll:               true,
                                reportDir:             'target/reports',
                                reportFiles:           'e2e-report.html',
                                reportName:            'E2E Report'
                            ])
                        }
                    }
                }

            }
        }
    }

    post {
        failure {
            mail to:      'tu-correo@gmail.com',
                 subject:  "FALLO: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body:     """El pipeline falló.
Job:   ${env.JOB_NAME}
Build: ${env.BUILD_NUMBER}
URL:   ${env.BUILD_URL}"""
        }
        always {
            junit allowEmptyResults: true,
                  testResults: 'target/reports/*-junit.xml'
            cleanWs()
        }
    }
}