import numpy as np
import pandas as pd
from IPython.display import display


miasta_lista = ["Wrocław", "Opole", "Kraków", "Poznań", "Warszawa", "Gdańsk", "Szczecin", "Łódź", "Lublin", "Katowice"]
powierzchnia_lista = [292.8, 149, 147.9, 7.29]
populacja_lista = [643782, 128140, 1200, 350]

print(miasta_lista[0])

if "Opole" in miasta_lista:
    print("Opole jest na liście")

powierzchnia_lista = powierzchnia_lista + powierzchnia_lista

#for powierzchnia in powierzchnia_lista:
#    print(powierzchnia)

for i in range(len(miasta_lista)):
    print(miasta_lista[i].upper())




arr = [
    ["Wrocław", "Kłodzko", "Wałbrzych"],
    ["Białystok", "Suwałki", "Augustów"],
    ["Kraków", "Wieliczka", "Wadowice"],
]

# Zadanie 1
for wojewodztwo in arr:
    for miasto in wojewodztwo:
        print(miasto)


print("------------ ----")

arr2 = ["Polska", "Niemcy", "Czechy", "Korea Południowa", "USA", "Rosja", "Chiny", "Japonia", "Francja", "Włochy", "Luksemburg"]

# Zadanie 2
for m, n in zip(miasta_lista, arr2):
    print(m, n)


kolor = 122, 255, 0

print(kolor[0])
print(kolor[1])
print(kolor[2])

print(f"R: {kolor[0]}, G: {kolor[1]}, B: {kolor[2]}")

populacja_dict = {
    "Wrocław": 643782,
    "Opole": 128140,
    "Złe Mięso": 195,
    "Radom": 210532,
    "Paryż": 134,
    "Paryż": 2148000,
    "Biłgoraj": 27106,
}

powierzchnia_dict = {
    "Wrocław": 292.8,
    "Opole": 149,
    "Złe Mięso": 4.38,
    "Radom": 111.8,
    "Paryż": 9.13,
    "Paryż": 105.4,
    "Biłgoraj": 21.1,
}

print(populacja_dict["Wrocław"])
print(populacja_dict.keys())
print(populacja_dict.values())
print(populacja_dict.items())

for miasto, populacja in populacja_dict.items():
    print(f"{miasto}: {populacja}")

matrix = np.array([1,1,2,3,5,8,13,21,34,55,89,144])
matrix = matrix + matrix

powierzchnia_tab = np.array(list(powierzchnia_dict.values()))
print(f"Powierzchnia_dict: {list(powierzchnia_dict.values())}")
print(f"Powierzchnia_tab: {powierzchnia_tab}")

dane_ustandaryzowane = (powierzchnia_tab - powierzchnia_tab.mean()) / powierzchnia_tab.std()
dane_ustandaryzowane = np.round(dane_ustandaryzowane, 2)
print(f"Dane ustandaryzowane: {dane_ustandaryzowane}")

matrix[-1]
matrix = dane_ustandaryzowane.copy()
print(matrix)

matrix = np.add(matrix, 3.14)

print(matrix)

print(f"Filtered matrix: {matrix[matrix > 4]}")

seria = pd.Series([1,2,3,4], index = ["a", "b", "c", "d"])

print(f"Seria: \n {seria}")

print(f"Seria b-d: \n {seria["b":"d"]}")

seria_ind = pd.Series([1, 2, 3, 4], index=[4, 3, 2, 1])

print("---")
print(seria_ind[3]) # na czwarte pozycji
print("---")
print(seria_ind.loc[3]) # o indexie z numerem 3
print("---")
print(seria_ind.iloc[3]) # na czwartej pozycji 


# 

lista1 = ["E1", "E2", "E3", "E4"]
lista2 = ["A1", "A2", "A3", "A4"]

lista12_series = pd.Series(lista1, index=lista2)
print(lista12_series)


#

df = pd.DataFrame({"populacja": populacja_dict, "powierzchnia": powierzchnia_dict})
display(df)

print("----")
print(df.index, df.columns, sep="\n") # wyświetla indeksy i kolumny
df.loc["Radom", "powierzchnia"] = 112 # zmienia wartość w komórce

print(df.T) # transpozycja

print("----")
print(f"Transpozycja 1: {df.T[['Wrocław']]}") 
print("----")
print(f"Transpozycja 2: {df.T['Wrocław']}") 
print("----")


