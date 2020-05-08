# Srage 1 - Build
FROM node:12.7-alpine as node
WORKDIR /usr/src/app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build-prd

# Stage 2 - Deploy
FROM nginx:1.13.12-alpine
COPY --from=node /usr/src/app/dist/processor-web /usr/share/nginx/html
COPY ./NGINX/nginx.conf /etc/nginx/conf.d/default.conf
