# Chat

Este projeto tem por objetivo desenvolver um Chat na linguagem java.
Para complementar a avaliação do desempenho do aluno na Disciplina de Linguagem de Programação Orientada a Objetos II do curso de Ciência da Computação da Universidade Luterana do Brasil - ULBRA.

# Definição

O trabalho consiste em criar uma aplicação Cliente/Servidor para que seja possível realizar conversas em grupo entre diferentes usuários
Requisitos

    O servidor deverá aguardar requisições dos clientes na porta 8976
    Deve possibilitar a conexão de até 100 clientes simultaneamente
    Todas as mensagens enviadas devem ser armazenadas em um arquivo no servidor. Quando o cliente se conectar, deverão ser recebidas as últimas 20 mensagens
    O cliente, ao iniciar, deve pedir ao usuário o IP e a porta do servidor e o nickname desejado
    Sempre que um usuário do grupo enviar uma mensagem, todos os clientes conectados devem exibir a mesma (inclusive o cliente que enviou)
    Um cliente deve poder funcionar com o servidor de qualquer outro colega e vice-versa
    Toda a comunicação deve ser em modo texto
    No máximo em duplas
    Constatação de cópia/plágio ou não saber como determinada parte do código funciona é nota zero (independente de quem copiou)

# Protocolo de Comunicação
# Entrar no Grupo 

Cliente envia comando ENTRAR juntamente com o nickname desejado. O servidor verifica se já existe um cliente com o mesmo nickname (se existir deve retornar um erro), caso não exista, deve retornar a lista das últimas 20 mensagens para o cliente que se conectou e uma mensagem para os demais informando que o cliente entrou no grupo.

Requisição:

ENTRAR nickname

Resposta para o cliente que se conectou:
MSG nickname1 Ola
MSG nickname2 Quer tc
...

Resposta para todos os demais clientes:
MSG nickname Entrou no grupo

Resposta em caso de erro
ERRO mensagem de erro

# Enviar mensagem

Cliente envia comando MSG juntamente com a mensagem que deve ser enviada ao grupo. Caso OK, enviar a mensagem para todos os usuários conectados (inclusive o cliente que enviou). Caso erro, enviar mensagem de erro.

Requisição:

MSG Estou a fim de falar com alguém

Resposta (para todos os clientes conectados):
MSG nickname Estou a fim de falar com alguém

Resposta em caso de erro
ERRO mensagem de erro

# Sair do Grupo

Cliente envia comando SAIR para o servidor. O servidor retorna uma mensagem de retorno para todos os clientes avisando que o nickname saiu do grupo e desconecta o cliente (lembrando que, após isto, o nickname deve ser liberado para uso).

Requisição:
SAIR

Resposta:
MSG nickname saiu do grupo

# Importante:

    O servidor deve poder funcionar com qualquer cliente que saiba conversar o protocolo definido acima.
    O cliente deve poder funcionar com qualquer servidor que saiba conversar o protocolo definido acima.
    O servidor deverá ser capaz de aceitar requisições de múltiplos clientes ao mesmo tempo.
    Todo protocolo é em modo texto.
    O trabalho pode ser em duplas
    O aluno (ou dupla) deverá estar presente na aula no dia da entrega para apresentar o trabalho ao professor, sob pena de 50% do valor do trabalho em caso de ausência

