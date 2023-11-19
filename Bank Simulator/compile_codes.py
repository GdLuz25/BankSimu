from os import system
from time import sleep

min = 0.5
file = "server"

system(f"javac {file}.java")
system(f"sudo java {file}")
