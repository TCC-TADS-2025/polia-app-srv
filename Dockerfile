# Stage 1: build
FROM maven:3.9.4-eclipse-temurin-21 AS builder
WORKDIR /app

# Copia pom.xml e baixa dependências (cache layer)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia o código-fonte e gera o JAR
COPY src ./src
RUN mvn clean package -DskipTests -B

# Stage 2: runtime
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Defina um usuário não-root (só se quiser reforçar segurança)
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copia o JAR do estágio anterior
COPY --from=builder /app/target/*.jar app.jar

# Expondo porta (padrão Spring Boot é 8080)
EXPOSE 8080

# Variáveis de ambiente (opcionais)
ENV JAVA_OPTS="-Xms256m -Xmx512m"

# Comando de inicialização
ENTRYPOINT ["java", "-Duser.timezone=America/Sao_Paulo", "-jar", "app.jar"]
