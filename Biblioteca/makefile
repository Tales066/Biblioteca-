BIN_DIR = bin

SRC_DIR = src

ifeq ($(OS),Windows_NT)
    CP_SEP = ;
else
    CP_SEP = :
endif

CLASSPATH = "$(SRC_DIR)$(CP_SEP)$(BIN_DIR)"

all: create_bin
	javac -cp $(CLASSPATH) $(SRC_DIR)/*.java -d $(BIN_DIR)

run:
	java -cp $(BIN_DIR) Main

create_bin:
	@mkdir -p $(BIN_DIR)

clean:
	@rm -rf $(BIN_DIR)
	@echo "Arquivos temporários removidos."


refresh: clean all run