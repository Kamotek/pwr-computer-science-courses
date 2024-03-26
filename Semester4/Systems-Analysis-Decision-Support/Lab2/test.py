from operator import inv
import numpy as np
import matplotlib.pyplot as plt

def test():
    print("This is a test function")
    test = np.arange(12)
    print(test)

    """

    mat = test.reshape(6, -1)

    print(mat)

    A = np.random.rand(1024, 1024)
    B = np.arange(16, 32).reshape(4, -1)

    print("A")
    print(A)
    print("B")
    print(B)

    print("A _ B")
  #  print(A @ B)

#    plt.imshow(A @ A.T)
#    plt.show()

    np.random.seed(32)
    a = np.random.rand(10)
    np.random.seed(32)
    b = np.random.rand(10)

    print(a)
    print(b)
    
    return 0
        """
    N = 12
    x = np.linspace(start=-2, stop=2, num=N)
    y = np.linspace(start=-2, stop=2, num=N)
    X, Y = np.meshgrid(x, y)
    X = X.flatten()
    Y = Y.flatten()

    fig, ax = plt.subplots(1, 2, figsize=(12, 6))

    rozmiar = np.random.normal(loc=300, scale=100, size=N * N)
    ax[0].scatter(X, Y, s=rozmiar, color="red", alpha=0.5)
    ax[0].set_aspect("equal")
    ax[0].set_title("Rozkład normalny rozmiarów")

    rozmiar = np.random.uniform(low=0, high=600, size=N * N)
    ax[1].scatter(X, Y, s=rozmiar, color="red", alpha=0.5)
    ax[1].set_aspect("equal")
    ax[1].set_title("Rozkład równomierny rozmiarów")

    plt.show()
if __name__ == "__main__":
    test()