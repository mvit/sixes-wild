compile:
	javac boundary/*.java controller/*.java model/*.java utils/*.java

play: compile
	java boundary.PlayerApplication

build:
	java boundary.BuilderApplication

gource:
	gource --seconds-per-day 1 --auto-skip-seconds 0.1 --max-files 0
