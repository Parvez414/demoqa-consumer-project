# Production-grade Dockerfile for DemoQA React SPA Test Automation
FROM maven:3.9.6-eclipse-temurin-17

# Install Google Chrome and dependencies
RUN apt-get update && apt-get install -y --no-install-recommends \
    wget \
    gnupg \
    ca-certificates \
    curl \
    unzip \
    && wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | gpg --dearmor -o /usr/share/keyrings/google-chrome.gpg \
    && echo "deb [arch=amd64 signed-by=/usr/share/keyrings/google-chrome.gpg] http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list \
    && apt-get update \
    && apt-get install -y --no-install-recommends google-chrome-stable \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Copy pom.xml and source files
COPY pom.xml .
COPY src ./src

# Set default entrypoint for headless test execution
ENTRYPOINT ["mvn", "test", "-Dheadless=true"]
