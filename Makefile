SRC = ./src/LanguageDetector/*.java
JAR = stat.LanguageDetector-LeGoff-Rudek.jar


run : all 
	@echo "Running..."
	@java -cp ./bin LanguageDetector.LanguageDetermination

all : $(SRC)
	@echo "Compiling..."
	@javac -d ./bin $(SRC)

doc : $(SRC)
	javadoc -private -charset utf-8 -sourcepath src/ -d doc-private/ LanguageDetector

jar : $(SRC)
	jar cvf $(JAR) .

clean : 
	@echo "Cleaning..."
	@rm -rf *~
	@rm -rf ./src/LanguageDetector/*~ 
	@rm -rf ./bin/*.class
	@rm -rf $(JAR)
	@echo "It's clean!"

.PHONY : clean