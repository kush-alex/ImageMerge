library("EBImage")
img <- readImage("1.jpg")

partLen = 50

img.width <- dim(img)[1]
img.height <- dim(img)[2]

print(sprintf("Width %s,Height %s",img.width, img.height))

additionColumn <- if (img.width %% partLen > 0) 1 else 0
columnsNum = img.width %/% partLen + additionColumn

additionRow <- if (img.height %% partLen > 0) 1 else 0
rowNum <- img.height %/% partLen + additionRow

Y <- 0

print(sprintf("Col %s,Row %s",columnsNum, rowNum))

while (Y<img.width) {
  part.width <- if((Y+partLen)>img.width) (img.width-Y) else partLen
  X <- 0
  while (X<img.height) {
    part.height <- if((X+partLen)>img.height) (img.height-X) else partLen
    print(sprintf("%s %s , %s %s", X, X+part.height, Y, Y+part.width))
    
    part <- img[Y:(Y+part.width), X:(X+part.height), ]
    #display(part)
    X <- X + part.height
  }
  Y <- Y + part.width
}

