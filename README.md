## Dependency
### lombok.jar

This project uses [Lombok](https://projectlombok.org/) `v1.18.24`.

1. Download jar file from [Project Lombok](https://projectlombok.org/).
1. Put jar file on the path where `STS.ini` exists.
    * Windows: `sts-3.*.*.RELEASE\`
    * Mac: `/Applications/STS.app/Contents/Eclipse/`
1. Start lombok.jar library installer by running command:  
`java -jar lombok.jar`
1. Choose STS(eclipse) which you use to open this project.
1. Click Install and done!
1. To check if lombok is installed correctly, open `STS.ini` file and see if it has follwing statement:  
`-javaagent:[path to STS]/lombok.jar`

### Installing from Maven

1. Right-click the project from package explorer in STS.
1. Select `Maven > Update Project...` and run update
1. Try `Force Update of Snapshots/Releases` if it didn't work.

### Maven Update 이후 확인

실행 잘되던 프로젝트에 maven - update project... 를 클릭후 정상적으로 실행이 안되서 찾아보니  
프로젝트 우클릭 - Maven - Update Project 실행시 maven 라이브러리 경로가 삭제되는 현상이 발생한다고 한다.

[링크](https://jokerkwu.tistory.com/109) 참고해서 수정