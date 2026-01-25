echo Vamos lá!

echo Recriando diretórios e compilando...;
rm -rf dist && mkdir dist &&
javac -d ./dist/ -cp "/usr/share/java/mysql-connector-java.jar" -s ./src/ ./src/**/*.java ./src/**/**/*.java

java -cp "dist:/usr/share/java/mysql-connector-java.jar" application.Program
