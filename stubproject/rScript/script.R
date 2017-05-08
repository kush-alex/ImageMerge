print(commandArgs(trailingOnly=TRUE))

args <- commandArgs(trailingOnly = TRUE)

#remove.packages(c("imager"))
#source("https://bioconductor.org/biocLite.R")
#if(!require(stringi)){install.packages("stringi")}
#if(!require(jpeg)){install.packages("jpeg")}
#if(!require(imager)){install.packages("imager")}
#if(!require(EBImage)){biocLite("EBImage")}
#if(!require(spatstat)){install.packages("spatstat")}

library(stringi)
library(jpeg)
library(imager)
library(EBImage)
library(spatstat)

parts <- 10
pxlsize <- 5

# i1 <- load.image('D:/Uni/Diplom/SpringStubProject/stubproject/rScript/1.jpg')
# i2 <- load.image('D:/Uni/Diplom/SpringStubProject/stubproject/rScript/2.jpg')
#print("START")
# i1 <- load.image(args[1])
# i2 <- load.image(args[2])

i1 <- load.image('D:/temp/StubSpringProject/stubproject/rScript/15.jpg')
i2 <- load.image('D:/temp/StubSpringProject/stubproject/rScript/16.jpg')

split_image <- function(img,parts_number){
  x <- imsplit(img,'x', parts_number)
  imglist <- list()
  for(i in 1:parts_number) {
    y <- imsplit(x[[i]],'y', parts_number)
    imglist<- c(imglist, y)
  }
  return(imglist)
}

normalize_image <- function(img, parts_number){
  list <- split_image(img, parts_number)
  for(index in 1:length(list)) {
    part<-list[[index]]
    
  }
}

imglist1 <- split_image(i1, parts)
imglist2 <- split_image(i2, parts)

i11 <- i1
i21 <- i2

x1 <- imsplit(i11,"c") %>% add
x2 <- imsplit(i21,"c") %>% add

# thmb1 <- resize(x1,200,200) 
# thmb2 <- resize(x2,200,200) 

# thmb3 <- resize(thmb1,dim(i11)[1],dim(i11)[2]) 
# thmb4 <- resize(thmb2,dim(i21)[1],dim(i21)[2]) 

m1 <- matrix(i1,dim(i1)[1],dim(i1)[2])
m2 <- matrix(i2,dim(i2)[1],dim(i2)[2])

thmb3 <- as.matrix(blur(as.im(m1), sigma=2))
thmb4 <- as.matrix(blur(as.im(m2), sigma=2))

thmb3 <- as.im(thmb3)
thmb4 <- as.im(thmb4)


# print(imcol(i11,1))

# normalize_image(x1,3)

#filter <- as.cimg(function(x,y) sign(x-5),10,10)
# plot(convolve(x1,filter),main="x1")
# plot(convolve(x2,filter),main="x1")

sub1 <- as.im(as.matrix(x1)-thmb3$v)
sub2 <- as.im(as.matrix(x2)-thmb4$v)

# binary1 <- round(sub1/max(sub1),2)
# binary2 <- round(sub2/max(sub2),2)

# thresh1 <- thresh(sub1, 10, 10, 0.02)
# thresh2 <- thresh(sub2, 10, 10, 0.02)

thresh1 <- sub1 < 1 & sub1> -1
thresh2 <- sub2 < 1 & sub2> -1

res <- i1

for(x in 1:(dim(i1)[1]-1)){
   for(y in 1:(dim(i1)[2]-1)){
     print(paste(x, y, sep="-", collapse=", "))
        if(thresh2[x,y,1,1] == 1){
          res[x,y,1, 1] <- i2[x,y,1,1]
          res[x,y,1, 2] <- i2[x,y,1,2]
          res[x,y,1, 3] <- i2[x,y,1,3]
        } 
        # else {
          # res[x,y,1, 1] <- i1[x,y,1,1]
          # res[x,y,1, 2] <- i1[x,y,1,2]
          # res[x,y,1, 3] <- i1[x,y,1,3]
        # }
   }
}
#save.image(res,args[3])
# pdf('D:/Uni/Diplom/SpringStubProject/stubproject/rScript/filename3.pdf')
pdf('D:/temp/StubSpringProject/stubproject/rScript/filename3.pdf')
plot(i1)
plot(i2)
plot(res)
dev.off()

print("DONE")
old.par <- par(mfrow=c(3, 4))

plot(i11, main="image 1 part")
plot(i21, main="image 2 part")

# plot(thmb1, main="thmb1")
# plot(thmb2, main="thmb2")
plot(thmb3, main="thmb3")
plot(thmb4, main="thmb4")

plot(x1, main="x1")
plot(x2, main="x2")

plot(sub1, main="x1-thmb3")
plot(sub2, main="x2-thmb4")

# plot(binary1 , main="Binary11")
# plot(binary2 , main="Binary21")

plot(thresh1 , main="thresh1")
plot(thresh2 , main="thresh2")

par(old.par)