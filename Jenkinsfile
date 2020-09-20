pipeline {
    agent { 
        node { 
            label 'jenkins_slave'
        }
    }
    environment {
        AZ_DOCKER_KEY_ID     = 'jenkins-user-for-docker-repository'
        REPOSITORY           = 'hansleolml/demo_spring'
        AZ_K8S_KEY_ID        = 'jenkins-user-for-k8s-azure'
    }
    stages {
        stage('Git Clone'){
            steps {
                git credentialsId: 'jenkins-user-for-git-repository', url: 'https://github.com/hansleolml/demos_springdocker.git'
            }
        }/*
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
        }*/
        stage('deploy k8s') {
            steps {
                    withCredentials([azureServicePrincipal(AZ_K8S_KEY_ID)]) {
                        
                        //sh 'az login --service-principal -u $AZURE_CLIENT_ID -p $AZURE_CLIENT_SECRET -t $AZURE_TENANT_ID'
                        //sh 'az aks get-credentials --resource-group kubernetesGroup --name nameAKSCluster'
                        //sh 'kubectl get nodes'
                        sh 'az login --service-principal --username a3f87ec2-b91e-4ed0-ae54-381b36fd8995 --password NJC~8f3T.BuYR3EhuLj-h-Y_wCdAl3~tbB --tenant c4a66c34-2bb7-451f-8be1-b2c26a430158'
                        sh 'az aks get-credentials --resource-group kubernetesGroup --name nameAKSCluster'
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
