pipeline {
    agent { 
    	node { 
    		label 'jenkins_slave'
    	}
    }
    environment {
        AZ_ACCESS_KEY_ID     = credentials('3f56ad64-c46f-4253-a70d-424d0402ab97')
        AZ_DOCKER_KEY_ID     = credentials('jenkins-user-for-docker-repository')
        //AZ_DOCKER_KEY_ID     = 'jenkins-user-for-docker-repository'
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
                    customImage = docker.build("hansleolml/demo_spring:${env.BUILD_ID}")
                }
            }
        }
        stage('Push Docker') {
            steps {
    	    	script {
                    docker.withRegistry('https://registry.hub.docker.com','${AZ_DOCKER_KEY_ID}') {
                        customImage.push()
                        customImage.push('latest')
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
