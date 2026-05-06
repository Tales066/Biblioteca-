# 📚 BiblioTech (Em atualização)

> Um sistema de gerenciamento de biblioteca robusto desenvolvido em Java, focado em organização de dados.

![Status do Projeto](https://img.shields.io/badge/status-em%20desenvolvimento-green)

---

## 🛠️ Estrutura Geral do código

O projeto foi desenvolvido com a implementação de dois módulos de interface distintos — **Administrador (ADMIN)** e **Estudantes** (abrangendo **Graduação** e **Pós-Graduação**) — com o objetivo de estruturar e gerenciar, de forma organizada e eficiente, os dados associados a perfis de usuários com diferentes níveis de acesso e responsabilidades.


```text
project/

├─ Menu Estudantes/
│  ├─ Pegar Livro              # Onde ele pode pegar os Livros que estão cadastrados na biblioteca                 
│  ├─ Devolver Livro           # Onde o usuário pode realizar a devolução do livro
│  ├─ Ver perfil               # Aqui o usuário visualiza o seu perfil, assim como seu empréstimos ativos
│  ├─ Buscar por autor         # O usuário tem a alternativa de procurar livros do autor que deseja
│  └─ Meu histórico            # Aqui o usuário possui a visualização de todos os seus empréstimos
└─  Sair

├─ Menu Administrador/
│  ├─ Listar Usuários          # Possui a visualização de todos os usuários registrados no sistema
│  ├─ Adicionar Usuário        # Pode registrar um novo usuário no sistema
│  ├─ Adicionar Livro          # Aqui será feito o cadastro dos livros (Nome, Autor, Genero e Quantidade)
│  ├─ Buscar por autor         # Ele tem a alternativa de procurar livros do autor que deseja
│  ├─ Ver histórico            # Aqui o administrador tem a visualização de todos os empréstimos feitos pelos usuários
│  └─ Ver perfil usuários      # Aqui o administrador tem a visualização de todos os perfis usuários
└─  Sair
```        
---

## 🛠️ Tecnologias e Ferramentas

O projeto foi construído utilizando as seguintes tecnologias:

* **Java SE**: Linguagem principal utilizada para a implementação da lógica de negócio, com compatibilidade garantida para a versão **Java 8 ou superior**, para a compilação do código.
* **Makefile**: Para automação de compilação e execução rápida.

---

## 📁 Estrutura do código 

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
│  ├─ ConsoleUtils.java        # Utilitário para limpar tela e pausar menu
│  ├─ Cores.java               # Utilitário para controlar as cores do sistema
│  ├─ Log.java                 # Utilitário para as Annotation do sistema
│  ├─ Menu.java                # Organização e as opções de navegação do menu
│  ├─ Perfil.java              # Gerenciamento de perfis dentro do sistema (Admin e Estudantes)
│  ├─ UI.java                  # Organização do menu dos usuários
│
├─ bin/                        # Arquivos compilados (.class)
│
└─ Makefile                   # Automação de build e execução
```

## 📁 Compilação e Execução
```bash
make all    # Compila e cria os arquivos na pasta bin, caso ele ainda não possua
make run    # Executa as classes 
make clean  # Remove arquivos .class

```
