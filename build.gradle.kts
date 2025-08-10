
plugins {
    id("java")
    application // 'application' 플러그인 추가
}

group = "com.elixcore.vallus"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}


application {
    mainClass.set("com.elixcore.vallus.SignalingServer") // 👈 [중요] 실제 패키지 경로와 클래스 이름으로 변경
}