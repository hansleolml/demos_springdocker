pipeline {
    agent { 
        node { 
            label 'jenkins_slave'
        }
    }
    environment {
        AZ_ACCESS_KEY_ID     = credentials('3f56ad64-c46f-4253-a70d-424d0402ab97')
        AZ_DOCKER_KEY_ID     = 'jenkins-user-for-docker-repository'
        REPOSITORY           = 'hansleolml/demo_spring'
    }
    stages {
        stage('Git Clone'){
            steps {
                git credentialsId: 'jenkins-user-for-git-repository', url: 'https://github.com/hansleolml/demos_springdocker.git'
            }
        }
        stage('Prueba login') {
            steps {
                sh("hostname")
                sh("az --version")
                sh("ls -la")
            }
        }
        stage('Docker Build') {
            steps {
                script{
                    customImage = docker.build(REPOSITORY+":${env.BUILD_ID}")
                }
            }
        }
        stage('Push Docker') {
            steps {
                script {
                    docker.withRegistry('',AZ_DOCKER_KEY_ID) {
                        customImage.push()
                        customImage.push('latest')
                    }
                }
            }
        }
        stage('Ver datos') {
            steps {
                script {
                    echo "My client id is $AZURE_CLIENT_ID"
                    echo "My client secret is $AZURE_CLIENT_SECRET"
                    echo "My tenant id is $AZURE_TENANT_ID"
                    echo "My subscription id is $AZURE_SUBSCRIPTION_ID"
                }
            }
        }
        stage('deploy k8s') {
            steps {
                script {
                    withCredentials([azureServicePrincipal('<mySrvPrincipal>')]) {
                        sh '''
                            az login --service-principal -u $AZURE_CLIENT_ID -p $AZURE_CLIENT_SECRET -t $AZURE_TENANT_ID
                            az account set -s $AZURE_SUBSCRIPTION_ID
                        '''
                    }
                }
            }
        }
    }
    /*
    post { 
        always { 
            cleanWs()
        }
    }
    */
}
