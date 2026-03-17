# 📚 BiblioTech (Em atualização)

> Um sistema de gerenciamento de biblioteca robusto desenvolvido em Java, focado em organização de dados.

![Status do Projeto](https://img.shields.io/badge/status-em%20desenvolvimento-green)

---

## 📸 Previa

Demonstração do Sistema

![Gravação de tela de 17-03-2026 18-19-21](https://github.com/user-attachments/assets/ba9f9a27-6e32-4e04-9e8f-72589c80a24e)

---

## 🛠️ Tecnologias e Ferramentas

O projeto foi construído utilizando as seguintes tecnologias:

* **Java SE**: Linguagem principal para lógica de negócio.
* **Makefile**: Para automação de compilação e execução rápida.
* **Paradigma OO**: Implementação rigorosa de Encapsulamento, Herança e Polimorfismo.

---

## 📁 Estrutura de Pastas

A organização do código segue o padrão de responsabilidade única:

```text
project/
├─ src/
│  ├─ Main.java         # Ponto de entrada da aplicação
│  ├─ Biblioteca.java   # Gerenciamento de acervo e regras
│  ├─ Livro.java        # Modelo de dados dos livros
│  ├─ Usuario.java      # Gestão de perfis e permissões
│  └─ Emprestimo.java   # Lógica de transações e prazos
├─ Makefile             # Scripts de automação
└─ README.md
```

## 📁 Requisitos

- Java 8 ou superior
- Make (opcional, para facilitar compilação)

## Como Compilar e Executar

### Usando Make

```bash
make        # Compila todos os arquivos .java
make run    # Executa a classe Main
make clean  # Remove arquivos .class
```
### Usando Manualmente

```bash
javac src/*.java           # Compila todos os arquivos
java -cp src Main          # Executa a classe Main
```
