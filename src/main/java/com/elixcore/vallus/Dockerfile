# 1. 베이스 이미지 선택 (Java 17 JDK 포함)
# 경량화된 리눅스(Jammy)에 OpenJDK 17이 설치된 이미지를 사용합니다.
FROM eclipse-temurin:17-jdk-jammy

# 2. 작업 디렉토리 설정
# 컨테이너 내에서 명령어가 실행될 기본 경로를 지정합니다.
WORKDIR /app

# 3. 소스 코드 복사
# 로컬에 있는 SignalingServer.java 파일을 컨테이너의 /app 디렉토리로 복사합니다.
COPY SignalingServer.java .

# 4. 자바 코드 컴파일
# 복사된 .java 파일을 컴파일하여 .class 파일을 생성합니다.
RUN javac SignalingServer.java

# 5. 포트 노출
# 컨테이너가 9090 포트를 외부에 노출하도록 설정합니다.
# Railway가 이 정보를 사용하여 네트워크를 구성합니다.
EXPOSE 9090

# 6. 애플리케이션 실행
# 컨테이너가 시작될 때 실행할 명령어를 지정합니다.
CMD ["java", "SignalingServer"]