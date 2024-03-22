permutaciones :: (Eq a)=> [a] -> [[a]]
permutaciones [] = [[]]
permutaciones xs = [x:p| x<-xs, p<- permutaciones(remove x xs)]

sublistas :: [a] -> [[a]]
sublistas [] = [[]]
sublistas (x:xs) = sublistas xs ++ map (x:) (sublistas xs)

remove :: (Eq a)=> a -> [a]->[a]
remove a [] = []
remove a (x:xs)
    |a==x = xs
    |otherwise = x : remove a xs
