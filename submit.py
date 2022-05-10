import os
from sys import argv

MY_TOKEN = "9ZbzKWhTaDqk"
command = f"python3 dirlididi.py submit {argv[1]} {MY_TOKEN} {argv[2]}"

os.system(command)
os.system(f"rm {argv[2].split('.')[0]}.class")
