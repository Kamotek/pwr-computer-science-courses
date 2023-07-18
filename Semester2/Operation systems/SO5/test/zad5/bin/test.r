setwd("/home/kamil/Documents/SO5/test/zad5/src")
par(mfrow=c(3,1))

#KOLEJNE
data1  <- read.csv("Strategia1-maxLoadFrom1to100.csv", header = TRUE, sep = ',')
data2  <- read.csv("Strategia2-maxLoadFrom1to100.csv", header = TRUE, sep = ',')
data3  <- read.csv("Strategia3-maxLoadFrom1to100.csv", header = TRUE, sep = ',')

data1  <- data1[-1,]
data2  <- data2[-1,]
data3  <- data3[-1,]

#PROCESSES FROM 10 TO 1000 - avg load

x1   <- seq(10, 1000, by=10)
y1    <- data1$Average_Load
x2   <- seq(10, 1000, by=10)
y2    <- data2$Average_Load
x3   <- seq(10, 1000, by=10)
y3    <- data3$Average_Load


plot(x1, y1,ylim = c(10,50),type = "l",col = c("red"),
     main="Load/Process number",sub = "",
     xlab="Processes", ylab="Average Load")
lines(x2, y2,type = "l",col=c("blue"),lwd = 3)
lines(x3, y3,type = "l",col=c("green"),lwd = 3)
grid(nx = NULL, ny = NULL,
     lty = 2,      # Grid line type
     col = "gray", # Grid line color
     lwd = 2)
legend(1, 50, legend=c("Leniwy Student", "Ambitny Student", "Samarytanin Student"),
       col=c("red", "blue","green"), lty=1, cex=0.8)

#PROCESSES FROM 10 TO 1000 -querries

x1   <- seq(10, 1000, by=10)
y1    <- data1$Querries_Count
x2   <- seq(10, 1000, by=10)
y2    <- data2$Querries_Count
x3   <- seq(10, 1000, by=10)
y3    <- data3$Querries_Count


plot(x1, y1,ylim = c(10,100000),type = "l",col=c("red"),
     main="Querries/Process number",sub = "",
     xlab="Processes", ylab="Average Load")
lines(x2, y2,type = "l",col=c("blue"),lwd = 3)
lines(x3, y3,type = "l",col=c("green"),lwd = 3)
grid(nx = NULL, ny = NULL,
     lty = 2,      # Grid line type
     col = "gray", # Grid line color
     lwd = 2)
legend(1, 90000, legend=c("Leniwy Student", "Ambitny Student", "Samarytanin Student"),
       col=c("red", "blue","green"), lty=1:2, cex=0.8)

#PROCESSES FROM 10 TO 1000 -querries

x1   <- seq(10, 1000, by=10)
y1    <- data1$Migration_count
x2   <- seq(10, 1000, by=10)
y2    <- data2$Migration_count
x3   <- seq(10, 1000, by=10)
y3    <- data3$Migration_count


plot(x1, y1,ylim = c(400,60000),type = "l",col=c("red"),
     main="Migrations/Process number",sub = "",
     xlab="Processes", ylab="Average Load")
lines(x2, y2,type = "l",col=c("blue"),lwd = 3)
lines(x3, y3,type = "l",col=c("green"),lwd = 3)
grid(nx = NULL, ny = NULL,
     lty = 2,      # Grid line type
     col = "gray", # Grid line color
     lwd = 2)
legend(1, 100000, legend=c("Leniwy Student", "Ambitny Student", "Samarytanin Student"),
       col=c("red", "blue","green"), lty=1:2, cex=0.8)


