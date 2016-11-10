library(imager)



parts <- 3

i1 <- load.image('D:/temp/StubSpringProject/stubproject/rScript/1.jpg')
i2 <- load.image('D:/temp/StubSpringProject/stubproject/rScript/2.jpg')

x1 <- imsplit(i1,'x', parts)
x2 <- imsplit(i2,'x', parts)

imglist1 <- list()
imglist2 <- list()

for(i in 1:parts) {
  y1 <- imsplit(x1[[i]],'y', parts)
  y2 <- imsplit(x2[[i]],'y', parts)
  imglist1<- c(imglist1,y1)
  imglist2<- c(imglist2,y2)
}

 i11 <- imglist1 [[1]]
 i21 <- imglist2 [[1]]

old.par <- par(mfrow=c(2, 2))
x1 <- imsplit(i11,"c") %>% add
x2 <- imsplit(i21,"c") %>% add
thmb1 <- resize(x1,-30,-30) 
thmb2 <- resize(x2,-30,-30) 
plot(x1, main="x1")
plot(x2, main="x2")
plot(thmb1, main="thmb1")
plot(thmb2, main="thmb2")
# plot(x1)
# plot(x2)
print(x1[0:dim(x1)[1],0:dim(x1)[2]][1])
print(x2[0:dim(x2)[1],0:dim(x2)[2]][1])
par(old.par)


