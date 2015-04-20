compile:
	javac boundary/*.java controller/*.java model/*.java utils/*.java

play: compile
	java boundary.PlayerApplication

build:
	java boundary.BuilderApplication
