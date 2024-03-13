import matplotlib.pyplot as plt
import numpy as np

x = np.linspace(start=-2, stop=2, num=100)
y = x**2
plt.plot(x, y)
plt.show()


fig, ax = plt.subplots(nrows=1, ncols=2, figsize=(10, 4))

x = np.arange(start=0, stop=2, step=0.1)
y = x**2
ax[0].plot(x, y, marker=".")
ax[0].set_xlabel("x", fontsize=14)
ax[0].set_ylabel("y", fontsize=14)

x = np.linspace(start=0, stop=6, num=100)
y = np.sin(x) + np.sin(3 * x)
ax[1].plot(x, np.sin(x), label="składnik 1", alpha=0.8, linestyle="dashed")
ax[1].plot(x, np.sin(3 * x), label="składnik 2", alpha=0.8, linestyle="dotted")
ax[1].plot(x, y, label="suma", linewidth=3)
ax[1].set_xlabel("x", fontsize=14)
ax[1].set_ylabel("y", fontsize=14)
ax[1].legend()
fig.tight_layout()
plt.show()


t = np.linspace(0, 2 * np.pi, 600)
x = np.sin(3 * t)
y = np.cos(5 * t)

fig, ax = plt.subplots(nrows=1, ncols=2, figsize=(11, 4))

ax[0].plot(t, x, label="x")
ax[0].plot(t, y, label="y")
ax[0].set_xlabel("t", fontsize=14)
ax[0].set_ylabel("wartość", fontsize=14)
ax[0].legend(fontsize=12)
ax[0].set_title("wykres na osi czasu")

ax[1].plot(x, y)
ax[1].set_xlabel("x", fontsize=14)
ax[1].set_ylabel("y", fontsize=14)
ax[1].axis("square")
ax[1].set_title("przestrzeń zmiennych stanu")

fig.tight_layout()
plt.show()

x = np.linspace(0, 20, 1001)
y = np.zeros_like(x)

N = 12100
for i in range(1, N + 1, 2):
    y += np.sin(i * x) / i

plt.plot(x, y)
plt.show()