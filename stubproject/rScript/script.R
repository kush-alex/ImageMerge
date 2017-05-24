print(commandArgs(trailingOnly=TRUE))

args <- commandArgs(trailingOnly = TRUE)

#remove.packages(c("imager"))

# local({r <- getOption("repos")
# r["CRAN"] <- "http://cran.r-project.org"
# options(repos=r)
# })
# 
# source("https://bioconductor.org/biocLite.R")
# if(!require(stringi)){install.packages("stringi")}
# if(!require(jpeg)){install.packages("jpeg")}
# if(!require(imager)){install.packages("imager")}
# if(!require(EBImage)){biocLite("EBImage")}
# if(!require(spatstat)){install.packages("spatstat")}
# if(!require(mmand)){install.packages("mmand")}
# if(!require(SDMTools)){install.packages("SDMTools")}
# install.packages("Runiversal") 

library(stringi)
library(jpeg)
library(imager)
library(EBImage)
library(spatstat)
library(mmand)
library(SDMTools)

parts <- 10
pxlsize <- 5

# i1 <- load.image('D:/Uni/Diplom/SpringStubProject/stubproject/rScript/1.jpg')
# i2 <- load.image('D:/Uni/Diplom/SpringStubProject/stubproject/rScript/2.jpg')
print("START")
i1 <- load.image(args[1])
i2 <- load.image(args[2])

# i1 <- load.image('D:/temp/StubSpringProject/stubproject/rScript/9.jpg')
# i2 <- load.image('D:/temp/StubSpringProject/stubproject/rScript/10.jpg')

# split_image <- function(img,parts_number){
#   x <- imsplit(img,'x', parts_number)
#   imglist <- list()
#   for(i in 1:parts_number) {
#     y <- imsplit(x[[i]],'y', parts_number)
#     imglist<- c(imglist, y)
#   }
#   return(imglist)
# }
# 
# normalize_image <- function(img, parts_number){
#   list <- split_image(img, parts_number)
#   for(index in 1:length(list)) {
#     part<-list[[index]]
#     
#   }
# }

# imglist1 <- split_image(i1, parts)
# imglist2 <- split_image(i2, parts)

i11 <- i1
i21 <- i2

x1 <- imsplit(i11,"c") %>% add
x2 <- imsplit(i21,"c") %>% add

smoothness <- 0.5

# thmb1 <- resize(x1,200,200) 
# thmb2 <- resize(x2,200,200) 

# thmb3 <- resize(thmb1,dim(i11)[1],dim(i11)[2]) 
# thmb4 <- resize(thmb2,dim(i21)[1],dim(i21)[2]) 

m1 <- matrix(i1,dim(i1)[1],dim(i1)[2])
m2 <- matrix(i2,dim(i2)[1],dim(i2)[2])

thmb3 <- as.matrix(blur(as.im(m1), smoothness))
thmb4 <- as.matrix(blur(as.im(m2), smoothness))

# thmb3 <- as.matrix(gaussianSmooth(m1, 3))
# thmb4 <- as.matrix(gaussianSmooth(m2, 3))

thmb3 <- as.im(thmb3)
thmb4 <- as.im(thmb4)


# print(imcol(i11,1))

# normalize_image(x1,3)

#filter <- as.cimg(function(x,y) sign(x-5),10,10)

# sub1 <- imsubtract(as.matrix(x1),thmb3$v)
# sub2 <- imsubtract(as.matrix(x2),thmb4$v) 
sub1 <- m1 - thmb3$v
sub2 <- m2 - thmb4$v


sub3 <- as.matrix(blur(as.im(sub1), smoothness))
sub4 <- as.matrix(blur(as.im(sub2), smoothness))


sub3 <- as.im(sub3)
sub4 <- as.im(sub4)

sub1 <- sub1 - sub3$v
sub2 <- sub2 - sub4$v

# sub1 <- sweep(as.matrix(x1), 1, thmb3$v)
# sub2 <- sweep(as.matrix(x2), 1, thmb4$v)

# greyscale1 <- round(sub1/max(sub1),2)
# greyscale2 <- round(sub2/max(sub2),2)

# thresh1 <- thresh(sub1, 10, 10, 0.02)
# thresh2 <- thresh(sub2, 10, 10, 0.02)

