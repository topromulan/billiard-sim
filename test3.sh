javac SimpleVector.java SimpleCoordinates.java  Ball.java Test3.java ||
	exit 1

OLDSTTY=`stty -g`

stty -echo -icanon min 1

java Test3

stty $OLDSTTY
