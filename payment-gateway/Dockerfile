# ===== STAGE 1: build =====
FROM maven:3.9-eclipse-temurin-21-alpine AS build

WORKDIR /workspace

COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 mvn -q -e -U -DskipTests dependency:go-offline

COPY src ./src
RUN --mount=type=cache,target=/root/.m2 mvn -q -e -DskipTests package

# ===== STAGE 2: runtime =====
FROM eclipse-temurin:24-jre-alpine AS runtime

WORKDIR /app

# Usuário não-root
RUN addgroup -S app && adduser -S app -G app
USER app

# Tuning: SerialGC (container <=256MB), menos threads e logs mínimos
ENV JAVA_TOOL_OPTIONS=" -XX:+UseSerialGC  -XX:MaxRAMPercentage=70  -XX:InitialRAMPercentage=10  -Dreactor.netty.ioWorkerCount=2  -Dreactor.netty.pool.leasingStrategy=lifo  -Dreactor.netty.pool.maxConnections=200  -Dreactor.netty.pool.maxIdleTime=60s  -Dfile.encoding=UTF-8  -Duser.timezone=UTC "

COPY --from=build /workspace/target/payment-gateway-*-SNAPSHOT.jar /app/app.jar

EXPOSE 8081

ENTRYPOINT [ "sh", "-c", "java ${JAVA_OPTS} -jar /app/app.jar" ]