# thresh1 <- greyscale1 > otsu(greyscale1, range = c(-1, 1))
# thresh2 <- greyscale2 > otsu(greyscale2, range = c(-1, 1))

thresh1 <- sub1 > otsu(sub1, range = c(-1, 1))
thresh2 <- sub2 > otsu(sub2, range = c(-1, 1))

threshNumeric1 <- thresh1 * 1
threshNumeric2 <- thresh2 * 1

kernelShape1 <- makeBrush(5, shape='diamond')  
kernelShape2 <- makeBrush(5, shape='diamond')  
# 
# threshNumeric1 <- dilate(threshNumeric1, kernelShape)
# 
# threshNumeric2 <- dilate(threshNumeric2, kernelShape)

threshNumeric1 <- erode(threshNumeric1, kernelShape1)
threshNumeric1 <- erode(threshNumeric1, kernelShape1)
threshNumeric2 <- erode(threshNumeric2, kernelShape2)
threshNumeric2 <- erode(threshNumeric2, kernelShape2)
threshNumeric1 <- dilate(threshNumeric1, kernelShape1)

threshNumeric2 <- dilate(threshNumeric2, kernelShape2)
# threshNumeric1 <- dilate(threshNumeric1, kernelShape1)
# 
# threshNumeric2 <- dilate(threshNumeric2, kernelShape2)

# thresh1 <- sub1 > otsu(sub1, range = c(-3, 3))
# thresh2 <- sub2 > otsu(sub2, range = c(-3, 3))

# thresh2 <- !thresh2


res <- i1

n <- 1

set_pixel <- function(z,x,y){
    if(z == 0) {
    # print(paste(z, x, y, sep="-", collapse=", "))
    res[x,y,1, 1] <<- i2[x,y,1,1]
    res[x,y,1, 2] <<- i2[x,y,1,2]
    res[x,y,1, 3] <<- i2[x,y,1,3]
  }
}

mapply(set_pixel , threshNumeric2, row(threshNumeric2),col(threshNumeric2))

# for(x in n:(dim(i1)[1])){
#    for(y in n:(dim(i1)[2])){
#      if(threshNumeric2[x,y] == 0){
#      # if(mean(thresh2[x-n:x+n,y-n:y+n])>mean(thresh1[x-n:x+n,y-n:y+n])){
#        # if(mean(thresh2[x-n:x+n,y-n:y+n])>mean(thresh1[x-n:x+n,y-n:y+n])){
#           print(paste(x, y, sep="-", collapse=", "))
#           res[x,y,1, 1] <- i2[x,y,1,1]
#           res[x,y,1, 2] <- i2[x,y,1,2]
#           res[x,y,1, 3] <- i2[x,y,1,3]
#         }
#         # else {
#           # res[x,y,1, 1] <- i1[x,y,1,1]
#           # res[x,y,1, 2] <- i1[x,y,1,2]
#           # res[x,y,1, 3] <- i1[x,y,1,3]
#         # }
#    }
# }
save.image(res,args[3])
# pdf('D:/Uni/Diplom/SpringStubProject/stubproject/rScript/filename3.pdf')
# pdf('D:/temp/StubSpringProject/stubproject/rScript/filename3.pdf')
# plot(i1)
# plot(i2)
# plot(as.raster(threshNumeric1))
# plot(as.raster(threshNumeric2))
# plot(res)
# dev.off()
# 
print("DONE")
# old.par <- par(mfrow=c(3, 4))
# 
# plot(i11, main="image 1 part")
# plot(i21, main="image 2 part")

# plot(thmb1, main="thmb1")
# plot(thmb2, main="thmb2")
# plot(thmb3, main="thmb3")
# plot(thmb4, main="thmb4")
# # 
# plot(x1, main="x1")
# plot(x2, main="x2")
# 
# image(sub1, main="x1-thmb3")
# image(sub2, main="x2-thmb4")

# image(greyscale1 , main="greyscale11")
# image(greyscale2 , main="greyscale21")
# 
# plot(as.raster(thresh1) , main="thresh1")
# plot(as.raster(thresh2) , main="thresh2")
# plot(as.raster(threshNumeric1) , main="threshNumeric1")
# plot(as.raster(threshNumeric2) , main="threshNumeric2")
# 
# par(old.par)