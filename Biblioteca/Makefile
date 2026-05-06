BIN_DIR = bin
SRC_DIR = src

all: create_bin compile

create_bin:
	mkdir -p $(BIN_DIR)

compile:
	javac -d $(BIN_DIR) $(SRC_DIR)/*.java

run:
	java -cp $(BIN_DIR) Main

clean:
	rm -rf $(BIN_DIR)

refresh: clean all run