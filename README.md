# Java gRPC
gRPC é um framework de código aberto de alta performance para Remote Procedure Call (chamada de procedimento remoto). Saiba mais em https://grpc.io/.

Este repositório contém uma implementação teste para comunicação entre cliente e servidor utilizando Java.

## Como executar
### Requisitos
- Java JDK (versão recomendada: 19.0.2 ou superior)
- Apache Maven (para instalar o Maven, utilize ```sudo apt install maven``` ou acesse https://maven.apache.org/install.html)

### Passo a passo
1. Clone o projeto e acesse sua pasta
2. Execute ```mvn compile``` para compilar
3. Para rodar o servidor, execute ```mvn exec:java -Dexec.mainClass="tests.Server"```
4. Para rodar o cliente, execute ```mvn exec:java -Dexec.mainClass="tests.Client"``` 
