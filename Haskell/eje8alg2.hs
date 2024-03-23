permutaciones :: (Eq a)=> [a] -> [[a]]
permutaciones [] = [[]]
permutaciones xs = [x:p| x<-xs, p<- permutaciones(remove x xs)]

remove :: (Eq a)=> a -> [a]->[a]
remove a [] = []
remove a (x:xs)
    |a==x = xs
    |otherwise = x : remove a xs


slowsort :: (Ord a)=>[a]->[a]
slowsort [] = []
slowsort xs = esOrd ms
    where
        ms = permutaciones xs

esOrd :: (Ord a)=>[[a]]->[a]
esOrd [] = []
esOrd (x:xs)
    |orden x = x
    |otherwise = esOrd xs


orden :: (Ord a)=>[a]->Bool
orden [] = True
orden [x] = True
orden (x:y:xs) = x<=y && orden (y:xs)