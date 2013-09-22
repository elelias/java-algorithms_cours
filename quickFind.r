


quickFind<-function(mat,myl){
	for (i in 1:nrow(mat)){
		oldN=myl[mat[i,1]]
		myl[mat[i,1]]=myl[mat[i,2]]
		for (j in 1:10){
			if (myl[j]==oldN){
				myl[j]=myl[mat[i,2]]
			}
		}
		print(myl)
	}
	return(myl)		 
}


mat=matrix(0,nrow=6,ncol=2)
mat[1,]=c(8,1)
mat[2,]=c(1,7)
mat[3,]=c(10,6)
mat[4,]=c(5,7)
mat[5,]=c(2,6)
mat[6,]=c(3,1)

myl=c(1:10)

myl=quickFind(mat,myl)