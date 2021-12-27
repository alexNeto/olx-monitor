# Olx monitor

Simples sistema que verifica novos items em um determinado link da olx e envia notificação cada vez que um novo item é cadastrado.

## Rodando localente

### Dependencias

 - Maven
 - Java 11
 - Docker
 - Postgres (pode ser usada a que está no docker-compose)
 - Um bot do telegram
 
 1. `mvn install`
 2. criar 2 variáveis de ambiente
    1. BOT_USERNAME: com o username do bot que você criou
    2. BOT_TOKEN: com o token do bot que você criou
 3. `docker-compose up --build -d` e espera terminar de subir o ambiente
 4. o servico fica disponível na porta 3000


