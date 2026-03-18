JAVAC = javac
JAVA = java

SRC_DIR = src
SRC = $(wildcard $(SRC_DIR)/*.java)

all:
	$(JAVAC) $(SRC)

run: all
	$(JAVA) -cp $(SRC_DIR) Main

clean:
	rm -f $(SRC_DIR)/*.class