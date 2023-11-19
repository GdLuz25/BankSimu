from os import remove, listdir

places = ["./", "./classes"]

for p in places:
    files = listdir(p)
    
    for f in files:
        if f.endswith(".class"):
            remove(f"{p}/{f}")
