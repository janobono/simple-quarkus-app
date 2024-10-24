FROM public.ecr.aws/nginx/nginx:alpine
COPY dist/simple-quarkus-app-gui/browser /usr/share/nginx/html
# new
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
