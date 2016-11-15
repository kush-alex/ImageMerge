library(imager)




parts <- 3
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
    # filter <- as.cimg(function(x,y) sign(x-5),10,10)
    # display(convolve(part,filter))
    # avarage_color <- 0
    # for(i in 1:dim(img)[1]){
    #   for(j in 1:dim(img)[2]){
    #   } 
    # }
  }
}

imglist1 <- split_image(i1, parts)
imglist2 <- split_image(i2, parts)
old.par <- par(mfrow=c(4, 2))
i11 <- imglist1 [[1]]
i21 <- imglist2 [[1]]
plot(i11)
plot(i21)
x1 <- imsplit(i11,"c") %>% add
x2 <- imsplit(i21,"c") %>% add
thmb1 <- resize(x1,-20,-20) 
thmb2 <- resize(x2,-20,-20) 
# plot(x1, main="x1")
# plot(x2, main="x2")
plot(thmb1, main="thmb1")
plot(thmb2, main="thmb2")


# print(imcol(i11,1))

# normalize_image(x1,3)
filter <- as.cimg(function(x,y) sign(x-5),10,10)
plot(convolve(x1,filter))
plot(convolve(x2,filter))
displacement(x1, x1, smoothness = 0.1, precision = 5,nb_scales = 0L, iteration_max = 10000L, is_backward = FALSE)
displacement(x1, x1, smoothness = 0.1, precision = 5,nb_scales = 0L, iteration_max = 10000L, is_backward = FALSE)

plot(x1, main="x1")
plot(x2, main="x2")
par(old.par)


# display(x1[44:dim(x1)[1],40:dim(x1)[2],])
# plot(x1)
# plot(x2)
# print(x1[44:dim(x1)[1],40:dim(x1)[2],])
# print(x2[0:dim(x2)[1],0:dim(x2)[2]])
