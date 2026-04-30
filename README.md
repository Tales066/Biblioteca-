# 📚 BiblioTech (Em atualização)

> Um sistema de gerenciamento de biblioteca robusto desenvolvido em Java, focado em organização de dados.

![Status do Projeto](https://img.shields.io/badge/status-em%20desenvolvimento-green)

---


## 🛠️ Tecnologias e Ferramentas

O projeto foi construído utilizando as seguintes tecnologias:

* **Java SE**: Linguagem principal para lógica de negócio.
* **Makefile**: Para automação de compilação e execução rápida.

---

## 📁 Estrutura de Pastas

A organização do código segue o padrão de responsabilidade única:

```text
project/
├─ src/
│  ├─ Main.java                # Ponto da aplicação
│  ├─ Biblioteca.java          # Gerenciamento de acervos e regras
│  ├─ Livro.java               # Modelo de dados dos livros + controle de estoque
│  ├─ Usuario.java             # Classe abstrata do perfil básico dos usuários
│  ├─ Graduacao.java           # Tipo de usuário 1
│  ├─ Posgraduacao.java        # Tipo de usuário 2
│  ├─ Emprestimo.java          # Controle de transações e prazos
│  ├─ EmprestimoExcecao.java   # Exceção personalizada do sistema
│  ├─ Exibivel.java            # Interface para padronizar exibição
│  └─ ConsoleUtils.java        # Utilitário para limpar tela e pausar menu
│
├─ bin/                        # Arquivos compilados (.class)
│
└─ Makefile                   # Automação de build e execução
```

## 📁 Requisitos

- Java 8 ou superior
- Make (opcional, para facilitar compilação)

## Como Compilar e Executar

### Usando Make

```bash
make all    # Compila e cria os arquivos na pasta bin, caso ele ainda não possua
make        # Compila todos os arquivos .java
make run    # Executa a classe Main
make clean  # Remove arquivos .class
```
### Usando Manualmente

```bash
javac src/*.java           # Compila todos os arquivos
java -cp src Main          # Executa a classe Main
```
