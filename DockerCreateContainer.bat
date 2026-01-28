docker run -d -p 8080:8080 \
  -e DB_USER=postgres \
  -e DB_PASSWORD=Jose@1234 \
  -e DB_URL=jdbc:postgresql://host.docker.internal:5432/EVNTMNGT \
  --name event-api \
  event-management-api