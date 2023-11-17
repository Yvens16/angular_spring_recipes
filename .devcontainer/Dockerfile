FROM node:latest

RUN npm install -g npm@9.5.1 && \
    npm install -g @angular/cli && \
    apt-get update && apt-get install -y default-mysql-client && rm -rf /var/lib/apt/lists/*

