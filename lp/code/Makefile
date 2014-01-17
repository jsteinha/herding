ifndef $(NAME)
  NAME=DEFAULT
endif

prefix=state/execs
include=jars/guava-14.0.1.jar:jars/fig.jar

% : %.java Makefile
	rm -f *.class
	javac -cp .:$(include) $<
	mkdir -p classes/$(NAME)
	mv *.class classes/$(NAME)/
	mkdir -p $(prefix)/$(NAME)

run:
	java -Xmx10g -cp .:$(include):classes/$(NAME) Main -execPoolDir $(prefix)/$(NAME) -Main.dataset $(DATASET)

run_silent:
	java -Xmx10g -cp .:$(include):classes/$(NAME) Main -execPoolDir $(prefix)/$(NAME) -log.stdout false -Main.dataset $(DATASET)


