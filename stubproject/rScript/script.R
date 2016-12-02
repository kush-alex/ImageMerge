# if(!require(imager)){install.packages("imager", repos = "http://cran.us.r-project.org")}

library.path <- .libPaths()[2]
print(library.path)
library(imager)

parts <- 10
pxlsize <- 5

i1 <- load.image('D:/temp/StubSpringProject/stubproject/rScript/1.jpg')
i2 <- load.image('D:/temp/StubSpringProject/stubproject/rScript/2.jpg')

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

old.par <- par(mfrow=c(4, 4))


i11 <- i1#imglist1 [[5]]
i21 <- i2#imglist2 [[5]]

# i11 <- imglist1 [[5]]
# i21 <- imglist2 [[5]]

plot(i11, main="image 1 part")
plot(i21, main="image 2 part")


x1 <- imsplit(i11,"c") %>% add
x2 <- imsplit(i21,"c") %>% add

thmb1 <- resize(x1,200,200) 
thmb2 <- resize(x2,200,200) 

thmb3 <- resize(thmb1,dim(i11)[1],dim(i11)[2]) 
thmb4 <- resize(thmb2,dim(i21)[1],dim(i21)[2]) 

plot(thmb1, main="thmb1")
plot(thmb2, main="thmb2")
plot(thmb3, main="thmb3")
plot(thmb4, main="thmb4")


# print(imcol(i11,1))

# normalize_image(x1,3)


filter <- as.cimg(function(x,y) sign(x-5),10,10)
# plot(convolve(x1,filter),main="x1")
# plot(convolve(x2,filter),main="x1")

plot(x1, main="x1")
plot(x2, main="x2")

# plot(x1-x2, main="x1-x2")
# plot(x2-x1, main="x2-x1")

plot(x1-thmb3, main="x1-thmb3")

plot(x2-thmb4, main="x2-thmb4")

plot(round(x1-thmb3/max(x1-thmb3),0), main="Binary11")
plot(round(x2-thmb4/max(x2-thmb4),0), main="Binary21")

# print(x1[1,2])
# x1[1,1] <- 0.5
# plot(x1, main="Binary11")
# plot(fft(x1, inverse = FALSE), main="Binary11")

# plot(thresh(x1-thmb3, 10, 10, 0.05), main="Binary11")
# plot(thresh(x2-thmb4, 10, 10, 0.05), main="Binary21")


print(thmb1)


par(old.par)


# display(x1[44:dim(x1)[1],40:dim(x1)[2],])
# plot(x1)
# plot(x2)
# print(x1[44:dim(x1)[1],40:dim(x1)[2],])
# print(x2[0:dim(x2)[1],0:dim(x2)[2]])
