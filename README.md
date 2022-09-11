
# Desafio Elo7

Desafio de Programação proposta pela empresa Elo7

## Descrição da necessidade
Por meio de uma API Rest, será necessário enviar comandos de navegação para uma nave em outro planeta.
A nave deverá se orientar através de um plano cartesiano, com os seguintes movimentos permitidos:

```
- M -> Andar para a frente na direção que está 1 posição.
- L -> Virar a sonda para a esquerda (90 graus)
- R -> Virar a sonda para a direita (90 graus)
```
## Exemplo

```
Tamanho da área do planeta : 5x5

Posição de pouso da sonda 1: x=1, y=2 apontando para Norte
Sequencia de comandos: LMLMLMLMM
Posição final da sonda: x=1 y=3 apontando para Norte

Posição de pouso da sonda 2: x=3, y=3 apontando para Leste
Sequencia de comandos: MMRMMRMRRML
Posição final da sonda: x=5 y=1 apontando para Norte
```


## Funcionalidades da versão

- CRUD's para os objetos necessários(Planet, Ship e Mission).
- Movimentação da nave.
- Validação de movimentos - evitando colisões.
- Validação de movimentos - dentro dos limites do planeta.
- Tratamento de exceções.
- Testes unitários para as regras negociais.


## Documentação da API

A documentação da API encontra-se disponível em:
https://desafio-elo7.herokuapp.com/swagger-ui/index.html
