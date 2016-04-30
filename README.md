# DropwizardHello
Dropwizard test project

#Quick start
0. Open console in project's root directory
1. Run: mvn clean install
2. Run: java -jar target/dropwizard-hello-1.0-SNAPSHOT.jar server configuration.yml
3. Open browser in: http://localhost:8080 or run:
curl http://localhost:8080
or
curl -i -X GET -H "Content-Type: application/x-www-form-urlencoded" --data-urlencode "name=AAA" http://localhost:8080/link
or
curl -i -X POST -H "Content-Type: application/x-www-form-urlencoded" -d '{"id":"111","name":"AAA","url":"https://bla.pl","type":"ARTICLE","modifiedOn":1459707869123}' http://localhost:8080/link
or run:
/dropwizard-hello/src/main/resources/link/post.html