

findFirstRootOfTree<-function(N){
	#cat('this shit is',rootOfTree[N],N,'\n')
	if (rootOfTree[N]!=N){
		findFirstRootOfTree(rootOfTree[N])
	}
	else{
		#cat('retuning now',N,'\n')
		return (N)
	}
}
findBigger<-function(L,R){
	sizeR=sizeOf[findFirstRootOfTree(R)]
	sizeL=sizeOf[findFirstRootOfTree(L)]
	if (sizeL > sizeR){
		big='L';small='R'
	}
	else if(sizeL < sizeR){
		big='R';small='L'
	}
	else{
		big='L';small='R'
	}
	#cat("returning",big,small,'\n')
	cat(big,"is bigger than",small,'\n')
	cat("look", L,R,'\n')
	return(list(big,small))
}
union<-function(L,R,rootOfTree,sizeOf){
	sizeList<-findBigger(L,R)
	#sizeList
	#print(list)
	big=sizeList[[1]]
	small=sizeList[[2]]
	#cat('hey',big,small,'\n')
	if (big=='L'){
		big=L
		small=R
	}else{
		big=R
		small=L
	}
	#sizeList[1] is the bigger tree
	bigRoot=findFirstRootOfTree(big)
	smallRoot=findFirstRootOfTree(small)
	rootOfTree[smallRoot]=bigRoot
	sizeOf[bigRoot]=sizeOf[bigRoot]+sizeOf[smallRoot]
	return(list(rootOfTree,sizeOf))
}

sizeOf=c(rep(1,10))
rootOfTree=c(1:10)
myl=1:10

mat=matrix(0,nrow=9,ncol=2)
mat[1,]=c(5,3)
mat[4,]=c(3,6)
mat[5,]=c(4,9)
mat[6,]=c(8,5)
mat[7,]=c(8,1)
mat[8,]=c(4,2)
mat[9,]=c(5,7)

# 4-3 0-2 1-9 6-7 2-1 8-6 4-6 9-6 1-5
for (i in 1:nrow(mat)){
	outlist=union(mat[i,1],mat[i,2],rootOfTree,sizeOf)
	rootOfTree=outlist[[1]]
	sizeOf=outlist[[2]]
	print('the roots are')
	print(rootOfTree)
	print('=====================')

}










