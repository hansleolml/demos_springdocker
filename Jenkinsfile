pipeline {
    agent { 
    	node { 
    		label 'jenkins_slave'
    	}
    }
    environment {
        AZ_ACCESS_KEY_ID     = credentials('3f56ad64-c46f-4253-a70d-424d0402ab97')
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
        stage('login docker') {
            steps {
	    	script {
	    		docker.withRegistry('https://registry.hub.docker.com','jenkins-user-for-docker-repository') {}
		}
	    }
        }
    }
    post { 
        always { 
            cleanWs()
        }
    }
}
