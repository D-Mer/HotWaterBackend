node{
    stage('git checkout'){
        updateGitlabCommitStatus name: 'jenkins', state: 'pending'
        checkout([$class: 'GitSCM', branches: [[name: '*/main']], browser: [$class: 'GitHub', repoUrl: 'https://git.nju.edu.cn/'], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '3', url: 'http://212.129.149.40/171250507_IIICEStseB/Backend-iiicestseb']]])
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
        updateGitlabCommitStatus name: 'jenkins', state: 'running'
        sh label: 'test', returnStatus: true, script: 'mvn clean'
        echo '=== git clean end ==='
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

//     stage('clean test'){
//         updateGitlabCommitStatus name: 'jenkins', state: 'running'
//         sh label: 'test', returnStatus: true, script: 'mvn clean'
//     }

    stage('package'){
//         sh label: 'package', returnStatus: true, script: 'mvn package -P prod -Dmaven.test.skip=true'
        sh label: 'package', returnStatus: true, script: 'mvn package -P prod -Dmaven.test.skip=true -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true'
        echo '=== package end ==='
    }

    stage('deploy'){
		sshPublisher(publishers: [sshPublisherDesc(configName: 'tencent', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: '''cd /var/www
./stop.sh
./start.sh''', execTimeout: 5000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '', remoteDirectorySDF: false, removePrefix: 'target/', sourceFiles: 'target/*.jar')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
		updateGitlabCommitStatus name: 'jenkins', state: 'success'
        echo '=== deploy end ==='
	}

    stage('archive'){
        archiveArtifacts allowEmptyArchive: true, artifacts: 'target/HotWaterBackend-0.0.1.jar', onlyIfSuccessful: true
        updateGitlabCommitStatus name: 'jenkins', state: 'success'
    }
}
