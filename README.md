# Sudoku — Java CLI

Jogo de Sudoku rodando inteiramente no terminal, desenvolvido em Java puro.
Este projeto é um fork do desafio original da [Digital Innovation One](https://github.com/digitalinnovationone/sudoku), com melhorias progressivas aplicadas sobre a base original.

---

## Agradecimentos

Projeto iniciado a partir do desafio proposto pela **[DIO — Digital Innovation One](https://www.dio.me/)** e disponibilizado no repositório [digitalinnovationone/sudoku](https://github.com/digitalinnovationone/sudoku). Obrigado à DIO e aos instrutores pelo ponto de partida sólido e pelo desafio bem estruturado.

---

## Melhorias aplicadas neste fork

| # | Melhoria | Arquivo(s) |
|---|---|---|
| 1 | Correção de typos nas mensagens ao usuário | `Main.java` |
| 2 | Atributo `label` do enum marcado como `final` | `GameStatusEnum.java` |
| 3 | Validação real das regras do Sudoku (linhas, colunas e quadrantes 3×3) | `SudokuValidator.java` *(novo)* |
| 4 | Mensagens de erro específicas por violação | `Main.java`, `Board.java` |
| 5 | Substituição dos 81 argumentos de CLI por menu interativo de dificuldade | `Main.java`, `PuzzleRepository.java` *(novo)* |
| 6 | Refatoração de `Board` com métodos auxiliares nomeados e helper `allCells()` | `Board.java` |

---

## Tecnologias

- Java 17+
- Sem dependências externas — apenas a JDK padrão

---

## Estrutura do projeto

```
src/br/com/dio/
├── Main.java                     # Entrypoint e loop principal do jogo
├── model/
│   ├── Space.java                # Modelo de uma célula do tabuleiro
│   ├── Board.java                # Lógica central do jogo
│   ├── GameStatusEnum.java       # Estados: NON_STARTED, INCOMPLETE, COMPLETE
│   └── SudokuValidator.java      # Validação de linhas, colunas e quadrantes
└── util/
    ├── BoardTemplate.java        # Template ASCII do tabuleiro 9×9
    └── PuzzleRepository.java     # Tabuleiros pré-definidos por dificuldade
```

---

## Como rodar

### Pré-requisitos

- Java 17 ou superior instalado
- Verificar: `java -version`

### Compilar

```bash
# Na raiz do projeto
javac -d out $(find src -name "*.java")
```

### Executar

```bash
java -cp out br.com.dio.Main
```

Nenhum argumento necessário — o menu interativo cuida de tudo.

---

## Como jogar

Ao iniciar o programa, um menu principal é exibido:

```
Selecione uma das opções a seguir
1 - Iniciar um novo Jogo
2 - Colocar um novo número
3 - Remover um número
4 - Visualizar jogo atual
5 - Verificar status do jogo
6 - Limpar jogo
7 - Finalizar jogo
8 - Sair
```

**Opção 1** exibe o menu de dificuldade:

```
Escolha a dificuldade:
1 - Fácil
2 - Médio
3 - Difícil
```

O tabuleiro é exibido automaticamente após a escolha. Células fixas (dadas pelo jogo) não podem ser alteradas. Use as opções 2 e 3 para inserir e remover números informando coluna (0–8) e linha (0–8).

**Opção 5** valida o estado atual e exibe erros específicos, por exemplo:

```
O jogo contém erros:
  - Número 5 duplicado na linha 3
  - Número 7 duplicado na coluna 6
  - Número 2 duplicado no quadrante (2,1)
```

**Opção 7** finaliza o jogo — só aceita conclusão se todas as células estiverem preenchidas e sem nenhuma violação das regras do Sudoku.

---

## Regras do Sudoku

- O tabuleiro tem 9×9 células divididas em 9 quadrantes de 3×3
- Cada linha deve conter os números de 1 a 9 sem repetição
- Cada coluna deve conter os números de 1 a 9 sem repetição
- Cada quadrante 3×3 deve conter os números de 1 a 9 sem repetição

---

## Autor

**Juan Carlo Andrade Cruz**
Fork e melhorias: [github.com/donjuan029/sudoku](https://github.com/donjuan029/sudoku)

Projeto base: [digitalinnovationone/sudoku](https://github.com/digitalinnovationone/sudoku) — DIO
