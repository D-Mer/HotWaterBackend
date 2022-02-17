node{
    stage('git checkout'){
//         updateGitlabCommitStatus name: 'jenkins', state: 'pending'
        checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: '1cbee7e4-b684-4b3a-a4a0-06ad542c4b2c', url: 'https://github.com/D-Mer/HotWaterBackend']]])
        echo '=== git clone end ==='
    }

//     stage('set test database'){
//         try{
//         updateGitlabCommitStatus name: 'jenkins', state: 'running'
//         sh encoding: 'UTF-8', label: '', returnStdout: true, script: '''
// bash /etc/shell/test_db.sh
// '''
//         echo '=== set test database end ==='
//         }
//         catch(exc){
//             updateGitlabCommitStatus name: 'jenkins', state: 'failure'
//             echo '=== build failure ==='
//             throw exc
//         }
//     }

    stage('clean'){
//         updateGitlabCommitStatus name: 'jenkins', state: 'running'
        sh label: 'clean', returnStatus: true, script: 'mvn clean'
        echo '=== mvn clean end ==='
    }

//     stage('test'){
//         updateGitlabCommitStatus name: 'jenkins', state: 'running'
//         sh label: 'test', returnStatus: true, script: 'mvn test -P test'
//     }

//     stage('reset database'){
//         try{
//         updateGitlabCommitStatus name: 'jenkins', state: 'running'
//         sh encoding: 'UTF-8', label: '', returnStdout: true, script: '''
// bash /etc/shell/reset_db.sh
// '''
//         echo '=== reset database end ==='
//         }
//         catch(exc){
//             updateGitlabCommitStatus name: 'jenkins', state: 'failure'
//             echo '=== build failure ==='
//             throw exc
//         }
//     }

//     stage('report'){
//         jacoco classPattern: '**/classes/**/controller,**/classes/**/serviceImpl', exclusionPattern: '**/*Test*.class', sourceInclusionPattern: '**/*ServiceImpl.java,**/*Controller.java'
//     }

    stage('test'){
//         updateGitlabCommitStatus name: 'jenkins', state: 'running'
        sh label: 'test', returnStatus: true, script: 'mvn test -P test'
        echo '=== mvn test end ==='
    }

    stage('package'){
//         updateGitlabCommitStatus name: 'jenkins', state: 'running'
//         sh label: 'package', returnStatus: true, script: 'mvn package -P prod -Dmaven.test.skip=true'
        sh label: 'package', returnStatus: true, script: 'mvn package -P main -Dmaven.test.skip=true -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true'
        echo '=== mvn package end ==='
    }

// 下面这个是远程部署到非jenkins所在的服务器上，本项目在本机部署
    def remote = [:]
        remote.name = 'jenkins'
        remote.host = '172.19.241.40'
        remote.user = 'jh'
        remote.password = 'jhlhk2132LYHLSJ'
        remote.allowAnyHosts = true
    stage('deploy'){
// 		updateGitlabCommitStatus name: 'jenkins', state: 'running'
        sshPut remote: remote, from: 'target/HotWaterBackend-0.0.1.jar', into: '/home/jh/artifacts/'
        sshCommand remote: remote, command: """
            cd artifacts
            ./deploy.sh
        """
        echo '=== deploy end ==='
	}

    stage('archive'){
        archiveArtifacts allowEmptyArchive: true, artifacts: 'target/*.jar', onlyIfSuccessful: true
//         updateGitlabCommitStatus name: 'jenkins', state: 'success'
    }
}
