library(EBImage)

img <- readImage("testna_slika6b.jpg")

partLen = 89

img.width <- dim(img)[1]
img.height <- dim(img)[2]

print(sprintf("Width %s,Height %s",img.width, img.height))

Y <- 0

while (Y<img.width) {
  part.width <- if((Y+partLen)>img.width) (img.width-Y) else partLen
  X <- 0
  while (X<img.height) {
    part.height <- if((X+partLen)>img.height) (img.height-X) else partLen
    # print(sprintf("%s %s , %s %s", X, X+part.height, Y, Y+part.width))
    
    part <- img[Y:(Y+part.width), X:(X+part.height), ]
    #display(round(part/max(part),0))
    #print(laplacian(part, normalised = FALSE))
    # display(part)
    X <- X + part.height
  }
  Y <- Y + part.width
}

