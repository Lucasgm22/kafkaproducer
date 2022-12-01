# springkafka-poc

Projeto prova de conceito para usar [SpringKafka](https://docs.spring.io/spring-kafka/reference/html/) para produzir e consumir mensagens em um tópico.

# Como rodar
- Instalar o [kafka](https://www.apache.org/dyn/closer.cgi?path=/kafka/3.3.1/kafka_2.13-3.3.1.tgz);
- Seguir os passos para subir o servidor e criar o tópico;
- Rodar o projeto:
```
    sh gradlew bootrun
```

# Postar no tópico
- Usar o produtor no terminal, explicado no [tutorial](https://www.apache.org/dyn/closer.cgi?path=/kafka/3.3.1/kafka_2.13-3.3.1.tgz);

OU

- Fazer uma request `POST` para `/message`.

    Ex: 
    ```
        curl -X POST localhost:8080/message?topic={topicname} 
        -H "Content-Type: application/json" 
        -d '{"key": 1, "value": "bbbbbb"}'  
    ```
