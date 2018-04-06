# payments

As tecnologias utilizadas foram:
- Java 8 (jdk 8)
- Spring boot
- WebServices: REST (Json)
- Database: Embedded Mongo DB
- Build: Maven - utilizando o Maven Wrapper para que não seja necessária uma instalação prévia do Maven
- ReactJS

## Pré requisitos
 - Java 8

## Processo de build e teste da aplicação Payments
O build pode ser feito da seguinte maneira:
##### Unix
> ./mvnw clean install
##### Windows
> ./mvnw.cmd clean install

Para subir a aplicação pode-se rodar o comando:
> java -jar target/payments.jar

Ou gerar a imagem docker
##### Unix
> docker build -t moip:payments
##### Windows
> docker build -f Dockerfile.windows -t moip:payments

E subir o container com a imagem gerada:
> docker run -d -p 8080:8080 --rm moip:payments

A aplicação estará disponível em http://localhost:8080

Wiki com motivação e todas as infos necessárias para:
* [Motivação](https://github.com/otaviosantana/payments/wiki/Motiva%C3%A7%C3%A3o)
* [Testando a aplicação](https://github.com/otaviosantana/payments/wiki/Testando-a-aplica%C3%A7%C3%A3o)
* [Melhorias](https://github.com/otaviosantana/payments/wiki/Melhorias)
