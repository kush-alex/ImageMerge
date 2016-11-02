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

X <- 0

print(sprintf("Col %s,Row %s",columnsNum, rowNum))

for (column in 0:(columnsNum)) {

}



for (column in 1:(columnsNum)) {
  Y <- 0
  part.width <- if((img.width-(column*partLen))<partLen) (img.width-((column)*partLen)) else partLen
  for (row in 1:(rowNum)) {
    part.height <- if((img.height-(row*partLen))<partLen) (img.height-((row)*partLen)) else partLen
    print(sprintf("%s %s %s , %s %s %s",column, X, X+part.width,row, Y, Y+part.height))
    
    part <- img[X:(X+part.width), Y:(Y+part.height), ]
    #display(part)
    Y <- Y + partLen
  }
  X <- X + partLen
}
