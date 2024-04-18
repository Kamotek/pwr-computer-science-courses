"""
Task: implement LSS method.

Here are some useful hints:
# https://docs.scipy.org/doc/scipy/reference/generated/scipy.linalg.lstsq.html
# https://www.statsoft.pl/textbook/stathome_stat.html?https%3A%2F%2Fwww.statsoft.pl%2Ftextbook%2Fstglm.html
"""
import urllib.request
import os
from typing import Tuple

import matplotlib.pyplot as plt
import numpy as np
import pandas as pd


def fetch_data_file() -> str:
    """Download a file with target data to fit LSS algo in."""
    file = "GDP_happiness.csv"
    url_server = "https://byes.pl/wp-content/uploads/datasets/" + file
    if not os.path.isfile(file):
        urllib.request.urlretrieve(url_server, file)
    else:
        print(f"File already exists!")
    return file


def read_data_vectors() -> Tuple[np.ndarray, np.ndarray]:
    """Read target data and obtain X and Y vectors without NaNs."""
    gdp_happines_df = pd.read_csv(fetch_data_file(), index_col=[0])
    gdp_happines_df = gdp_happines_df.fillna(gdp_happines_df.mean(axis=0))
    gdp_happines_df.tail()

    X = gdp_happines_df["GDP per capita"].values
    Y = gdp_happines_df["happiness"].values

    return X, Y


def get_polynomial_form(polynomial_degree: int) -> np.ndarray:
    """
    Get array with form of polynomial.

    :param polynomial_degree: a degree of polynomial
        [[0], [1]] - 1st order, [[0], [1], [2]] - 2nd order, 
        [[0], [1], [2], [3]] - 3rd order, and so on...
    :return: a array with degrees of polynomial
    """
    return np.array([[i] for i in range(polynomial_degree + 1)])


def print_polynomial(theta: np.ndarray, precision: int = 3) -> str:
    """Return string representation of polynomial."""
    terms = []
    for degree, coeff in enumerate(theta):
        formatted_coeff = f"{coeff[0]:.{precision}f}"  # Format with specified precision
        formatted_coeff = formatted_coeff.rstrip('0').rstrip('.')  # Remove trailing zeros and decimal point if the last significant number is 0
        if '.' not in formatted_coeff:  # If coefficient is integer, add decimal point
          formatted_coeff += '.0'
        if degree == 0:
            term = f"{formatted_coeff}*x^{degree}"
        else:
            term = f"+ {formatted_coeff}*x^{degree}"
        terms.append(term)
    return " ".join(terms)



def least_squares_solution(
        X: np.ndarray, Y: np.ndarray, polynomial_degree: int
) -> np.ndarray:
    """
    Compute theta matrix with coefficients of polynomial fitted by LSS.

    :param X: argument vector, shape = (N, )
    :param Y: target vector, shape = (N, ) 
    :param polynomial_degree: degree of fitted polynomial

    :return: theta matrix of polynomial, shape = (1, polynomial_degree + 1)
    """
    # Step 1: Create the Vandermonde matrix
    X_vander = np.vander(X, polynomial_degree + 1, increasing=True)

    # Step 2: Compute the Least Squares Solution
    theta = np.linalg.inv(X_vander.T @ X_vander) @ X_vander.T @ Y

    
    # Step 3: Return the Coefficient Matrix
    return theta.reshape(polynomial_degree+1, 1)


def generalised_linear_model(X: np.ndarray, T: np.ndarray) -> np.ndarray:
    """
    Compute values for generalised linear model.

    :param X: argument vector, shape = (N, )
    :param T: theta matrix of polynomial, shape = (1, polynomial_degree + 1)
    :return: regressed values, shape = (N, )
    """
    return sum([coeff * X ** degree for degree, coeff in enumerate(T)])


def visualise_LSS_method(X: np.ndarray, Y: np.ndarray, T: np.ndarray):
    """
    Visualise LSS model on fancy Matplotlib plot.

    :param X: input argument vector
    :param Y: input target vector
    :param T: theta vector with coefficients of ploynomial
    """
    X_test = np.linspace(start=X.min(), stop=X.max(), num=300)
    Y_pred = generalised_linear_model(X_test, T)
    plt.scatter(X, Y, color="tab:blue", label="real data")
    plt.plot(X_test, Y_pred, color="tab:orange", label="estimated function")
    # add approx error sum info
    plt.text(
        0.5, 0.5, f"Approximation error sum: {calculate_approximation_errors(X, Y, T):.2f}",
        fontsize=8, color="blue", transform=plt.gca().transAxes
    )

    plt.xlabel("x - GDP", fontsize=14)
    plt.ylabel("y - happiness", fontsize=14)
    plt.title(f"Fitted: \n {print_polynomial(T, precision=10)}")
    plt.legend()
    plt.show()


def calculate_approximation_errors(X: np.ndarray, Y: np.ndarray, T: np.ndarray) -> float:
    """
    Calculate the sum of approximation errors for each point on the chart.

    :param X: input argument vector
    :param Y: input target vector
    :param T: theta vector with coefficients of polynomial
    :return: sum of approximation errors
    """
    Y_pred = generalised_linear_model(X, T)
    errors = np.abs(Y - Y_pred)
    return np.sum(errors)



if __name__ == "__main__":
    # here is a playground for your tests!
    X, Y = read_data_vectors()
    T = least_squares_solution(X, Y, 20)
    print(print_polynomial(T))
    print('gefe')
    visualise_LSS_method(X, Y, T)
