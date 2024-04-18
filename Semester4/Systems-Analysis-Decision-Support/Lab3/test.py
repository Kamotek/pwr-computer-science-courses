import numpy as np
import matplotlib.pyplot as plt
import pandas as pd

import urllib.request
import os
import seaborn as sns
from IPython.display import display

plik = 'GDP_happiness.csv'
URL = "https://byes.pl/wp-content/uploads/datasets/" + plik
if not os.path.isfile(plik):
    print('Pobieram plik z ', URL)
    urllib.request.urlretrieve(URL, plik)
    print('Pobrano plik')
else:
    print(f'Plik {plik} już jest na dysku')

dane = pd.read_csv(plik, index_col=[0])

dane = dane.fillna(dane.mean(axis=0))
dane.tail()


X = dane['GDP per capita'].values
Y = dane['happiness'].values

'''
plt.figure(figsize=(6,6))
sns.scatterplot(
    x='GDP per capita',
    y='happiness',
    data=dane,
    hue='happiness',
    size='GDP per capita',
    sizes=(20, 200),
    legend='brief',
)
plt.show()
'''

'''
dane = dane.sort_values(by='happiness', ascending=False)

plt.figure(figsize=(12, 6))
sns.barplot(
    x=dane.index,  # Country names as x-values
    y='happiness',
    data=dane,
    color='green'
)
plt.xticks(rotation=90)  # Rotate x-axis labels for better readability
plt.title('Happiness Score by Country')
plt.xlabel('Country')
plt.ylabel('Happiness Score')
plt.ylim(4, 8)  # Set y-axis limits to 0-10
plt.tight_layout()
plt.show()

display(dane)
'''

def model(parametry, x):
  a, b = parametry
  return a * x + b


'''
# W tym miejscu wprowadzasz swoje oszacowania (ang. estimates) wartości parametrów modelu
a_est, b_est = 0.0115, 5.3


a_est = (X @ Y - np.mean(Y) * np.sum(X)) / (X @ X - np.mean(X) * np.sum(X))
b_est = np.mean(Y) - a_est * np.mean(X)


X_test = np.linspace(start=X.min(), stop=X.max(), num=300)
Y_pred = model(parametry=[a_est, b_est], x=X_test)

# Calculate the approximation errors for each point
approximation_errors = Y - model(parametry=[a_est, b_est], x=X)

# Calculate SSE
SSE = np.sum(approximation_errors ** 2)

# Calculate MSE
MSE = SSE / len(X)

# Calculate RMSE
RMSE = np.sqrt(MSE)

print("Sum of Squared Errors (SSE):", SSE)
print("Mean Squared Error (MSE):", MSE)
print("Root Mean Squared Error (RMSE):", RMSE)

# Plot the original data points and the regression line
plt.scatter(X, Y, label='Data')
plt.plot(X_test, Y_pred, color='tab:orange', label='Regression Line')
plt.xlabel('x - PKB na osobę', fontsize=14)
plt.ylabel('y - poczucie szczęścia', fontsize=14)

# Plot the approximation errors
for i in range(len(X)):
    plt.plot([X[i], X[i]], [Y[i], model(parametry=[a_est, b_est], x=X[i])], color='red', linestyle='--')

# Annotate the approximation errors on the plot
for i in range(len(X)):
    plt.text(X[i], Y[i], f"{approximation_errors[i]:.2f}", fontsize=8, color='blue')

plt.legend()
plt.show()


# oblicz błąd przybliżenia dla każdego punktu danych
errors = (Y - model([a_est, b_est], X))**2
# oblicz sumę kwadratów błędów
Q = np.sum(errors)
a_low, b_low = a_est, b_est + Q
a_high, b_high = a_est, b_est - Q
Y_low = model(parametry=[a_low, b_low], x=X_test)
Y_high = model(parametry=[a_high, b_high], x=X_test)

plt.scatter(X, Y)
plt.plot(X_test, Y_pred, color='tab:orange')
plt.plot(X_test, Y_low, color='tab:red')
plt.plot(X_test, Y_high, color='tab:red')
plt.xlabel('x - PKB na osobę', fontsize=14)
plt.ylabel('y - poczucie szczęścia', fontsize=14)
plt.title(f'Błąd przybliżenia: {Q:.2f}', fontsize=16)  # dodaj informację o błędzie
plt.show()

'''
'''
X = X.reshape(1, -1)
print(X)
X = np.vstack([X, np.ones_like(X)])

_T = np.linalg.inv(X @ X.T) @ X @ Y.T.ravel().squeeze()

def plot_fig(X: np.ndarray, Y: np.ndarray, coeff: np.ndarray):
  X_test = np.linspace(start=X.min(), stop=X.max(), num=300)
  func_str = "y = "
  Y_pred = model(coeff, X_test)
  for i, c in enumerate(coeff.ravel()[::-1]):
      func_str += f"{round(c, 4)} * x ** {i} + "

  plt.scatter(X, Y, label='dane rzeczywiste')
  plt.plot(X_test, Y_pred, color='tab:orange', label='estymowany trend')
  plt.xlabel('x - PKB na osobę', fontsize=14)
  plt.ylabel('y - poczucie szczęścia', fontsize=14)
  plt.title(f"Dopasowano funkcję: {func_str[:-2]}")
  plt.legend()
  plt.show()

plot_fig(X[0], Y, _T)
'''

# ----------------------------

from sklearn.linear_model import LinearRegression



def generator(rozklad_x, model, rozklad_z, typ_z):
  # rozklad_x musi w pełni określać macierz X,
  # model musi mieć określone wartości parametrów, za wejście będzie wstawiana X
  # rozkład_z musi w pełni określać macierz Z
  # typ_z może być 'addytywne' lub 'multiplikatywne'

  X = rozklad_x()
  Y_pred = model(X)
  #print(X.shape)
  if typ_z == 'addytywne':
    Y = Y_pred + rozklad_z()
  elif typ_z == 'multiplikatywne':
    Y = Y_pred * rozklad_z()

  return X, Y


N = 100
X, Y = generator(
  rozklad_x=lambda: np.random.uniform(low=0,high=1, size=N),
  model=lambda x: model(parametry=[0.5, 1], x=x),
  rozklad_z=lambda: np.random.normal(loc=0,scale=0.05, size=N),
  typ_z='addytywne',
)

model_lin = LinearRegression()
model_lin.fit(X.reshape(-1,1), Y)
X_test = np.linspace(start=X.min(), stop=X.max(), num=300)
Y_pred = model_lin.predict(X_test.reshape(-1,1))



plt.scatter(X,Y)
plt.plot(X_test, Y_pred, color='tab:orange', linewidth=3)
plt.xlabel('x')
plt.ylabel('y')
plt.show()
